package com.Universidad.entrenadorserver.Dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class EntrenadorDto {
    private Long id;
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private String especialidad;
    private Boolean activo;
}
