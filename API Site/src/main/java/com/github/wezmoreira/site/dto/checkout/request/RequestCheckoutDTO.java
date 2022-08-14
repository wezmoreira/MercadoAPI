package com.github.wezmoreira.site.dto.checkout.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestCheckoutDTO {
    List<RequestCheckoutItemDTO> itens;
    RequestCheckoutClienteDTO cliente_info;
}
