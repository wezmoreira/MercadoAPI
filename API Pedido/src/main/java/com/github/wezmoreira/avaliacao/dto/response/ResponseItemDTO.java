package com.github.wezmoreira.avaliacao.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.wezmoreira.avaliacao.entities.Oferta;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ResponseItemDTO {
    private Long id;
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data_criacao;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data_validade;
    private Double valor;
    private String descricao;
    private List<Oferta> ofertas;

}
