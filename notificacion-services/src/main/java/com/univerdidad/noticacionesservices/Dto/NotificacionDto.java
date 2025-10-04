package com.univerdidad.noticacionesservices.Dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor  @AllArgsConstructor
@Builder
public class NotificacionDto {
    private String id;
    private String destinatario;
    private String tipo;
    private String asunto;
    private String cuerpo;
    private String estado;
    private LocalDateTime fechaCreacion;
}
