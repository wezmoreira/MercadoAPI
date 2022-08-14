package com.github.wezmoreira.site.dto.checkout.pedido;

import com.github.wezmoreira.site.entities.ClienteCartoes;
import com.github.wezmoreira.site.entities.Items;
import com.github.wezmoreira.site.enums.EnumStatus;
import com.github.wezmoreira.site.enums.EnumStatusPagamento;
import com.github.wezmoreira.site.enums.EnumTipoPagamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestCheckoutPedidoDTO {
    private String cpf;
    private Double total;
    private EnumStatus status;
    private EnumStatusPagamento status_pagamento;
    private EnumTipoPagamento tipo_pagamento;
    private List<RequestCheckoutPedidoItemDTO> itens;
    private RequestCheckoutPedidoPagamentoDTO pedidoPagamento;  //alterei para ClienteCartoes
}
