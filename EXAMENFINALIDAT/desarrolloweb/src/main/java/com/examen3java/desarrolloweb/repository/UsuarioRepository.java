package com.examen3java.desarrolloweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.examen3java.desarrolloweb.Entity.Usuario;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
} 