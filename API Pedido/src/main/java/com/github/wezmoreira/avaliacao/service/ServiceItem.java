package com.github.wezmoreira.avaliacao.service;

import com.github.wezmoreira.avaliacao.dto.request.atualizacao.RequestAtualizaItemDTO;
import com.github.wezmoreira.avaliacao.dto.response.ResponseItemDTO;
import com.github.wezmoreira.avaliacao.entities.Item;
import com.github.wezmoreira.avaliacao.exceptions.PedidoNaoEncontradoException;
import com.github.wezmoreira.avaliacao.repositories.RepositoryItem;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceItem {
    @Autowired
    private RepositoryItem repositoryItem;

    @Autowired
    private ModelMapper modelMapper;


    public ResponseItemDTO patch(Long id, RequestAtualizaItemDTO requestItemDTO) {
        Item item = repositoryItem.findById(id)
                .orElseThrow(PedidoNaoEncontradoException::new);
        if(requestItemDTO.getNome() != null) {
            item.setNome(requestItemDTO.getNome());
        }
        if(requestItemDTO.getValor() != null) {
            item.setValor(requestItemDTO.getValor());
        }
        if(requestItemDTO.getData_criacao() != null) {
           item.setData_criacao(requestItemDTO.getData_criacao());
        }
        if(requestItemDTO.getData_validade() != null) {
             item.setData_validade(requestItemDTO.getData_validade());
        }
        if(requestItemDTO.getDescricao() != null) {
            item.setDescricao(requestItemDTO.getDescricao());
        }
        if(requestItemDTO.getOfertas() != null) {
            item.setOfertas(requestItemDTO.getOfertas());
        }
        repositoryItem.save(item);
        return modelMapper.map(item, ResponseItemDTO.class);
    }
}
