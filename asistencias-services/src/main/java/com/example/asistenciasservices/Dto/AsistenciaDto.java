package com.example.asistenciasservices.Dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class AsistenciaDto {
    private Long id;
    private Long usuarioId;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSalida;
}
