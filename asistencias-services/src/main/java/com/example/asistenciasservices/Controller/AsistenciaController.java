package com.example.asistenciasservices.Controller;

import com.example.asistenciasservices.Dto.AsistenciaDto;
import com.example.asistenciasservices.Services.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/asistencia")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;

    // S - Search
    @GetMapping("/search")
    public List<AsistenciaDto> search(
            @RequestParam(required = false) Long usuarioId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {

        if (usuarioId != null && fecha != null) {
            return asistenciaService.findByUsuarioYFecha(usuarioId, fecha);
        }
        if (usuarioId != null) {
            return asistenciaService.findByUsuarioId(usuarioId);
        }
        if (fecha != null) {
            return asistenciaService.findByFecha(fecha);
        }
        return asistenciaService.getAll();
    }

    // C - Entrada
    @PostMapping("/entrada")
    public ResponseEntity<AsistenciaDto> registrarEntrada(@RequestParam Long usuarioId) {
        AsistenciaDto asistencia = asistenciaService.registrarEntrada(usuarioId);
        return ResponseEntity.status(201).body(asistencia);
    }

    // C - Salida
    @PostMapping("/salida/{id}")
    public ResponseEntity<AsistenciaDto> registrarSalida(@PathVariable Long id) {
        AsistenciaDto asistencia = asistenciaService.registrarSalida(id);
        return ResponseEntity.ok(asistencia);
    }

    // R - Read
    @GetMapping("/{id}")
    public ResponseEntity<AsistenciaDto> read(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(asistenciaService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // D - Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        asistenciaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
