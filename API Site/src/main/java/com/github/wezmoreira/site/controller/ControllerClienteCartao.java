package com.github.wezmoreira.site.controller;

import com.github.wezmoreira.site.dto.request.RequestClienteCartoesDTO;
import com.github.wezmoreira.site.dto.response.ResponseClienteCartoesDTO;
import com.github.wezmoreira.site.service.ServiceClienteCartao;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
public class ControllerClienteCartao {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ServiceClienteCartao serviceClienteCartao;

    //TA FUNCIONANDO
    @GetMapping("/api/cliente/{cpf}/cartoes")
    public ResponseEntity<List<ResponseClienteCartoesDTO>> getCartao(@PathVariable String cpf) {
        List<ResponseClienteCartoesDTO> responseClienteCartoesDTO = serviceClienteCartao.get(cpf);
        return ResponseEntity.ok(responseClienteCartoesDTO);
    }

    //TA FUNCIONANDO
    @PostMapping("/api/cliente/{cpf}/cartoes")
    public ResponseEntity<ResponseClienteCartoesDTO> postCartao
            (@RequestBody @Valid RequestClienteCartoesDTO requestClienteCartoesDTO,
             @PathVariable String cpf, UriComponentsBuilder uriComponentsBuilder) {
        ResponseClienteCartoesDTO responseClienteCartoesDTO = serviceClienteCartao.postCartao(requestClienteCartoesDTO, cpf);
        URI uri = uriComponentsBuilder.path("/api/cliente/{cpf}/cartoes/{id}").buildAndExpand(cpf, responseClienteCartoesDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(responseClienteCartoesDTO);
    }

    //TA FUNCIONANDO
    @PutMapping("/api/cliente/{cpf}/cartoes/{id}")
    public  ResponseEntity<Void> updateCartao(@RequestBody @Valid RequestClienteCartoesDTO requestClienteCartoesDTO, @PathVariable Long id) {
        serviceClienteCartao.atualizar(requestClienteCartoesDTO, id);
        return ResponseEntity.noContent().build();
    }





}
