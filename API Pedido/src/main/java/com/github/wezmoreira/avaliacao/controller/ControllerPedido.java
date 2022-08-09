package com.github.wezmoreira.avaliacao.controller;

import com.github.wezmoreira.avaliacao.dto.request.RequestPedidoDTO;
import com.github.wezmoreira.avaliacao.dto.request.atualizacao.RequestAtualizaPedidoDTO;
import com.github.wezmoreira.avaliacao.dto.response.ResponsePedidoDTO;
import com.github.wezmoreira.avaliacao.service.ServicePedido;
import com.github.wezmoreira.avaliacao.service.ServiceRabbit;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pedido")
public class ControllerPedido {

    @Autowired
    ServicePedido servicePedido;

    @Autowired
    ServiceRabbit serviceRabbit;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<Page<ResponsePedidoDTO>> get
            (@RequestParam(required = false) String cpf, Pageable pageable) {
        Page<ResponsePedidoDTO> responsePedidoDTOS = servicePedido.get(cpf, pageable);
        return ResponseEntity.ok(responsePedidoDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePedidoDTO> get(@PathVariable Long id) {
        ResponsePedidoDTO responseAssociadosDto = servicePedido.get(id);
        return ResponseEntity.ok(responseAssociadosDto);
    }

    @PostMapping
    public ResponseEntity<ResponsePedidoDTO> post
            (@RequestBody @Valid RequestPedidoDTO requestPedidoDTO, UriComponentsBuilder uriComponentsBuilder) {
        ResponsePedidoDTO responsePedidoDTO = servicePedido.post(requestPedidoDTO);
        URI uri = uriComponentsBuilder.path("/api/pedidos/{id}").buildAndExpand(responsePedidoDTO.getId()).toUri();
        serviceRabbit.rabbitMensagem(responsePedidoDTO); //comunicação com o consumer
        System.out.println(responsePedidoDTO.getPedidoPagamento());
        System.out.println(responsePedidoDTO.getItens());
        return ResponseEntity.created(uri).body(responsePedidoDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponsePedidoDTO> atualiza(@PathVariable Long id,
                                                      @Valid @RequestBody RequestAtualizaPedidoDTO requestPedidoDTO) {
        ResponsePedidoDTO responseDto = servicePedido.patch(id, requestPedidoDTO);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        servicePedido.delete(id);
        return ResponseEntity.noContent().build();
    }
}
