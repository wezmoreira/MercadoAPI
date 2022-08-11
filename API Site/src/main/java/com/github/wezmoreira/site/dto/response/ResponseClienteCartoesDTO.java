package com.github.wezmoreira.site.dto.response;

import com.github.wezmoreira.site.enums.EnumMarca;
import lombok.*;

import javax.persistence.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseClienteCartoesDTO {

    private Long id;
    private String numero;
    private String codigo;
    private EnumMarca marca;
    private String mes_validade;
    private String ano_validade;

}
