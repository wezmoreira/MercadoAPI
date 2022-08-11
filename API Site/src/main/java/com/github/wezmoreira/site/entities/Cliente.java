package com.github.wezmoreira.site.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"cpf"})})
public class Cliente {

    @Id
    @Column(unique=true, updatable = false)
    @NotBlank @NotNull
    private String cpf;
    @NotBlank
    private String nome;
    @CreationTimestamp
    private LocalDateTime data_criacao;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "RL_cliente_id")
    private List<ClienteCartoes> cliente_cartoes;
}
