package com.proyecto.gestorcasilleros.controller;

import com.proyecto.gestorcasilleros.model.Usuario;
import com.proyecto.gestorcasilleros.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

//se usa para que el navegador permita recibir las peticiones entrantes
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    //se usa para probar la conexion inicial a la bd al desplegar el proyecto
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodos() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }
    //se realiza el registro o se devuelvo un error al registrar
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.guardar(usuario) ?
                ResponseEntity.ok("Usuario registrado correctamente") :
                ResponseEntity.badRequest().body("Error al registrar usuario");
    }
    //se comparan los datos recibidos con los de la bd para mostrar un mensaje
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        System.out.println("logeado");
        Optional<Usuario> usuarioAutenticado = usuarioService.login(usuario.getEmail(), usuario.getContrasena());

        return usuarioAutenticado.isPresent() ?
                ResponseEntity.ok("Inicio de sesion exitoso") :
                ResponseEntity.status(401).body("Credenciales incorrectas");
    }
    //en el momento no se esta usando pero servira para actualizar datos de un usuario
    @PutMapping
    public ResponseEntity<String> actualizar(@RequestBody Usuario usuario) {
        return usuarioService.actualizar(usuario) ?
                ResponseEntity.ok("Usuario actualizado correctamente") :
                ResponseEntity.badRequest().body("Error al actualizar usuario");
    }
    //prueba de conexion a la bd
    @GetMapping("/testConexion")
    public ResponseEntity<String> probarConexion() {
        try {
            System.out.println("Iniciando testConexion");
            // Ejecuta una consulta para probar la conexión
            usuarioService.obtenerTodos();
            return ResponseEntity.ok("hay conexion con la bd");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("error, no hay conexion con la bd" + e.getMessage());
        }
    }
}
