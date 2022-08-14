package com.github.wezmoreira.site.dto.response;

import com.github.wezmoreira.site.enums.EnumMarca;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseClienteCartoesDTO {

    private Long id;
    private String nome_cartao;
    private String numero_cartao;
    private String codigo_seguranca;
    private EnumMarca marca;
    private String mes_expiracao;
    private String ano_expiracao;
}
