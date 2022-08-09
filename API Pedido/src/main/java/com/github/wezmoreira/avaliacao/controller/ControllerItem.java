package com.github.wezmoreira.avaliacao.controller;

import com.github.wezmoreira.avaliacao.dto.request.atualizacao.RequestAtualizaItemDTO;
import com.github.wezmoreira.avaliacao.dto.response.ResponseItemDTO;
import com.github.wezmoreira.avaliacao.service.ServiceItem;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/item")
public class ControllerItem {

    @Autowired
    ServiceItem serviceItem;

    @Autowired
    ModelMapper modelMapper;

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseItemDTO> atualiza(@PathVariable Long id,
                                                    @Valid @RequestBody RequestAtualizaItemDTO responseItemDTO) {
        ResponseItemDTO responseItem = serviceItem.patch(id, responseItemDTO);
        return ResponseEntity.ok(responseItem);
    }

}
