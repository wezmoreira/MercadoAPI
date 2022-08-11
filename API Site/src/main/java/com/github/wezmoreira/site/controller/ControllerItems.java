package com.github.wezmoreira.site.controller;

import com.github.wezmoreira.site.dto.request.RequestItemsDTO;
import com.github.wezmoreira.site.dto.response.ResponseItemsDTO;
import com.github.wezmoreira.site.service.ServiceClienteCartao;
import com.github.wezmoreira.site.service.ServiceItems;
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
@RequestMapping("/api/item")
public class ControllerItems {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ServiceItems serviceItems;

    @Autowired
    ServiceClienteCartao serviceClienteCartao;

    @GetMapping
    public ResponseEntity<List<ResponseItemsDTO>> get() {
        List<ResponseItemsDTO> responseItemDTOS = serviceItems.get();
        return ResponseEntity.ok(responseItemDTOS);
    }

    @PostMapping
    public ResponseEntity<ResponseItemsDTO> post
            (@RequestBody @Valid RequestItemsDTO requestItemDTO, UriComponentsBuilder uriComponentsBuilder) {
        ResponseItemsDTO responseItemDTO = serviceItems.post(requestItemDTO);
        URI uri = uriComponentsBuilder.path("/api/item/{id}").buildAndExpand(responseItemDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(responseItemDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseItemsDTO> get(@PathVariable Long id) {
        ResponseItemsDTO responseItemDTO = serviceItems.get(id);
        return ResponseEntity.ok(responseItemDTO);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Void> update(@RequestBody @Valid RequestItemsDTO requestItemDTO, @PathVariable Long id) {
        serviceItems.atualizar(requestItemDTO, id);
        return ResponseEntity.noContent().build();
    }

}
