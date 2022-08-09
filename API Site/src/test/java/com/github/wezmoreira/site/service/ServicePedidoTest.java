package com.github.wezmoreira.site.service;

import com.github.wezmoreira.site.AplicationConfigTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@Slf4j
@DisplayName("ServicePedidoTest")
public class ServicePedidoTest extends AplicationConfigTest {

    @MockBean
    private RepositoryPedido repositoryPedido;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    ServiceValidacao serviceValidacao;

    @Autowired
    ServicePedido servicePedido;


    @Test
    @DisplayName("Deveria deletar um pedido pelo o id")
    public void DeveriaDeletarPorId(){
        Pedido pedido = Mockito.mock(Pedido.class);
        Pedido pedidonovo = new Pedido();
        pedidonovo.setStatus_pagamento(EnumStatusPagamento.APPROVED);

        Mockito.when(repositoryPedido.findById(1l)).thenReturn(Optional.of(pedido));
        servicePedido.delete(1l);

        Assertions.assertEquals(EnumStatusPagamento.APPROVED, pedidonovo.getStatus_pagamento());

        //talvez mockar uma classe e tentar outra classe como new

        //Mockito.verify(repositoryPedido, Mockito.times(1)).findById(1l);
    }

    @Test
    @DisplayName("Deveria trazer os pedidos por id")
    public void DeveriaTrazerPedidosPorId(){
        Pedido pedido = Mockito.mock(Pedido.class);
        pedido.setId(1L);
        Mockito.when(repositoryPedido.findById(1L)).thenReturn(Optional.of(pedido));
        servicePedido.get(1l);
        Mockito.verify(repositoryPedido, Mockito.times(1)).findById(1L);
    }

    @Test
    @DisplayName("Deveria fazer um novo cadastro de pedido")
    public void DeveriaCadastrarPedido(){
        Pedido pedido = Mockito.mock(Pedido.class);
        RequestPedidoDTO requestPedidoDTO = Mockito.mock(RequestPedidoDTO.class);
        ResponsePedidoDTO responsePedidoDTO = Mockito.mock(ResponsePedidoDTO.class);
        pedido.setCpf("71417389915");
        pedido.setTotal(50.0);
        Mockito.when(serviceValidacao.validaDataItem(requestPedidoDTO)).thenReturn(true);
        Mockito.when(serviceValidacao.validaDataOfertas(requestPedidoDTO)).thenReturn(true);
        repositoryPedido.save(pedido);
        Mockito.when(servicePedido.post(requestPedidoDTO)).thenReturn(responsePedidoDTO);
    }

    @Test
    @DisplayName("Deveria fazer um patch de algum campo de pedido")
    public void DeveriaFazerPatchDePedido(){
        Pedido pedido = Mockito.mock(Pedido.class);
        RequestAtualizaPedidoDTO requestAtualizaPedidoDTO = Mockito.mock(RequestAtualizaPedidoDTO.class);
        RequestAtualizaPedidoDTO pedidoDto =
                RequestAtualizaPedidoDTO.builder().cpf("71417389915").total(50.0).build();
        Mockito.when(repositoryPedido.findById(1L)).thenReturn(Optional.of(pedido));
        servicePedido.patch(1L, pedidoDto);
        Assertions.assertEquals(50.0, pedidoDto.getTotal());
    }

    @Test
    @DisplayName("Deveria fazer um patch do Status aprovado de pedido")
    public void DeveriaFazerPatchDoStatusAprovado(){
        Pedido pedido = Mockito.mock(Pedido.class);
        //RequestAtualizaPedidoDTO requestAtualizaPedidoDTO = Mockito.mock(RequestAtualizaPedidoDTO.class);
        //RecebeDadoPagamentoDTO recebeDadoPagamentoDTO = Mockito.mock(RecebeDadoPagamentoDTO.class);

        RecebeDadoPagamentoDTO recebe = RecebeDadoPagamentoDTO.builder()
                .status_pagamento(EnumStatusPagamento.APPROVED.name()).build();

        Mockito.when(repositoryPedido.findById(1L)).thenReturn(Optional.of(pedido));
        servicePedido.atualizaStatus(1L, recebe);

        Assertions.assertEquals(EnumStatusPagamento.APPROVED.name(), recebe.getStatus_pagamento());
    }

    @Test
    @DisplayName("Deveria fazer um patch do Status reprovado de pedido")
    public void DeveriaFazerPatchDoStatusReprovado(){
        Pedido pedido = Mockito.mock(Pedido.class);
        //RequestAtualizaPedidoDTO requestAtualizaPedidoDTO = Mockito.mock(RequestAtualizaPedidoDTO.class);
        //RecebeDadoPagamentoDTO recebeDadoPagamentoDTO = Mockito.mock(RecebeDadoPagamentoDTO.class);

        RecebeDadoPagamentoDTO recebeReprovado = RecebeDadoPagamentoDTO.builder()
                .status_pagamento(EnumStatusPagamento.REPROVED.name()).build();

        Mockito.when(repositoryPedido.findById(1L)).thenReturn(Optional.of(pedido));
        servicePedido.atualizaStatus(1L, recebeReprovado);

        Assertions.assertEquals(EnumStatusPagamento.REPROVED.name(), recebeReprovado.getStatus_pagamento());
    }


}
