package ar.com.dperalta.amigoinvisible.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_usuario;
    private String email;
    private String nombre;
    private String apellido;
    private String apodo;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<UsuarioSorteo> usuarioSorteos;

    public long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public List<UsuarioSorteo> getUsuarioSorteos() {
        return usuarioSorteos;
    }

    public void setUsuarioSorteos(List<UsuarioSorteo> usuarioSorteos) {
        this.usuarioSorteos = usuarioSorteos;
    }

    /*
    public Usuario(String apodo, String apellido, String nombre, String email) {
        this.apodo = apodo;
        this.apellido = apellido;
        this.nombre = nombre;
        this.email = email;
    }

    public Usuario() {
    }

     */
}