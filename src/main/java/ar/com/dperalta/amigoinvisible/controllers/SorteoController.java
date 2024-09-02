package ar.com.dperalta.amigoinvisible.controllers;

import ar.com.dperalta.amigoinvisible.entities.Sorteo;
import ar.com.dperalta.amigoinvisible.services.AppService;
import ar.com.dperalta.amigoinvisible.services.SorteoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/sorteo")
public class SorteoController {
    @Autowired
    private SorteoService sorteoService;
    @Autowired
    private AppService appService;

    @GetMapping(path = "/buscar")
    public Sorteo buscarSorteoPorCodigo(@RequestParam String codigoSorteo){
        return sorteoService.existeSorteoPorCodigo(codigoSorteo);
    }

    @GetMapping(path = "/renviaremails")
    public String renviarEmails(@RequestParam Long sorteoId){
        return appService.resendEmails(sorteoId);
    }
}