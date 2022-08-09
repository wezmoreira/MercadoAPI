package com.github.wezmoreira.pagamento.dto.rabbitMQ;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RetornaStatus {
    private Long pedido_id;
    private String status_pagamento;
}
