package com.univerdidad.pagosservice.Client;

import com.univerdidad.pagosservice.Dto.PlanDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PlanClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String PLANES_URL = "http://localhost:8902/api/planes";

    public PlanDto obtenerPlan(Long planId) {
        return restTemplate.getForObject(PLANES_URL + "/" + planId, PlanDto.class);
    }
}
