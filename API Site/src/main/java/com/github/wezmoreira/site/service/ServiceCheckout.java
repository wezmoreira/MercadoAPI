package com.github.wezmoreira.site.service;

import com.github.wezmoreira.site.dto.request.RequestClienteDTO;
import com.github.wezmoreira.site.dto.response.ResponseClienteDTO;
import com.github.wezmoreira.site.entities.Cliente;
import com.github.wezmoreira.site.exceptions.ClienteCadastradoException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceCheckout {

    @Autowired
    ModelMapper modelMapper;


/*

    public ResponseClienteDTO post(RequestClienteDTO requestClienteDTO) {
        Cliente cliente = modelMapper.map(requestClienteDTO, Cliente.class);
        Cliente clienteSalvo = repositoryCliente.save(cliente);
        return modelMapper.map(clienteSalvo, ResponseClienteDTO.class);
    }

 */
}
