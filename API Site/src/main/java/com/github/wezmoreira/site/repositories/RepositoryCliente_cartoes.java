package com.github.wezmoreira.site.repositories;

import com.github.wezmoreira.site.entities.Cliente;
import com.github.wezmoreira.site.entities.ClienteCartoes;
import com.github.wezmoreira.site.entities.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RepositoryCliente_cartoes extends JpaRepository<ClienteCartoes, Long> {

}
