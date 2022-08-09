package com.github.wezmoreira.avaliacao.entities;

import com.github.wezmoreira.avaliacao.enums.EnumStatus;
import com.github.wezmoreira.avaliacao.enums.EnumStatusPagamento;
import com.github.wezmoreira.avaliacao.enums.EnumTipoPagamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedido")
public class Pedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private Double total;

    @Enumerated(EnumType.STRING)
    private EnumStatus status;
    @Enumerated(EnumType.STRING)
    private EnumStatusPagamento status_pagamento;
    @Enumerated(EnumType.STRING)
    private EnumTipoPagamento tipo_pagamento;

    //Alterei de @ManyToMany
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "RL_pedidos_itens",
            joinColumns = @JoinColumn(
                    name = "pedido_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "item_id"
            )
    )
    private List<Item> itens;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pagamento_id")
    private Pagamento pedidoPagamento;
    //private List<@Valid Pagamento> pedidoPagamento; //alterei
}
