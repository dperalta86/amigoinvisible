package ar.com.dperalta.amigoinvisible.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Sorteo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_sorteo;
    private String codigoSorteo;
    private LocalDate fechaSorteo;
    private String mensaje;

    @OneToMany(mappedBy = "sorteo", cascade = CascadeType.ALL)
    private List<UsuarioSorteo> usuarioSorteos;

    public String getCodigoSorteo() {
        return codigoSorteo;
    }

    public void setCodigoSorteo(String codigoSorteo) {
        this.codigoSorteo = codigoSorteo;
    }

    public LocalDate getFechaSorteo() {
        return fechaSorteo;
    }

    public void setFechaSorteo(LocalDate fechaSorteo) {
        this.fechaSorteo = fechaSorteo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<UsuarioSorteo> getUsuarioSorteos() {
        return usuarioSorteos;
    }

    public void setUsuarioSorteos(List<UsuarioSorteo> usuarioSorteos) {
        this.usuarioSorteos = usuarioSorteos;
    }

    public long getId_sorteo() {
        return id_sorteo;
    }

    public void setId_sorteo(long id_sorteo) {
        this.id_sorteo = id_sorteo;
    }
}
