package com.github.wezmoreira.site.service;

import com.github.wezmoreira.site.dto.request.RequestItemDTO;
import com.github.wezmoreira.site.dto.response.ResponseItemDTO;
import com.github.wezmoreira.site.entities.Item;
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
public class ServiceItem {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    RepositoryItem repositoryItem;

    @Autowired
    CodigoSku codigoSku;


    public List<ResponseItemDTO> get() {
        List<Item> items = repositoryItem.findAll();
        return items.stream().map(item -> modelMapper.map
                (item, ResponseItemDTO.class)).collect(Collectors.toList());
    }


    public ResponseItemDTO get(Long id) {
        Item item = repositoryItem.findById(id)
                .orElseThrow(ItemNaoEncontradoException::new);
        return modelMapper.map(item, ResponseItemDTO.class);
    }

    public ResponseItemDTO post(RequestItemDTO requestItemDTO) {
        Item item = modelMapper.map(requestItemDTO, Item.class);
        item.setSkuid(codigoSku.skuId());
        Item itemSalvo = repositoryItem.save(item);
        return modelMapper.map(itemSalvo, ResponseItemDTO.class);
    }

    public void atualizar(RequestItemDTO requestItemDTO, Long id) {
        Item item = repositoryItem.findById(id)
                .orElseThrow(ItemNaoEncontradoException::new);
        modelMapper.map(requestItemDTO, item);
        repositoryItem.save(item);
    }
}
