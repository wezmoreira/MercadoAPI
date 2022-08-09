package com.github.wezmoreira.avaliacao.service;


import com.github.wezmoreira.avaliacao.AplicationConfigTest;
import com.github.wezmoreira.avaliacao.entities.Item;
import com.github.wezmoreira.avaliacao.repositories.RepositoryItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Optional;

@DisplayName("ServicePedidoTest")
public class ServiceItemTest extends AplicationConfigTest {

    @MockBean
    private RepositoryItem repositoryItem;

    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    ServiceItem serviceItem;

    @Test
    @DisplayName("Deveria fazer um patch de algum campo de item")
    public void DeveriaFazerPatchDeItem(){
        Item pedido = Mockito.mock(Item.class);
        LocalDateTime dataCriacao = LocalDateTime.of(2018, 07, 22, 10, 15, 30);
        LocalDateTime dataValidade = LocalDateTime.of(2025, 07, 22, 10, 15, 30);
        RequestAtualizaItemDTO requestAtualizaItemDTO = Mockito.mock(RequestAtualizaItemDTO.class);
        RequestAtualizaItemDTO itemDTO =
                RequestAtualizaItemDTO.builder().nome("Headset").valor(50.0).descricao("Fone").data_criacao(dataCriacao)
                        .data_validade(dataValidade).build();
        Mockito.when(repositoryItem.findById(1L)).thenReturn(Optional.of(pedido));
        serviceItem.patch(1L, itemDTO);
        Assertions.assertEquals("Headset", itemDTO.getNome());
        Assertions.assertEquals(50.0, itemDTO.getValor());
        Assertions.assertEquals("Fone", itemDTO.getDescricao());
        Assertions.assertEquals(dataCriacao, itemDTO.getData_criacao());
        Assertions.assertEquals(dataValidade, itemDTO.getData_validade());
    }

}
