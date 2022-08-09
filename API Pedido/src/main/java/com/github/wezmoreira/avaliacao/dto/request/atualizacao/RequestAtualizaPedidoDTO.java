package com.github.wezmoreira.avaliacao.dto.request.atualizacao;

import com.github.wezmoreira.avaliacao.entities.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestAtualizaPedidoDTO {

    private String cpf;
    private Double total;
    private List<@Valid Item> itens;


}
