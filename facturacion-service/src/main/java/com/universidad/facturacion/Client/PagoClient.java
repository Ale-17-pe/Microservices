package com.universidad.facturacion.Client;

import com.universidad.facturacion.Dto.PagoDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class PagoClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String URL = "http://localhost:8899/api/pagos";

    public PagoDto obtenerPago(Long pagoId) {
        return restTemplate.getForObject(URL + "/" + pagoId, PagoDto.class);

    }
}