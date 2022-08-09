package com.github.wezmoreira.avaliacao.dto.request;

import com.github.wezmoreira.avaliacao.entities.Item;
import com.github.wezmoreira.avaliacao.entities.Pagamento;
import com.github.wezmoreira.avaliacao.enums.EnumStatus;
import com.github.wezmoreira.avaliacao.enums.EnumStatusPagamento;
import com.github.wezmoreira.avaliacao.enums.EnumTipoPagamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestPedidoDTO {

    @NotBlank
    @CPF(message = "CPF inválido! Deve seguir o padrão de 11 digitos")
    private String cpf;
    @NotNull @Positive
    private Double total;
    @NotNull
    private EnumStatus status;
    @NotNull
    private EnumStatusPagamento status_pagamento;
    @NotNull
    private EnumTipoPagamento tipo_pagamento;
    @NotNull
    private List<@Valid Item> itens;
    @NotNull
    private Pagamento pedidoPagamento;
    //private List<@Valid Pagamento> pedidoPagamento;

}
