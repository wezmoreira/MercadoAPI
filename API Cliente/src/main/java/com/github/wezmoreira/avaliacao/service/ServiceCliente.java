package com.github.wezmoreira.avaliacao.service;

import com.github.wezmoreira.avaliacao.dto.request.RequestClienteDTO;
import com.github.wezmoreira.avaliacao.dto.response.ResponseClienteDTO;
import com.github.wezmoreira.avaliacao.entities.Cliente;
import com.github.wezmoreira.avaliacao.exceptions.ClienteNaoEncontradoException;
import com.github.wezmoreira.avaliacao.repositories.RepositoryCliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ServiceCliente {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    RepositoryCliente repositoryCliente;


    public Page<ResponseClienteDTO> get(String cpf, Pageable pageable) {
        Page<Cliente> clientes;
        if (cpf == null) {
            clientes = repositoryCliente.findAll(pageable);
        } else {
            clientes = repositoryCliente.findByCpf(cpf, pageable);
        }
        return clientes.map(clienteEntity -> modelMapper.map
                (clienteEntity, ResponseClienteDTO.class));
    }

    public ResponseClienteDTO get(String cpf) {
        Cliente cliente = repositoryCliente.findById(cpf)
                .orElseThrow(ClienteNaoEncontradoException::new);
        return modelMapper.map(cliente, ResponseClienteDTO.class);
    }

    public ResponseClienteDTO post(RequestClienteDTO requestClienteDTO) {
        Cliente cliente = modelMapper.map(requestClienteDTO, Cliente.class);
        Cliente clienteSalvo = repositoryCliente.save(cliente);
        return modelMapper.map(clienteSalvo, ResponseClienteDTO.class);
    }

    public void atualizar(RequestClienteDTO requestClienteDTO, String cpf) {
        Cliente cliente = repositoryCliente.findById(cpf).orElseThrow(ClienteNaoEncontradoException::new);
        modelMapper.map(requestClienteDTO, cliente);
        repositoryCliente.save(cliente);
    }
}