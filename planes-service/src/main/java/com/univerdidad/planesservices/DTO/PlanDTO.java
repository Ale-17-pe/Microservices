package com.univerdidad.planesservices.DTO;

import lombok.*;

import java.math.BigDecimal;

@Setter @Getter @Builder
@NoArgsConstructor @AllArgsConstructor
public class PlanDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer duracionDias;
    private Boolean activo;
}
