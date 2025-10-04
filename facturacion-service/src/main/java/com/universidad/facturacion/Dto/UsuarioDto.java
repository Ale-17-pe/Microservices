package com.universidad.facturacion.Dto;

import lombok.*;

@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
public class UsuarioDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
}
