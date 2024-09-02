package ar.com.dperalta.amigoinvisible.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    @GetMapping("/holamundo")
    public String holaMundo() {
        return "Hola Mundo!";
    }
}
