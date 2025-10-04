package com.univerdidad.pagosservice.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity @Table(name = "pago")
@Getter @Setter
@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "ID de usuario es obligatorio")
    private Long usuarioId;

    @NotNull(message = "ID del plan es obligatorio")
    private Long planId;

    @NotNull(message = "Monto es obligatorio")
    @Positive(message = "Monto debe ser mayor a 0")
    private BigDecimal monto;

    @NotNull(message = "Estado es obligatorio")
    private String estado;

    private LocalDateTime fechaPago = LocalDateTime.now();
}
