package com.github.wezmoreira.avaliacao.repositories;

import com.github.wezmoreira.avaliacao.entities.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RepositoryPedido extends JpaRepository<Pedido, Long> {
    @Query("SELECT pedidos FROM Pedido pedidos WHERE (:cpf IS NULL OR :cpf = pedidos.cpf)")
    List<Pedido> findWithFilters(@Param("cpf") String cpf, Sort sort);
    Page<Pedido> findByCpf(String Cpf, Pageable pageable);
}
