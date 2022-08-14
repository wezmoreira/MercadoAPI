package com.github.wezmoreira.site.controller;

import com.github.wezmoreira.site.dto.checkout.request.RequestCheckoutDTO;
import com.github.wezmoreira.site.dto.checkout.response.ResponseCheckoutPedidoDTO;
import com.github.wezmoreira.site.dto.request.RequestClienteDTO;
import com.github.wezmoreira.site.dto.request.atualizacao.RequestAtualizaClienteDTO;
import com.github.wezmoreira.site.dto.response.ResponseClienteDTO;
import com.github.wezmoreira.site.service.ServiceCheckout;
import com.github.wezmoreira.site.service.ServiceCliente;
import com.github.wezmoreira.site.service.ServiceClienteCartao;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/api/checkout")
public class ControllerCheckout {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ServiceCheckout serviceCheckout;

    @PostMapping
    public ResponseEntity<ResponseCheckoutPedidoDTO> post
            (@RequestBody @Valid RequestCheckoutDTO requestCheckoutDTO, UriComponentsBuilder uriComponentsBuilder) {
        ResponseCheckoutPedidoDTO responseCheckoutPedidoDTO = serviceCheckout.post(requestCheckoutDTO);
        URI uri = uriComponentsBuilder.path("http://localhost:8080/api/pedido/").buildAndExpand(responseCheckoutPedidoDTO.getNumeroDoPedido()).toUri();
        return ResponseEntity.created(uri).body(responseCheckoutPedidoDTO);
    }
}
