package com.github.wezmoreira.site.dto.response;

import lombok.*;

import java.time.LocalDateTime;


@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseItemsDTO {

    private Long id;
    private String nome;
    private LocalDateTime data_validade;
    private Double valor;
    private String descricao;
    private String estoque;
    private String skuid;
}
