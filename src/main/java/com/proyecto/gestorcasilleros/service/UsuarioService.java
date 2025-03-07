package com.proyecto.gestorcasilleros.service;

import com.proyecto.gestorcasilleros.model.Usuario;
import com.proyecto.gestorcasilleros.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.obtenerTodos();
    }

    public boolean guardar(Usuario usuario) {
        return usuarioRepository.guardar(usuario) > 0;
    }
    public Optional<Usuario> login(String email, String contrasena) {
        return usuarioRepository.loginUsuario(email, contrasena);
    }

    public boolean actualizar(Usuario usuario) {
        return usuarioRepository.actualizar(usuario) > 0;
    }

    public boolean eliminar(int idUsuario) {
        return usuarioRepository.eliminar(idUsuario) > 0;
    }

}