package com.github.wezmoreira.avaliacao.dto.rabbit;

import com.github.wezmoreira.avaliacao.entities.Pagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class RabbitMensagemDTO {
    private Long id;
    private String cpf;
    private Double total;
    private String tipo_pagamento;
    private Pagamento pagamento;
}
