package com.github.wezmoreira.avaliacao.entities;

import com.github.wezmoreira.avaliacao.enums.EnumMarca;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente_cartoes {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private String codigo;
    @Enumerated(EnumType.STRING)
    private EnumMarca marca;
    private String mes_validade;
    private String ano_validade;

}
