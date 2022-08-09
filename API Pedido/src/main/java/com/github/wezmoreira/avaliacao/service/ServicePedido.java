package com.github.wezmoreira.avaliacao.service;

import com.github.wezmoreira.avaliacao.dto.rabbit.RecebeDadoPagamentoDTO;
import com.github.wezmoreira.avaliacao.dto.request.RequestPedidoDTO;
import com.github.wezmoreira.avaliacao.dto.request.atualizacao.RequestAtualizaPedidoDTO;
import com.github.wezmoreira.avaliacao.dto.response.ResponsePedidoDTO;
import com.github.wezmoreira.avaliacao.entities.Pedido;
import com.github.wezmoreira.avaliacao.enums.EnumStatus;
import com.github.wezmoreira.avaliacao.enums.EnumStatusPagamento;
import com.github.wezmoreira.avaliacao.exceptions.DelecaoInvalidaException;
import com.github.wezmoreira.avaliacao.exceptions.PedidoNaoEncontradoException;
import com.github.wezmoreira.avaliacao.exceptions.RemocaoInvalidaException;
import com.github.wezmoreira.avaliacao.repositories.RepositoryPedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ServicePedido {

    @Autowired
    private RepositoryPedido repositoryPedido;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ServiceValidacao serviceValidacao;

    public Page<ResponsePedidoDTO> get(String cpf, Pageable pageable) {
        Page<Pedido> pedidos;
        if (cpf == null) {
            pedidos = repositoryPedido.findAll(pageable);
        } else {
            pedidos = repositoryPedido.findByCpf(cpf, pageable);
        }
        return pedidos.map(pedidoEntity -> modelMapper.map
                (pedidoEntity, ResponsePedidoDTO.class));
    }

    public ResponsePedidoDTO get(Long id) {
        Pedido pedido = repositoryPedido.findById(id)
                .orElseThrow(PedidoNaoEncontradoException::new);
        return modelMapper.map(pedido, ResponsePedidoDTO.class);
    }

    public ResponsePedidoDTO post(RequestPedidoDTO requestPedidoDTO) {
        serviceValidacao.validaDataItem(requestPedidoDTO);
        serviceValidacao.validaDataOfertas(requestPedidoDTO);
        Pedido pedidos = modelMapper.map(requestPedidoDTO, Pedido.class);
        Pedido pedidoSalvo = repositoryPedido.save(pedidos);
        return modelMapper.map(pedidoSalvo, ResponsePedidoDTO.class);
    }

    public ResponsePedidoDTO patch(Long id, RequestAtualizaPedidoDTO requestAtualizaPedidoDTO) {
        Pedido pedido = repositoryPedido.findById(id)
                .orElseThrow(PedidoNaoEncontradoException::new);
        if(requestAtualizaPedidoDTO.getCpf() != null) {
            pedido.setCpf(requestAtualizaPedidoDTO.getCpf());
        }
        if(requestAtualizaPedidoDTO.getTotal() != null) {
            pedido.setTotal(requestAtualizaPedidoDTO.getTotal());
        }
        repositoryPedido.save(pedido);
        return modelMapper.map(pedido, ResponsePedidoDTO.class);
    }

    public void delete(Long id) {
        Pedido pedido = repositoryPedido.findById(id)
                .orElseThrow(PedidoNaoEncontradoException::new);
        if (pedido.getStatus_pagamento().equals(EnumStatusPagamento.APPROVED) ||
                pedido.getStatus_pagamento().equals(EnumStatusPagamento.REPROVED)) {
            throw new RemocaoInvalidaException();
        }
        repositoryPedido.delete(pedido);
    }

    public void atualizaStatus(Long id, RecebeDadoPagamentoDTO recebeDadoPagamentoDTO) {
        Pedido pedido = repositoryPedido.findById(id)
                .orElseThrow(PedidoNaoEncontradoException::new);
        if(recebeDadoPagamentoDTO.getStatus_pagamento().equals(EnumStatusPagamento.APPROVED.name())) {
            pedido.setStatus_pagamento(EnumStatusPagamento.APPROVED);
            pedido.setStatus(EnumStatus.FINALIZADO);
        }
        if(recebeDadoPagamentoDTO.getStatus_pagamento().equals(EnumStatusPagamento.REPROVED.name())) {
            pedido.setStatus_pagamento(EnumStatusPagamento.REPROVED);
            pedido.setStatus(EnumStatus.FINALIZADO);
        }
        repositoryPedido.save(pedido);
    }
}
