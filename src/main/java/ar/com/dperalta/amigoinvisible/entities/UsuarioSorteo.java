package ar.com.dperalta.amigoinvisible.entities;

import jakarta.persistence.*;

@Entity
public class UsuarioSorteo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuarioSorteo;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_sorteo")
    private Sorteo sorteo;

    @ManyToOne
    @JoinColumn(name = "sorteado")
    private Usuario sorteado;

    public Long getId_usuarioSorteo() {
        return id_usuarioSorteo;
    }

    public void setId_usuarioSorteo(Long id_usuarioSorteo) {
        this.id_usuarioSorteo = id_usuarioSorteo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Sorteo getSorteo() {
        return sorteo;
    }

    public void setSorteo(Sorteo sorteo) {
        this.sorteo = sorteo;
    }

    public Usuario getSorteado() {
        return sorteado;
    }

    public void setSorteado(Usuario sorteado) {
        this.sorteado = sorteado;
    }
}

