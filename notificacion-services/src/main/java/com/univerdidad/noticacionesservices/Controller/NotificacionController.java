package com.univerdidad.noticacionesservices.Controller;

import com.univerdidad.noticacionesservices.Dto.NotificacionDto;
import com.univerdidad.noticacionesservices.Service.NotificacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {
    @Autowired
    private NotificacionService notificacionService;

    // S - Search
    @GetMapping("/search")
    public List<NotificacionDto> search(
            @RequestParam(required = false) String destinatario,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String estado) {

        if (destinatario != null) return notificacionService.findByDestinatario(destinatario);
        if (tipo != null) return notificacionService.findByTipo(tipo);
        if (estado != null) return notificacionService.findByEstado(estado);
        return notificacionService.getAll();
    }

    // C - Create
    @PostMapping
    public ResponseEntity<NotificacionDto> create(@Valid @RequestBody NotificacionDto dto) {
        NotificacionDto created = notificacionService.create(dto);
        return ResponseEntity.status(201).body(created);
    }

    // R - Read
    @GetMapping("/{id}")
    public ResponseEntity<NotificacionDto> read(@PathVariable String id) {
        try {
            return ResponseEntity.ok(notificacionService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // U - Update
    @PutMapping("/{id}")
    public ResponseEntity<NotificacionDto> update(@PathVariable String id, @Valid @RequestBody NotificacionDto dto) {
        try {
            return ResponseEntity.ok(notificacionService.update(id, dto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // D - Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        try {
            notificacionService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
