package com.ex4javaweb.Repository;

import com.ex4javaweb.Entity.Videojuego;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface VideojuegoRepository extends JpaRepository <Videojuego, Integer> {
    Videojuego findByNombre(String nombre);
    Videojuego findByGenero(String genero);
    Videojuego findByDesarrollador(String desarrollador);
    Videojuego findByPlataforma(String plataforma);
    void guardarvideojuego(Videojuego videojuego);
    void buscarporId (Integer id);
    void eliminarVideojuego (Integer id);
    List<Videojuego> findByTituloContainingIgnoreCaseOrGeneroContainingIgnoreCaseOrPlataformaContainingIgnoreCase(String titulo, String genero, String plataforma);
}
