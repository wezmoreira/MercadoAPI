package com.github.wezmoreira.site.service;

import com.github.wezmoreira.site.dto.request.RequestItemsDTO;
import com.github.wezmoreira.site.dto.response.ResponseItemsDTO;
import com.github.wezmoreira.site.entities.Items;
import com.github.wezmoreira.site.exceptions.ItemNaoEncontradoException;
import com.github.wezmoreira.site.repositories.RepositoryItem;
import com.github.wezmoreira.site.util.CodigoSku;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ServiceItems {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    RepositoryItem repositoryItem;

    @Autowired
    CodigoSku codigoSku;


    public List<ResponseItemsDTO> get() {
        List<Items> items = repositoryItem.findAll();
        return items.stream().map(item -> modelMapper.map
                (item, ResponseItemsDTO.class)).collect(Collectors.toList());
    }


    public ResponseItemsDTO get(Long id) {
        Items item = repositoryItem.findById(id)
                .orElseThrow(ItemNaoEncontradoException::new);
        return modelMapper.map(item, ResponseItemsDTO.class);
    }

    public ResponseItemsDTO post(RequestItemsDTO requestItemDTO) {
        Items item = modelMapper.map(requestItemDTO, Items.class);
        item.setSkuid(codigoSku.skuId());
        Items itemSalvo = repositoryItem.save(item);
        return modelMapper.map(itemSalvo, ResponseItemsDTO.class);
    }

    public void atualizar(RequestItemsDTO requestItemDTO, Long id) {
        Items item = repositoryItem.findById(id)
                .orElseThrow(ItemNaoEncontradoException::new);
        modelMapper.map(requestItemDTO, item);
        repositoryItem.save(item);
    }
}
