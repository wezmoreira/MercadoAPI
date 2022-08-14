package com.github.wezmoreira.site.repositories;

import com.github.wezmoreira.site.entities.Items;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RepositoryItem extends JpaRepository<Items, Long> {
    Page<Items> findById(Long id, Pageable pageable);

    Items findBySkuid(String skuid);
}
