package com.github.wezmoreira.avaliacao.dto.request.atualizacao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.wezmoreira.avaliacao.entities.Oferta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestAtualizaItemDTO {

    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data_criacao;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data_validade;
    private Double valor;
    private String descricao;
    private List<Oferta> ofertas;

}
