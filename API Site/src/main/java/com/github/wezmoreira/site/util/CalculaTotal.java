package com.github.wezmoreira.site.util;

import com.github.wezmoreira.site.dto.checkout.pedido.RequestCheckoutPedidoItemDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CalculaTotal {

    public Double calculaValor(List<RequestCheckoutPedidoItemDTO> list){
        Double total = 0.0;
        for(int i = 0; i < list.size(); i++){
            total += list.get(i).getValor();
        }
        log.info("Valor do double: " + total);
        return total;
    }
}
