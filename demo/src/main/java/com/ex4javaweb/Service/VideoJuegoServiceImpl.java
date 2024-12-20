package com.ex4javaweb.Service;

import com.ex4javaweb.Entity.Videojuego;
import com.ex4javaweb.Repository.VideojuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VideoJuegoServiceImpl implements VideoJuegoService {

    @Autowired
    private VideojuegoRepository videojuegoRepository;

    @Override
    public Videojuego guardarVideojuego(Videojuego videojuego) {
        return videojuegoRepository.save(videojuego);
    }

    @Override
    public Videojuego buscarPorId(Integer id) {
        Optional<Videojuego> videojuego = videojuegoRepository.findById(id);
        return videojuego.orElse(null);
    }

    @Override
    public void eliminarVideojuego(Integer id) {
        videojuegoRepository.deleteById(id);
    }

    @Override
    public List<Videojuego> listarTodos() {
        return videojuegoRepository.findAll();
    }

    @Override
    public Videojuego buscarPorNombre(String nombre) {
        return videojuegoRepository.findByNombre(nombre);
    }

    @Override
    public Videojuego buscarPorGenero(String genero) {
        return videojuegoRepository.findByGenero(genero);
    }

    @Override
    public Videojuego buscarPorDesarrollador(String desarrollador) {
        return videojuegoRepository.findByDesarrollador(desarrollador);
    }

    @Override
    public Videojuego buscarPorPlataforma(String plataforma) {
        return videojuegoRepository.findByPlataforma(plataforma);
    }
}
