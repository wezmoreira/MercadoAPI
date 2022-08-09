package com.github.wezmoreira.avaliacao.entities;

import com.github.wezmoreira.avaliacao.enums.EnumMarca;
import com.github.wezmoreira.avaliacao.enums.EnumMoeda;
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
@Table(name = "pedido_pagamentos")
public class Pagamento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero_cartao;
    private String nome_cartao;
    private String codigo_seguranca;
    @Enumerated(EnumType.STRING)
    private EnumMarca marca;
    private String mes_expiracao;
    private String ano_expiracao;
    @Enumerated(EnumType.STRING)
    private EnumMoeda moeda;
    private Double valor;
}
