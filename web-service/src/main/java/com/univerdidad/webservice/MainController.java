package com.univerdidad.webservice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Controller
public class MainController {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String API_GATEWAY = "http://localhost:8080";

    @GetMapping("/")
    public String home(Model model) {
        // Obtener planes desde el gateway
        Object[] planes = restTemplate.getForObject(API_GATEWAY + "/api/planes", Object[].class);
        model.addAttribute("planes", Arrays.asList(planes));
        return "index";
    }

    @GetMapping("/usuarios")
    public String listaUsuarios(Model model) {
        Object[] usuarios = restTemplate.getForObject(API_GATEWAY + "/api/usuarios", Object[].class);
        model.addAttribute("usuarios", Arrays.asList(usuarios));
        return "usuarios/lista";
    }

    @GetMapping("/pagos/nuevo")
    public String formularioPago(Model model) {
        // Cargar datos para el formulario
        Object[] usuarios = restTemplate.getForObject(API_GATEWAY + "/api/usuarios", Object[].class);
        Object[] planes = restTemplate.getForObject(API_GATEWAY + "/api/planes", Object[].class);
        model.addAttribute("usuarios", Arrays.asList(usuarios));
        model.addAttribute("planes", Arrays.asList(planes));
        return "pagos/form";
    }
}