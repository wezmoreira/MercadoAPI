package com.github.wezmoreira.site.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestItemsDTO {

    @NotBlank
    private String nome;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data_validade;
    @NotNull @Positive
    private Double valor;
    @NotBlank
    private String descricao;
    @NotBlank
    private String estoque;
    private String skuid;
}
