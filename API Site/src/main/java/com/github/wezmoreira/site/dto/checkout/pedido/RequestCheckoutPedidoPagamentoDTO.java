package com.github.wezmoreira.site.dto.checkout.pedido;

import com.github.wezmoreira.site.entities.ClienteCartoes;
import com.github.wezmoreira.site.entities.Items;
import com.github.wezmoreira.site.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestCheckoutPedidoPagamentoDTO {

    private String numero_cartao;
    private String nome_cartao;
    private String codigo_seguranca;
    private EnumMarca marca;
    private String mes_expiracao;
    private String ano_expiracao;
    private EnumMoeda moeda;
    private Double valor;
}
