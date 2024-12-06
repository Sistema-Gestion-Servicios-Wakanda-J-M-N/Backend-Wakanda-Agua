package org.example.backendwakandaagua.service;

import org.example.backendwakandaagua.domain.usuario.Credenciales;
import org.example.backendwakandaagua.domain.usuario.Usuario;
import org.example.backendwakandaagua.model.usuario.UsuarioDTO;
import org.example.backendwakandaagua.repos.CredencialesRepository;
import org.example.backendwakandaagua.repos.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CredencialesRepository credencialesRepository;

    // Convertir entidad Usuario a DTO
    private UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setApellido(usuario.getApellidos());
        dto.setEmail(usuario.getEmail());
        dto.setRoles(usuario.getRoles());
        return dto;
    }

    // Convertir DTO a entidad Usuario
    private Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNombre(dto.getNombre());
        usuario.setApellidos(dto.getApellido());
        usuario.setEmail(dto.getEmail());
        usuario.setRoles(dto.getRoles());
        return usuario;
    }

    // CRUD: Obtener todos los usuarios
    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    // CRUD: Obtener un usuario por ID
    public UsuarioDTO get(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        return toDTO(usuario);
    }

    // CRUD: Crear un nuevo usuario y sus credenciales
    public Long create(UsuarioDTO dto) {
        Usuario usuario = toEntity(dto);
        usuario = usuarioRepository.save(usuario);

        Credenciales credenciales = new Credenciales();
        credenciales.setCorreo(usuario.getEmail());
        credenciales.setPassword("defaultPassword123"); // Contraseña predeterminada o generada
        credenciales.setUsuario(usuario);
        credencialesRepository.save(credenciales);

        return usuario.getId();
    }

    // CRUD: Actualizar un usuario y sus credenciales
    public void update(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        usuario.setNombre(dto.getNombre());
        usuario.setApellidos(dto.getApellido());
        usuario.setEmail(dto.getEmail());
        usuario.setRoles(dto.getRoles());
        usuarioRepository.save(usuario);

        Credenciales credenciales = credencialesRepository.findByUsuarioId(id)
                .orElseThrow(() -> new RuntimeException("Credenciales no encontradas para el usuario con ID: " + id));
        credenciales.setCorreo(usuario.getEmail());
        credencialesRepository.save(credenciales);
    }

    // CRUD: Eliminar un usuario y sus credenciales
    public void delete(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
        credencialesRepository.deleteByUsuarioId(id);
        usuarioRepository.deleteById(id);
    }
}
