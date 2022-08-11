package com.github.wezmoreira.site.dto.response;

import com.github.wezmoreira.site.entities.ClienteCartoes;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseClienteDTO {

    private String cpf;
    private String nome;
    private LocalDateTime data_criacao;
    private List<ClienteCartoes> cliente_cartoes;;
}
