package com.universidad.facturacion.Client;

import com.universidad.facturacion.Dto.PlanDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PlanClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String URL = "http://localhost:8899/api/planes";

    public PlanDto obtenerPlan(Long planId) {
        return restTemplate.getForObject(URL + "/" + planId, PlanDto.class);
    }
}
