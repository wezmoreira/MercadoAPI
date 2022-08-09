package com.github.wezmoreira.avaliacao.dto.rabbit;

import com.github.wezmoreira.avaliacao.entities.Pagamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecebeDadoPagamentoDTO {
    private Long pedido_id;
    private String status_pagamento;
}
