package com.univerdidad.pagosservice.Client;

import com.univerdidad.pagosservice.Dto.UsuarioDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UsuarioClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String USUARIOS_URL ="http://localhost:8900/api/usuarios";

    public UsuarioDto obtenerUsuario(Long usuarioId) {
        return restTemplate.getForObject(USUARIOS_URL + "/" + usuarioId, UsuarioDto.class);
    }
}
