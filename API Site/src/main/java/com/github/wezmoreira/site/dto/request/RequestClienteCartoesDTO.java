package com.github.wezmoreira.site.dto.request;

import com.github.wezmoreira.site.enums.EnumMarca;
import com.github.wezmoreira.site.enums.EnumMoeda;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestClienteCartoesDTO {
    @NotBlank
    private String nome_cartao;
    @NotBlank
    private String numero_cartao;
    @NotBlank
    private String codigo_seguranca;
    @Enumerated(EnumType.STRING)
    private EnumMarca marca;
    @NotBlank
    private String mes_expiracao;
    @NotBlank
    private String ano_expiracao;
}
