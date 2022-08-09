package com.github.wezmoreira.avaliacao.repositories;

import com.github.wezmoreira.avaliacao.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RepositoryCliente_cartoes extends JpaRepository<Item, Long> {

}
