package com.github.wezmoreira.site.dto.checkout.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestCheckoutItemDTO {
    private String skuId;
    private Integer qtd;
}
