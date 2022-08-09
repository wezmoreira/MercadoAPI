package com.github.wezmoreira.pagamento.dto;

import com.github.wezmoreira.pagamento.dto.pagamento.response.ResponsePagamentoCartaoDTO;
import com.github.wezmoreira.pagamento.entities.Pagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PagamentoDTO {

    private Long id;
    private Long pedidoId;
    private String cpf;
    private LocalDateTime data;
    private double total;
    private String tipo_pagamento;
    private String status;
    private Pagamento pagamento;
    private ResponsePagamentoCartaoDTO pagamento_cartao;
}
