package com.universidad.facturacion.Dto;

import lombok.*;

import java.math.BigDecimal;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class PlanDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer duracionDias;
}
