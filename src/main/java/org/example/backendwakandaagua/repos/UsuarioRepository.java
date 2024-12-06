package org.example.backendwakandaagua.repos;

import org.example.backendwakandaagua.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Encuentra un usuario por correo electrónico
    Optional<Usuario> findByEmail(String email);
}
