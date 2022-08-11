package com.github.wezmoreira.site.service;

import com.github.wezmoreira.site.dto.request.RequestClienteCartoesDTO;
import com.github.wezmoreira.site.dto.response.ResponseClienteCartoesDTO;
import com.github.wezmoreira.site.entities.Cliente;
import com.github.wezmoreira.site.entities.ClienteCartoes;
import com.github.wezmoreira.site.exceptions.CartaoNaoEncontradoException;
import com.github.wezmoreira.site.exceptions.ClienteNaoEncontradoException;
import com.github.wezmoreira.site.repositories.RepositoryCliente;
import com.github.wezmoreira.site.repositories.RepositoryCliente_cartoes;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ServiceClienteCartao {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    RepositoryCliente_cartoes repositoryCliente_cartoes;

    @Autowired
    RepositoryCliente repositoryCliente;


    public List<ResponseClienteCartoesDTO> get(String cpf) {
        Cliente cliente = repositoryCliente.findById(cpf)
                .orElseThrow(CartaoNaoEncontradoException::new);
        return cliente.getCliente_cartoes().stream().map(cartao -> modelMapper
                .map(cartao, ResponseClienteCartoesDTO.class)).collect(Collectors.toList());
    }

    public ResponseClienteCartoesDTO postCartao(RequestClienteCartoesDTO requestClienteCartoesDTO, String cpf) {
        log.info("O valor do cpf do cliente é : " + cpf);
        log.info("O valor do request é : " + requestClienteCartoesDTO);
        Cliente cliente = repositoryCliente.findById(cpf).orElseThrow(ClienteNaoEncontradoException::new);
        log.info("O valor do id do cliente é : " + cliente);
        ClienteCartoes clienteCartoes = modelMapper.map(requestClienteCartoesDTO, ClienteCartoes.class);
        List<ClienteCartoes> cartoesList = cliente.getCliente_cartoes();
        cartoesList.add(clienteCartoes);
        cliente.setCliente_cartoes(cartoesList);
        ClienteCartoes cartoesSalvo = repositoryCliente_cartoes.save(clienteCartoes);
        return modelMapper.map(cartoesSalvo, ResponseClienteCartoesDTO.class);
    }

    public void atualizar(RequestClienteCartoesDTO requestClienteCartoesDTO, Long id) {
        ClienteCartoes clienteCartoes = repositoryCliente_cartoes.findById(id)
                .orElseThrow(CartaoNaoEncontradoException::new);
        modelMapper.map(requestClienteCartoesDTO, clienteCartoes);
        repositoryCliente_cartoes.save(clienteCartoes);
    }
}
