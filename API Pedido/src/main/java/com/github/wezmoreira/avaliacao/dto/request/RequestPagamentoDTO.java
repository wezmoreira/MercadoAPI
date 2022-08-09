package com.github.wezmoreira.avaliacao.dto.request;

import com.github.wezmoreira.avaliacao.enums.EnumMarca;
import com.github.wezmoreira.avaliacao.enums.EnumMoeda;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestPagamentoDTO {
    @NotBlank
    private String numero_cartao;
    @NotBlank
    private String nome_cartao;
    @NotBlank
    private String codigo_seguranca;
    @Enumerated(EnumType.STRING)
    @NotBlank @NotNull
    private EnumMarca marca;
    @NotBlank
    private String mes_expiracao;
    @NotBlank
    private String ano_expiracao;
    @Enumerated(EnumType.STRING)
    @NotBlank @NotNull
    private EnumMoeda moeda;
    @NotBlank @Positive
    private Double valor;

}
