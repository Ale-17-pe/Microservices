package com.example.asistenciasservices.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "asistencia")
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "ID de usuario es obligatorio")
    private Long usuarioId;

    @NotNull
    private LocalDateTime horaEntrada;

    private LocalDateTime horaSalida;

    private Boolean activo = true;
}