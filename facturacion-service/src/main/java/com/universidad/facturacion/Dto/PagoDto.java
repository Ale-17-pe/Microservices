package com.universidad.facturacion.Dto;

import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class PagoDto {
    private Long id;
    private Long usuarioId;
    private Long planId;
    private BigDecimal monto;
    private String estado;
}

