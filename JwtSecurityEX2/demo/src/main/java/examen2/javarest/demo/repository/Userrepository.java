package examen2.javarest.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import examen2.javarest.demo.entity.User;

public interface Userrepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    // Buscar usuarios por rol
    List<User> findByRole(String role);

    // Contar usuarios por rol
    long countByRole(String role);

    // Eliminar usuarios por nombre de usuario
    void deleteByUsername(String username);
}
