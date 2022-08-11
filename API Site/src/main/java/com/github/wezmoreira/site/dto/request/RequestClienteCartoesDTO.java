package com.github.wezmoreira.site.dto.request;

import com.github.wezmoreira.site.enums.EnumMarca;
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
    private String numero;
    @NotBlank
    private String codigo;
    @Enumerated(EnumType.STRING)
    private EnumMarca marca;
    @NotBlank
    private String mes_validade;
    @NotBlank
    private String ano_validade;

}
