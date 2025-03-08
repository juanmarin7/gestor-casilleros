package com.proyecto.gestorcasilleros.model;

import java.time.LocalDate;

//este es el modelo de datos acorde a la bd
public class Usuario {
    //Variables
    private int idUsuario;
    private String nombre;
    private String email;
    private String contrasena;

    //constructor vacio
    public Usuario() {
    }

    //constructor con parametros
    public Usuario(int idUsuario, String nombre, String email, String contrasena) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
    }

    //aca se crean los get y set para cada variable
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
