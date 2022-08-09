package com.github.wezmoreira.avaliacao.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.wezmoreira.avaliacao.entities.Oferta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestItemDTO {

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
    private List<@Valid Oferta> ofertas;

}
