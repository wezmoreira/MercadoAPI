package com.github.wezmoreira.site.service;

import com.github.wezmoreira.site.dto.checkout.pedido.*;
import com.github.wezmoreira.site.dto.checkout.request.RequestCheckoutDTO;
import com.github.wezmoreira.site.dto.checkout.request.RequestCheckoutItemDTO;
import com.github.wezmoreira.site.dto.checkout.response.ResponseCheckoutItemDTO;
import com.github.wezmoreira.site.dto.checkout.response.ResponseCheckoutPedidoDTO;
import com.github.wezmoreira.site.entities.Cliente;
import com.github.wezmoreira.site.entities.Items;
import com.github.wezmoreira.site.enums.EnumMoeda;
import com.github.wezmoreira.site.enums.EnumStatus;
import com.github.wezmoreira.site.enums.EnumStatusPagamento;
import com.github.wezmoreira.site.enums.EnumTipoPagamento;
import com.github.wezmoreira.site.exceptions.ClienteNaoEncontradoException;
import com.github.wezmoreira.site.exceptions.ItemNaoEncontradoException;
import com.github.wezmoreira.site.exceptions.SemEstoqueException;
import com.github.wezmoreira.site.repositories.RepositoryCheckout;
import com.github.wezmoreira.site.repositories.RepositoryCliente;
import com.github.wezmoreira.site.repositories.RepositoryItem;
import com.github.wezmoreira.site.util.CalculaTotal;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ServiceCheckout {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    WebClient.Builder webClient;

    @Autowired
    ServiceCheckout serviceCheckout;

    @Autowired
    RepositoryCliente repositoryCliente;

    @Autowired
    RepositoryItem repositoryItem;

    @Autowired
    RepositoryCheckout repositoryCheckout;

    @Autowired
    CalculaTotal calculaTotal;

    private final String uriPedido = "http://localhost:8080/api/pedido";

    public ResponseCheckoutPedidoDTO post(RequestCheckoutDTO requestCheckoutDTO) {

        Cliente clientesId = repositoryCliente.findById(requestCheckoutDTO.getCliente_info().getClienteId())
                .orElseThrow(ClienteNaoEncontradoException::new);
        List<RequestCheckoutPedidoItemDTO> itemDTOList = montaItens(requestCheckoutDTO);
        var total = calculaTotal.calculaValor(itemDTOList, requestCheckoutDTO);
        var clienteCartoes = montaCartao(requestCheckoutDTO, total);

        RequestCheckoutPedidoDTO requestPedidoDTO = RequestCheckoutPedidoDTO.builder()
                .cpf(clientesId.getCpf())
                .total(total)
                .status(EnumStatus.EM_ANDAMENTO)
                .status_pagamento(EnumStatusPagamento.PROCESSING)
                .tipo_pagamento(EnumTipoPagamento.CREDIT_CARD)
                .itens(itemDTOList)
                .pedidoPagamento(clienteCartoes)
                .build();

        var retorno = webClient.build().post()
                .uri(uriPedido)
                .bodyValue(requestPedidoDTO)
                .retrieve()
                .bodyToMono(ResponseRetornoPedidoDTO.class)
                .block();

        return retornoDoPedido(retorno);
    }

    public ResponseCheckoutPedidoDTO retornoDoPedido(ResponseRetornoPedidoDTO responseRetornoPedidoDTO){
        List<ResponseCheckoutItemDTO> setItens = responseRetornoPedidoDTO.getItens().stream().map(
                item -> modelMapper.map(item, ResponseCheckoutItemDTO.class)).collect(Collectors.toList());
        ResponseCheckoutPedidoDTO retornoPedido = ResponseCheckoutPedidoDTO.builder()
                .numeroDoPedido(responseRetornoPedidoDTO.getId())
                .total(responseRetornoPedidoDTO.getTotal())
                .status(responseRetornoPedidoDTO.getStatus())
                .itens(setItens)
                .build();
        retornoPedido.setNumeroDoPedido(responseRetornoPedidoDTO.getId());
        return retornoPedido;
    }

    public RequestCheckoutPedidoPagamentoDTO montaCartao(RequestCheckoutDTO requestCheckoutDTO, Double total){
        Cliente clientesId = repositoryCliente.findById(requestCheckoutDTO.getCliente_info().getClienteId())
                .orElseThrow(ClienteNaoEncontradoException::new);

        RequestCheckoutPedidoPagamentoDTO clienteCartoes = RequestCheckoutPedidoPagamentoDTO.builder()
                .nome_cartao(clientesId.getCliente_cartoes().get(0).getNome_cartao())
                .numero_cartao(clientesId.getCliente_cartoes().get(0).getNumero_cartao())
                .codigo_seguranca(clientesId.getCliente_cartoes().get(0).getCodigo_seguranca())
                .marca(clientesId.getCliente_cartoes().get(0).getMarca())
                .mes_expiracao(clientesId.getCliente_cartoes().get(0).getMes_expiracao())
                .ano_expiracao(clientesId.getCliente_cartoes().get(0).getAno_expiracao())
                .moeda(EnumMoeda.BRL)
                .valor(total)
                .build();

        return clienteCartoes;
    }

    public List<RequestCheckoutPedidoItemDTO> montaItens(RequestCheckoutDTO requestCheckoutDTO) {
        List<RequestCheckoutPedidoItemDTO> requestCheckoutPedidoItemDTOS = new ArrayList<>();
        List<RequestCheckoutOfertaDTO> ofertas = new ArrayList<>();
        for (int i = 0; i < requestCheckoutDTO.getItens().size(); i++) {
            try {
                String id = requestCheckoutDTO.getItens().get(i).getSkuId();
                Items items = repositoryItem.findBySkuid(id);
                if (items.getEstoque() < requestCheckoutDTO.getItens().get(i).getQtd()) {
                    throw new SemEstoqueException();
                }
                RequestCheckoutPedidoItemDTO pedidoItem = RequestCheckoutPedidoItemDTO.builder()
                        .nome(items.getNome()).data_criacao(items.getData_criacao())
                        .data_validade(items.getData_validade())
                        .valor(items.getValor())
                        .descricao(items.getDescricao()).ofertas(ofertas).build();
                requestCheckoutPedidoItemDTOS.add(pedidoItem);
                items.setEstoque(items.getEstoque() - requestCheckoutDTO.getItens().get(i).getQtd());
                repositoryItem.save(items);
            }catch (NullPointerException e) {
                throw new ItemNaoEncontradoException();
            }
        }
        return requestCheckoutPedidoItemDTOS;
    }
}
