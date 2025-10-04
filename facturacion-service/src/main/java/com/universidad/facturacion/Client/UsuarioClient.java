package com.universidad.facturacion.Client;

import com.universidad.facturacion.Dto.UsuarioDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UsuarioClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String URL = "http://localhost:8899/api/usuarios";

    public UsuarioDto obtenerUsuario(Long usuarioId) {
        return restTemplate.getForObject(URL + "/" + usuarioId, UsuarioDto.class);
    }
}
