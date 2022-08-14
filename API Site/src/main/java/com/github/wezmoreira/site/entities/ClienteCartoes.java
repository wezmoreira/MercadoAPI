package com.github.wezmoreira.site.entities;

import com.github.wezmoreira.site.enums.EnumMarca;
import com.github.wezmoreira.site.enums.EnumMoeda;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteCartoes {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero_cartao;
    private String nome_cartao;
    private String codigo_seguranca;
    @Enumerated(EnumType.STRING)
    private EnumMarca marca;
    private String mes_expiracao;
    private String ano_expiracao;
}