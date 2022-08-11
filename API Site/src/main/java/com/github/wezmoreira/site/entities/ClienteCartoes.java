package com.github.wezmoreira.site.entities;

import com.github.wezmoreira.site.enums.EnumMarca;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteCartoes {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private String codigo;
    @Enumerated(EnumType.STRING)
    private EnumMarca marca;
    private String mes_validade;
    private String ano_validade;

}
