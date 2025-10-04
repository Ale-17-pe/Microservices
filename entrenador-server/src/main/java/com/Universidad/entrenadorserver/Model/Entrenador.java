package com.Universidad.entrenadorserver.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "entrenador")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Entrenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "Los apellidos son obligatorios")
    private String apellidos;

    @Email(message = "Debe ser un correo válido")
    private String email;

    private String telefono;
    private String especialidad;
    private Boolean activo = true;
}