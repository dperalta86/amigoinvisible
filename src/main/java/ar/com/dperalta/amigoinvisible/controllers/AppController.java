package ar.com.dperalta.amigoinvisible.controllers;

import ar.com.dperalta.amigoinvisible.services.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/app")
public class AppController {
    @Autowired
    private AppService appService;

    @PostMapping
    public String parsearJson (@RequestBody String jsonString){
        long idSorteo = appService.cargarJsonASorteo(jsonString);

        return "ID Sorteo: " + idSorteo;
    }

}


