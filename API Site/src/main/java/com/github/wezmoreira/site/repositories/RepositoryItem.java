package com.github.wezmoreira.site.repositories;

import com.github.wezmoreira.site.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RepositoryItem extends JpaRepository<Item, Long> {

}
