package com.examen3java.desarrolloweb.service;
import com.examen3java.desarrolloweb.Entity.Pedidos;
import java.util.List;

public interface PedidosService {
    List<Pedidos> findAll();
    Pedidos findById(Long id);
    Pedidos save(Pedidos pedidos);
    void deleteById(Long id);
    List<Pedidos> buscarPorClienteOProducto(String buscar);
}
