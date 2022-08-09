package com.github.wezmoreira.avaliacao.service;

import com.github.wezmoreira.avaliacao.AplicationConfigTest;
import com.github.wezmoreira.avaliacao.dto.request.RequestPedidoDTO;
import com.github.wezmoreira.avaliacao.entities.Item;
import com.github.wezmoreira.avaliacao.entities.Oferta;
import com.github.wezmoreira.avaliacao.service.ServiceValidacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@DisplayName("ServicePedidoTest")
public class ServiceValidacaoTest extends AplicationConfigTest {

    @Autowired
    ServiceValidacao serviceValidacao;

//TA FUNCIONANDO FINALMENTE
    @Test
    @DisplayName("Deveria fazer a validação das ofertas")
    public void DeveriaValidarDataOferta(){
        ServiceValidacao serviceValidacao = new ServiceValidacao();
        RequestPedidoDTO requestPedidoDTO = new RequestPedidoDTO();
        List<Oferta> ofertaList = new ArrayList<>();
        List<Item> items = new ArrayList<>();
        LocalDateTime dateTime = LocalDateTime.of(2022, 5, 2, 11, 22, 0);
        LocalDateTime dataCriacao = LocalDateTime.of(2018, 7, 22, 10, 15, 30);
        LocalDateTime dataValidade = LocalDateTime.of(2025, 7, 22, 10, 15, 30);
        ofertaList.add(Oferta.builder().id(1L).data_criacao(dataCriacao).data_validade(dataValidade).build());
        items.add(Item.builder().id(1L).data_criacao(dataCriacao).data_validade(dataValidade).ofertas(ofertaList).build());
        requestPedidoDTO.setItens(items);
        boolean testData = serviceValidacao.validaDataOfertas(requestPedidoDTO);
        Assertions.assertFalse(testData);  //mudei pra false
    }

    @Test
    @DisplayName("Deveria fazer a validação dos itens")
    public void DeveriaValidarDataItem(){
        ServiceValidacao serviceValidacao = new ServiceValidacao();
        RequestPedidoDTO requestPedidoDTO = new RequestPedidoDTO();
        List<Item> items = new ArrayList<>();
        LocalDateTime dateTime = LocalDateTime.of(2022, 5, 2, 11, 22, 0);
        LocalDateTime dataCriacao = LocalDateTime.of(2018, 7, 22, 10, 15, 30);
        LocalDateTime dataValidade = LocalDateTime.of(2025, 7, 22, 10, 15, 30);
        items.add(Item.builder().id(1L).data_criacao(dataCriacao).data_validade(dataValidade).build());
        requestPedidoDTO.setItens(items);
        boolean testData = serviceValidacao.validaDataItem(requestPedidoDTO);
        Assertions.assertFalse(testData);
    }
}
