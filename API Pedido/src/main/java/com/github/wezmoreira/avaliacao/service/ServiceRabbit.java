package com.github.wezmoreira.avaliacao.service;

import com.github.wezmoreira.avaliacao.dto.rabbit.RabbitMensagemDTO;
import com.github.wezmoreira.avaliacao.dto.rabbit.RecebeDadoPagamentoDTO;
import com.github.wezmoreira.avaliacao.dto.response.ResponsePedidoDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceRabbit {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    ServicePedido servicePedido;

    private final String QUEUERECEBE = "pedidos.v1.pagamentos-realizados";

    public void rabbitMensagem(ResponsePedidoDTO responsePedidoDTO) {
        RabbitMensagemDTO rabbitMensagemDTO = new RabbitMensagemDTO
                (responsePedidoDTO.getId(), responsePedidoDTO.getCpf(), responsePedidoDTO.getTotal(),
                        responsePedidoDTO.getTipo_pagamento().toString(), responsePedidoDTO.getPedidoPagamento());
        String routingKey = "pedidos.v1.pedidos-criados";
        rabbitTemplate.convertAndSend(routingKey, rabbitMensagemDTO);
    }

    @RabbitListener(queues = QUEUERECEBE)
    public void consumidor(RecebeDadoPagamentoDTO pagamentoDTO) {
        System.out.println("Id recebido: " + pagamentoDTO.getPedido_id());
        System.out.println("Status recebido: " + pagamentoDTO.getStatus_pagamento());
        System.out.println("*******************************");
        var id = pagamentoDTO.getPedido_id();
        servicePedido.atualizaStatus(id, pagamentoDTO);
    }
}
