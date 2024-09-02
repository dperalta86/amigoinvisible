package ar.com.dperalta.amigoinvisible.repositories;

import ar.com.dperalta.amigoinvisible.entities.Sorteo;
import ar.com.dperalta.amigoinvisible.entities.UsuarioSorteo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioSorteoRepository extends JpaRepository<UsuarioSorteo, Long> {
    List<UsuarioSorteo> findBySorteo(Sorteo sorteoEncontrado);
}
