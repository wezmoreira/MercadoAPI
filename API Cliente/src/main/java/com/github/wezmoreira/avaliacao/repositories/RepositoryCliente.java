package com.github.wezmoreira.avaliacao.repositories;

import com.github.wezmoreira.avaliacao.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RepositoryCliente extends JpaRepository<Cliente, String> {
    Page<Cliente> findByCpf(String Cpf, Pageable pageable);
}
