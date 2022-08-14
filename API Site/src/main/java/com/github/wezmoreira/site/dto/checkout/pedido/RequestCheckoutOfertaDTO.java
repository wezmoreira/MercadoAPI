package com.github.wezmoreira.site.dto.checkout.pedido;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.wezmoreira.site.enums.EnumStatus;
import com.github.wezmoreira.site.enums.EnumStatusPagamento;
import com.github.wezmoreira.site.enums.EnumTipoPagamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestCheckoutOfertaDTO {
    private String nome;
    private LocalDateTime data_criacao;
    private LocalDateTime data_validade;
    private Double desconto;
    private String descricao;
}
