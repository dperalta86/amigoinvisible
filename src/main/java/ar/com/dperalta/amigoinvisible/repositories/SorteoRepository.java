package ar.com.dperalta.amigoinvisible.repositories;

import ar.com.dperalta.amigoinvisible.entities.Sorteo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SorteoRepository extends JpaRepository<Sorteo, Long> {
    Optional<Sorteo> findByCodigoSorteo(String codigoSorteo);
}
