package com.Universidad.entrenadorserver.Controller;

import com.Universidad.entrenadorserver.Dto.EntrenadorDto;
import com.Universidad.entrenadorserver.Service.EntrenadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entrenadores")
public class UsuarioController {
    @Autowired
    private EntrenadorService entrenadorService;

    // S - Search
    @GetMapping("/search")
    public List<EntrenadorDto> search(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String especialidad) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            return entrenadorService.searchByNombre(nombre);
        }
        if (especialidad != null && !especialidad.trim().isEmpty()) {
            return entrenadorService.searchByEspecialidad(especialidad);
        }
        return entrenadorService.getAll();
    }

    // C - Create
    @PostMapping
    public ResponseEntity<EntrenadorDto> create(@Valid @RequestBody EntrenadorDto dto) {
        EntrenadorDto created = entrenadorService.create(dto);
        return ResponseEntity.status(201).body(created);
    }

    // R - Read
    @GetMapping("/{id}")
    public ResponseEntity<EntrenadorDto> read(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(entrenadorService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // U - Update
    @PutMapping("/{id}")
    public ResponseEntity<EntrenadorDto> update(@PathVariable Long id, @Valid @RequestBody EntrenadorDto dto) {
        try {
            return ResponseEntity.ok(entrenadorService.update(id, dto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // D - Delete (soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            entrenadorService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
