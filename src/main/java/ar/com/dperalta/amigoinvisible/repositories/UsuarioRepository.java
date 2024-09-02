package ar.com.dperalta.amigoinvisible.repositories;

import ar.com.dperalta.amigoinvisible.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
