package com.github.wezmoreira.avaliacao.dto.request;

import com.github.wezmoreira.avaliacao.entities.Pagamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestPagamentoPedidoDTO {

    // talvez deletar

    private Long id;
    private String cpf;
    private Double total;
    private String tipo_pagamento;
    private Pagamento pagamento;
}
