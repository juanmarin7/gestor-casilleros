package com.proyecto.gestorcasilleros.repository;

import com.proyecto.gestorcasilleros.model.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository {
    private final JdbcTemplate jdbcTemplate;

    public UsuarioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //se crean las filas acordes a la bd
    private final RowMapper<Usuario> usuarioRowMapper = (rs, rowNum) ->
            new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nombre"),
                    rs.getString("email"),
                    rs.getString("contraseña")
            );

    //peticion de prueba para validar conexion a la bd, trae los usuarios
    public List<Usuario> obtenerTodos() {
        String sql = "SELECT * FROM Usuario";
        return jdbcTemplate.query(sql, usuarioRowMapper);
    }

    //guarda el usuario en la bd, y los datos del usuario se sostienen en el modelo
    public int guardar(Usuario usuario) {
        try {
            String sql = "INSERT INTO Usuario ( nombre, email, contraseña) VALUES (?, ?, ?)";
            return jdbcTemplate.update(sql, usuario.getNombre(), usuario.getEmail(), usuario.getContrasena());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //esta implementado para actualizar , de momento no se esta usando
    public int actualizar(Usuario usuario) {
        try {
            String sql = "UPDATE Usuario SET nombre = ?, email = ?, contraseña = ? WHERE id_usuario = ?";
            return jdbcTemplate.update(sql, usuario.getNombre(), usuario.getEmail(), usuario.getContrasena(), usuario.getIdUsuario());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    //se usa para logear el usuario en el aplicativo validando que los datos sean correctos
    public Optional<Usuario> loginUsuario(String email, String contrasena) {
        try {
            String sql = "SELECT * FROM Usuario WHERE email = ? AND contraseña = ?";
            Usuario usuario = jdbcTemplate.queryForObject(sql, usuarioRowMapper, email, contrasena);
            return Optional.ofNullable(usuario);
        } catch (Exception e) {
            return Optional.empty(); // Si no hay coincidencias, devuelve vacío
        }
    }

}
