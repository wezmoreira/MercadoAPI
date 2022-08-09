package com.github.wezmoreira.avaliacao.dto.response;

import com.github.wezmoreira.avaliacao.entities.Item;
import com.github.wezmoreira.avaliacao.entities.Pagamento;
import com.github.wezmoreira.avaliacao.enums.EnumStatus;
import com.github.wezmoreira.avaliacao.enums.EnumStatusPagamento;
import com.github.wezmoreira.avaliacao.enums.EnumTipoPagamento;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
public class ResponsePedidoDTO {

    private Long id;
    private String cpf;
    private Double total;

    private EnumStatus status;
    private EnumStatusPagamento status_pagamento;
    private EnumTipoPagamento tipo_pagamento;

    private List<Item> itens;
    private Pagamento pedidoPagamento;
    //private List<@Valid Pagamento> pedidoPagamento;
}
