package com.univerdidad.pagosservice.Dto;

import com.univerdidad.pagosservice.Model.Pago;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class PagoDto {
    private Long id;
    private Long usuarioId;
    private Long planId;
    private BigDecimal monto;
    private String estado;
    private LocalDateTime fechaPago;
}