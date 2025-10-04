package com.universidad.facturacion.Dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter @Getter @Builder
@NoArgsConstructor  @AllArgsConstructor

public class FacturaDto {
    private Long id;
    private Long pagoId;
    private Long usuarioId;
    private String nombreCliente;
    private String emailCliente;
    private String descripcionPlan;
    private BigDecimal monto;
    private String estadoPago;
    private LocalDateTime fechaEmision;
    private String numeroFactura;
}
