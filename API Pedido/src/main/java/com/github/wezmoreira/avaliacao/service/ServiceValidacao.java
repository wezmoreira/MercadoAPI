package com.github.wezmoreira.avaliacao.service;


import com.github.wezmoreira.avaliacao.dto.request.RequestPedidoDTO;
import com.github.wezmoreira.avaliacao.entities.Item;
import com.github.wezmoreira.avaliacao.exceptions.DataInvalidaException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceValidacao {

    public boolean validaDataItem(RequestPedidoDTO requestPedidoDTO) {
        boolean valido = true;
        List<Item> itemDTOS = requestPedidoDTO.getItens();
        for (int i = 0; i < requestPedidoDTO.getItens().size(); i++) {
            valido = itemDTOS.get(i).getData_validade()
                    .isBefore(itemDTOS.get(i).getData_criacao());
            if(itemDTOS.get(i).getData_criacao()
                    .isAfter(itemDTOS.get(i).getData_validade())){
                throw new DataInvalidaException();
            }
        }
        return valido;
    }

    public boolean validaDataOfertas(RequestPedidoDTO requestPedidoDTO) {
        boolean valido = true;
        List<Item> itemDTOS = requestPedidoDTO.getItens();
        for (int i = 0; i < requestPedidoDTO.getItens().size(); i++) {
            for(int j = 0; j < requestPedidoDTO.getItens().get(i).getOfertas().size(); j++) {
                valido = itemDTOS.get(i).getOfertas().get(j).getData_validade()
                        .isBefore(itemDTOS.get(i).getOfertas().get(j).getData_criacao());
                if (itemDTOS.get(i).getOfertas().get(j).getData_criacao()
                        .isAfter(itemDTOS.get(i).getOfertas().get(j).getData_validade())) {
                    throw new DataInvalidaException();
                }
            }
        }
        return valido;
    }
}

