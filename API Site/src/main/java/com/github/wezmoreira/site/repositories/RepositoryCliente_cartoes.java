package com.github.wezmoreira.site.repositories;

import com.github.wezmoreira.site.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RepositoryCliente_cartoes extends JpaRepository<Item, Long> {

}
