package com.github.wezmoreira.avaliacao.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedido_itens")
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data_criacao;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data_validade;
    @NotNull @Positive
    private Double valor;
    @NotBlank
    private String descricao;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "RL_itens_ofertas",
            joinColumns = @JoinColumn(
                    name = "item_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "oferta_id"
            )
    )
    private List<Oferta> ofertas;
}
