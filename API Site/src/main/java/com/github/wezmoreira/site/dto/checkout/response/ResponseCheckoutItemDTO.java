package com.github.wezmoreira.site.dto.checkout.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCheckoutItemDTO {
    private String nome;
    private Double valor;
}
