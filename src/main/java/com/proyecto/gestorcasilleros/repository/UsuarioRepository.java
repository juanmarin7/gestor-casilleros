package com.proyecto.gestorcasilleros.repository;

import com.proyecto.gestorcasilleros.model.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UsuarioRepository {
    private final JdbcTemplate jdbcTemplate;

    public UsuarioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Mapeador de filas
    private final RowMapper<Usuario> usuarioRowMapper = (rs, rowNum) ->
            new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nombre"),
                    rs.getString("email"),
                    rs.getString("contraseña")
            );

    // Obtener todos los usuarios
    public List<Usuario> obtenerTodos() {
        String sql = "SELECT * FROM Usuario";
        return jdbcTemplate.query(sql, usuarioRowMapper);
    }

    // Guardar un usuario
    public int guardar(Usuario usuario) {
        try {
            String sql = "INSERT INTO Usuario (nombre, email, contraseña) VALUES (?, ?, ?)";
            return jdbcTemplate.update(sql, usuario.getNombre(), usuario.getEmail(), usuario.getContrasena());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Actualizar un usuario
    public int actualizar(Usuario usuario) {
        try {
            String sql = "UPDATE Usuario SET nombre = ?, email = ?, contraseña = ? WHERE id_usuario = ?";
            return jdbcTemplate.update(sql, usuario.getNombre(), usuario.getEmail(), usuario.getContrasena(), usuario.getIdUsuario());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Eliminar un usuario
    public int eliminar(int idUsuario) {
        try {
            String sql = "DELETE FROM Usuario WHERE id_usuario = ?";
            return jdbcTemplate.update(sql, idUsuario);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public boolean probarConexion() {
        try {
            jdbcTemplate.execute("SELECT 1");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
