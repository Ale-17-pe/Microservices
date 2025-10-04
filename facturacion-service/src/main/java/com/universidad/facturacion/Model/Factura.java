package com.universidad.facturacion.Model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter @AllArgsConstructor @Builder @Entity
@NoArgsConstructor @Table(name = "facturas")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pagoId;
    private Long usuarioId;
    private String nombreCliente;
    private String emailCliente;
    private String descripcionPlan;
    private BigDecimal monto;
    private String estadoPago;
    private LocalDateTime fechaEmision = LocalDateTime.now();
    private String numeroFactura;
}