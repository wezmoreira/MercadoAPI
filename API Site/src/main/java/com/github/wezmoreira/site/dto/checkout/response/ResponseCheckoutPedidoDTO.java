package com.github.wezmoreira.site.dto.checkout.response;

import com.github.wezmoreira.site.dto.response.ResponseItemsDTO;
import com.github.wezmoreira.site.enums.EnumStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCheckoutPedidoDTO {
    private Long numeroDoPedido;
    private Double total;
    private EnumStatus status;
    private List<ResponseCheckoutItemDTO> itens;

}
