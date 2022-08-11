package com.github.wezmoreira.site.controller;

import com.github.wezmoreira.site.dto.request.RequestClienteDTO;
import com.github.wezmoreira.site.dto.request.atualizacao.RequestAtualizaClienteDTO;
import com.github.wezmoreira.site.dto.response.ResponseClienteDTO;
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
/*
    @GetMapping
    public ResponseEntity<Page<ResponseClienteDTO>> get
            (@RequestParam(required = false) String cpf, Pageable pageable) {
        Page<ResponseClienteDTO> responseClienteDTOS = serviceCliente.get(cpf, pageable);
        return ResponseEntity.ok(responseClienteDTOS);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ResponseClienteDTO> get(@PathVariable String cpf) {
        ResponseClienteDTO responseClienteDTO = serviceCliente.get(cpf);
        return ResponseEntity.ok(responseClienteDTO);
    }

    @PostMapping
    public ResponseEntity<ResponseClienteDTO> post
            (@RequestBody @Valid RequestClienteDTO requestClienteDTO, UriComponentsBuilder uriComponentsBuilder) {
        ResponseClienteDTO responseClienteDTO = serviceCliente.post(requestClienteDTO);
        URI uri = uriComponentsBuilder.path("/api/cliente/{id}").buildAndExpand(responseClienteDTO.getCpf()).toUri();
        return ResponseEntity.created(uri).body(responseClienteDTO);
    }

 */
}
