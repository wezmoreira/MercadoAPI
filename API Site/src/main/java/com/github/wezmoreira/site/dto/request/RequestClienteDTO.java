package com.github.wezmoreira.site.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.wezmoreira.site.entities.ClienteCartoes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestClienteDTO {

    @NotBlank(message = "O CPF deve ser unico")
    @Column(unique=true, updatable = false)
    @NotNull
    @CPF(message = "Deve ser um CPF valido!")
    private String cpf;
    @NotBlank()
    private String nome;
    private List<ClienteCartoes> cliente_cartoes;; // talvez retirar
}
