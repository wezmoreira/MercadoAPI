package com.github.wezmoreira.site.controller;

import com.github.wezmoreira.site.dto.request.RequestClienteCartoesDTO;
import com.github.wezmoreira.site.dto.request.atualizacao.RequestAtualizaClienteDTO;
import com.github.wezmoreira.site.dto.request.RequestClienteDTO;
import com.github.wezmoreira.site.dto.response.ResponseClienteCartoesDTO;
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
@RequestMapping("/api/cliente")
public class ControllerCliente {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ServiceCliente serviceCliente;

    @Autowired
    ServiceClienteCartao serviceClienteCartao;

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

    @PutMapping("/{cpf}")
    public  ResponseEntity<Void> update(@RequestBody @Valid RequestAtualizaClienteDTO requestClienteDTO, @PathVariable String cpf) {
        serviceCliente.atualizar(requestClienteDTO, cpf);
        return ResponseEntity.noContent().build();
    }






















/*

// MUDAR O CONTROLLER E VER SE É PROBLEMA DE ROTA

    @GetMapping("/{cpf}/cartoes/")
    public ResponseEntity<ResponseClienteCartoesDTO> getCartao(@PathVariable String cpf) {
        ResponseClienteCartoesDTO responseClienteCartoesDTO = serviceClienteCartao.get(cpf);
        return ResponseEntity.ok(responseClienteCartoesDTO);
    }

    @PostMapping("/{cpf}/cartoes/")
    public ResponseEntity<ResponseClienteCartoesDTO> postCartao
            (@RequestBody @Valid RequestClienteCartoesDTO requestClienteCartoesDTO,
             @PathVariable String cpf, UriComponentsBuilder uriComponentsBuilder) {
        System.out.println("INICIO PORRA");
        log.info("O valor do cpf do cliente é : " + cpf);
        log.info("O valor do request é : " + requestClienteCartoesDTO);
        ResponseClienteCartoesDTO responseClienteCartoesDTO = serviceClienteCartao.post(requestClienteCartoesDTO, cpf);
        URI uri = uriComponentsBuilder.path("/api/cliente/{cpf}/cartoes/{id}").buildAndExpand(cpf, responseClienteCartoesDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(responseClienteCartoesDTO);
    }

    @PutMapping("/{cpf}/cartoes/{id}")
    public  ResponseEntity<Void> updateCartao(@RequestBody @Valid RequestClienteCartoesDTO requestClienteCartoesDTO, @PathVariable Long id) {
        serviceClienteCartao.atualizar(requestClienteCartoesDTO, id);
        return ResponseEntity.noContent().build();
    }


 */


}
