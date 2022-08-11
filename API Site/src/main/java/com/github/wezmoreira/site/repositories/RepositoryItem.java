package com.github.wezmoreira.site.repositories;

import com.github.wezmoreira.site.entities.Cliente;
import com.github.wezmoreira.site.entities.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RepositoryItem extends JpaRepository<Item, Long> {
    Page<Item> findById(Long id, Pageable pageable);

}
