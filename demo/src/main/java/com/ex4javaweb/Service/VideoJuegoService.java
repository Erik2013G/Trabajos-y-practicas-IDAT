package com.ex4javaweb.Service;

import com.ex4javaweb.Entity.Videojuego;
import java.util.List;

public interface VideoJuegoService {
    Videojuego guardarVideojuego(Videojuego videojuego);
    Videojuego buscarPorId(Integer id);
    void eliminarVideojuego(Integer id);
    List<Videojuego> listarTodos();
    Videojuego buscarPorNombre(String nombre);
    Videojuego buscarPorGenero(String genero);
    Videojuego buscarPorDesarrollador(String desarrollador);
    Videojuego buscarPorPlataforma(String plataforma);
}
