package ar.com.dperalta.amigoinvisible.services;

import ar.com.dperalta.amigoinvisible.entities.Sorteo;
import ar.com.dperalta.amigoinvisible.repositories.SorteoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SorteoService {
    @Autowired
    SorteoRepository sorteoRepository;

    public Sorteo existeSorteoPorCodigo(String codigoSorteo) {
        Optional<Sorteo> sorteo = sorteoRepository.findByCodigoSorteo(codigoSorteo);
        return sorteo.orElse(null);
    }
}
