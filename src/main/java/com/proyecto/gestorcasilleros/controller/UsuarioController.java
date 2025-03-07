package com.proyecto.gestorcasilleros.controller;

import com.proyecto.gestorcasilleros.model.Usuario;
import com.proyecto.gestorcasilleros.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodos() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.guardar(usuario) ?
                ResponseEntity.ok("Usuario registrado correctamente") :
                ResponseEntity.badRequest().body("Error al registrar usuario");
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        System.out.println("logeado");
        Optional<Usuario> usuarioAutenticado = usuarioService.login(usuario.getEmail(), usuario.getContrasena());

        return usuarioAutenticado.isPresent() ?
                ResponseEntity.ok("Inicio de sesión exitoso") :
                ResponseEntity.status(401).body("Credenciales incorrectas");
    }

    @PutMapping
    public ResponseEntity<String> actualizar(@RequestBody Usuario usuario) {
        return usuarioService.actualizar(usuario) ?
                ResponseEntity.ok("Usuario actualizado correctamente") :
                ResponseEntity.badRequest().body("Error al actualizar usuario");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        return usuarioService.eliminar(id) ?
                ResponseEntity.ok("Usuario eliminado correctamente") :
                ResponseEntity.badRequest().body("Error al eliminar usuario");
    }
    @GetMapping("/testConexion")
    public ResponseEntity<String> probarConexion() {
        //logger.info("Endpoint testConexion ha sido llamado");
        try {
            System.out.println("Iniciando testConexion");
            usuarioService.obtenerTodos(); // Ejecuta una consulta para probar la conexión
            return ResponseEntity.ok("Conexión exitosa a la base de datos");
        } catch (Exception e) {
            //logger.error("Error en la conexión a la base de datos", e);
            return ResponseEntity.status(500).body("Error en la conexión a la base de datos: " + e.getMessage());
        }
    }
}
