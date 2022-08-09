package com.github.wezmoreira.pagamento.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoRecebeDado {

    private Long id;
    private String numero_cartao;
    private String nome_cartao;
    private String codigo_seguranca;
    private String marca;
    private String mes_expiracao;
    private String ano_expiracao;
    private String moeda;
    private Double valor;
}
