package com.github.wezmoreira.site.controller;

import com.github.wezmoreira.site.dto.request.RequestClienteDTO;
import com.github.wezmoreira.site.dto.request.RequestItemDTO;
import com.github.wezmoreira.site.dto.request.atualizacao.RequestAtualizaClienteDTO;
import com.github.wezmoreira.site.dto.response.ResponseClienteDTO;
import com.github.wezmoreira.site.dto.response.ResponseItemDTO;
import com.github.wezmoreira.site.service.ServiceCliente;
import com.github.wezmoreira.site.service.ServiceClienteCartao;
import com.github.wezmoreira.site.service.ServiceItem;
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
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/item")
public class ControllerItem {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ServiceItem serviceItem;

    @Autowired
    ServiceClienteCartao serviceClienteCartao;

    @GetMapping
    public ResponseEntity<List<ResponseItemDTO>> get() {
        List<ResponseItemDTO> responseItemDTOS = serviceItem.get();
        return ResponseEntity.ok(responseItemDTOS);
    }

    @PostMapping
    public ResponseEntity<ResponseItemDTO> post
            (@RequestBody @Valid RequestItemDTO requestItemDTO, UriComponentsBuilder uriComponentsBuilder) {
        ResponseItemDTO responseItemDTO = serviceItem.post(requestItemDTO);
        URI uri = uriComponentsBuilder.path("/api/item/{id}").buildAndExpand(responseItemDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(responseItemDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseItemDTO> get(@PathVariable Long id) {
        ResponseItemDTO responseItemDTO = serviceItem.get(id);
        return ResponseEntity.ok(responseItemDTO);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Void> update(@RequestBody @Valid RequestItemDTO requestItemDTO, @PathVariable Long id) {
        serviceItem.atualizar(requestItemDTO, id);
        return ResponseEntity.noContent().build();
    }

}
