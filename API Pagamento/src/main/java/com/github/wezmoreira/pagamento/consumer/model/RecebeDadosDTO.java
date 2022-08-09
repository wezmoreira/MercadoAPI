package com.github.wezmoreira.pagamento.consumer.model;

import com.github.wezmoreira.pagamento.entities.PagamentoRecebeDado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecebeDadosDTO {

    private Long id;
    private String cpf;
    private Double total;
    private String tipo_pagamento;
    private PagamentoRecebeDado pagamento;

}
