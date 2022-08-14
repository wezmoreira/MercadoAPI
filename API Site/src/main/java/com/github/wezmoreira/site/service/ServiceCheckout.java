package com.github.wezmoreira.site.service;

import com.github.wezmoreira.site.dto.checkout.pedido.*;
import com.github.wezmoreira.site.dto.checkout.request.RequestCheckoutDTO;
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
import com.github.wezmoreira.site.repositories.RepositoryCheckout;
import com.github.wezmoreira.site.repositories.RepositoryCliente;
import com.github.wezmoreira.site.repositories.RepositoryItem;
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

    private final String uriPedido = "http://localhost:8080/api/pedido";

    public ResponseCheckoutPedidoDTO post(RequestCheckoutDTO requestCheckoutDTO) {

        Cliente clientesId = repositoryCliente.findById(requestCheckoutDTO.getCliente_info().getClienteId())
                .orElseThrow(ClienteNaoEncontradoException::new);
        List<RequestCheckoutPedidoItemDTO> itemDTOList = montaItens(requestCheckoutDTO);
        var clienteCartoes = montaCartao(requestCheckoutDTO);

        /*
        RequestCheckoutPedidoPagamentoDTO clienteCartoes = RequestCheckoutPedidoPagamentoDTO.builder()
                .nome_cartao(clientesId.getCliente_cartoes().get(0).getNome_cartao())
                .numero_cartao(clientesId.getCliente_cartoes().get(0).getNumero_cartao())
                .codigo_seguranca(clientesId.getCliente_cartoes().get(0).getCodigo_seguranca())
                .marca(clientesId.getCliente_cartoes().get(0).getMarca())
                .mes_expiracao(clientesId.getCliente_cartoes().get(0).getMes_expiracao())
                .ano_expiracao(clientesId.getCliente_cartoes().get(0).getAno_expiracao())
                .moeda(EnumMoeda.BRL)
                .valor(50.0) //calculo do total
                .build();


         */
        //********************************************

        RequestCheckoutPedidoDTO requestPedidoDTO = RequestCheckoutPedidoDTO.builder()
                .cpf(clientesId.getCpf())
                .total(50.0)
                .status(EnumStatus.EM_ANDAMENTO)
                .status_pagamento(EnumStatusPagamento.PROCESSING)
                .tipo_pagamento(EnumTipoPagamento.CREDIT_CARD)
                .itens(itemDTOList)
                .pedidoPagamento(clienteCartoes)
                .build();

        log.info("O valor da request é: " + requestPedidoDTO);

        var retorno = webClient.build().post()
                .uri(uriPedido)
                .bodyValue(requestPedidoDTO)
                .retrieve()
                .bodyToMono(ResponseRetornoPedidoDTO.class)
                .block();

        log.info("O valor de retorno é: " + retorno);
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

    public RequestCheckoutPedidoPagamentoDTO montaCartao(RequestCheckoutDTO requestCheckoutDTO){
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
                .valor(50.0) //precisa fazer o calculo do total
                .build();

        return clienteCartoes;
    }


    public List<RequestCheckoutPedidoItemDTO> montaItens(RequestCheckoutDTO requestCheckoutDTO) {
        List<RequestCheckoutPedidoItemDTO> requestCheckoutPedidoItemDTOS = new ArrayList<>();
        List<RequestCheckoutOfertaDTO> ofertas = new ArrayList<>();
        for (int i = 0; i < requestCheckoutDTO.getItens().size(); i++) {
            try {
            String id = requestCheckoutDTO.getItens().get(i).getSkuId();
            Items items = repositoryItem.findBySkuid(id); //.orElseThrow(ItemNaoExiste::new);
            log.info("O valor do pedidoItem antes  é: " + requestCheckoutDTO);

            RequestCheckoutPedidoItemDTO pedidoItem = RequestCheckoutPedidoItemDTO.builder()
                    .nome(items.getNome())
                    .data_criacao(items.getData_criacao())
                    .data_validade(items.getData_validade())
                    .valor(items.getValor())
                    .descricao(items.getDescricao())
                    .ofertas(ofertas)
                    .build();

            requestCheckoutPedidoItemDTOS.add(pedidoItem);
            repositoryItem.save(items);
            }catch (NullPointerException e) {
                throw new ItemNaoEncontradoException();
            }
            //log.info("O valor do pedidoItem é: " + pedidoItem);
        }
        return requestCheckoutPedidoItemDTOS;
    }
}
