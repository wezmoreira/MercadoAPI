package com.github.wezmoreira.site.repositories;

import com.github.wezmoreira.site.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RepositoryCheckout extends JpaRepository<Cliente, String> {
}
