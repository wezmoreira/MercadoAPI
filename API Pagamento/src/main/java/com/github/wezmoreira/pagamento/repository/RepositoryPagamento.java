package com.github.wezmoreira.pagamento.repository;

import com.github.wezmoreira.pagamento.entities.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryPagamento extends JpaRepository<Pagamento, Long> {
}
