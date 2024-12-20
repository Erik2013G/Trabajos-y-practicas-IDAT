package com.examen3java.desarrolloweb.repository;

import com.examen3java.desarrolloweb.Entity.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidosRepository extends JpaRepository<Pedidos, Long> {
    List<Pedidos> findByClienteContainingOrProductoContaining(String cliente, String producto);
}