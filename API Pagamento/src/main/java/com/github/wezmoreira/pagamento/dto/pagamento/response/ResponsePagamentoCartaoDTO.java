package com.github.wezmoreira.pagamento.dto.pagamento.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePagamentoCartaoDTO {
    private Long id;
    private String numero_cartao;
    private String nome_cartao;
    private String codigo;
    private String marca;
    private String mes;
    private String ano;
    private String moeda;
    private Double valor;
}
