package com.github.wezmoreira.avaliacao.dto.response;

import com.github.wezmoreira.avaliacao.enums.EnumMarca;
import com.github.wezmoreira.avaliacao.enums.EnumMoeda;
import lombok.Data;

import javax.persistence.*;

@Data
public class ResponsePagamentoDTO {
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
