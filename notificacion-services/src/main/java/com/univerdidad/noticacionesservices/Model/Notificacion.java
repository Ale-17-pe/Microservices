package com.univerdidad.noticacionesservices.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collation = "noticaciones")
@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Notificacion {

    @Id
    private String id;
    private String destinatario;
    private String tipo;
    private String asunto;
    private String cuerpo;
    private String estado;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
}
