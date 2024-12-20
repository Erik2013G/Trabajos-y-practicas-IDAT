package com.examen3java.desarrolloweb.service;

import com.examen3java.desarrolloweb.Entity.Pedidos;
import com.examen3java.desarrolloweb.repository.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidosServiceImpl implements PedidosService {

    @Autowired
    private PedidosRepository proveedoresRepository;

    @Override
    public List<Pedidos> findAll() {
        return proveedoresRepository.findAll();
    }

    @Override
    public Pedidos findById(Long id) {
        return proveedoresRepository.findById(id).orElse(null);
    }

    @Override
    public Pedidos save(Pedidos proveedor) {
        return proveedoresRepository.save(proveedor);
    }

    @Override
    public void deleteById(Long id) {
        proveedoresRepository.deleteById(id);
    }

    @Override
    public List<Pedidos> buscarPorClienteOProducto(String buscar) {
        return proveedoresRepository.findByClienteContainingOrProductoContaining(buscar, buscar);
    }
}
