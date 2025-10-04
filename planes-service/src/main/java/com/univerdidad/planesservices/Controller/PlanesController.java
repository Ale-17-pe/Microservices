package com.univerdidad.planesservices.Controller;

import com.univerdidad.planesservices.DTO.PlanDTO;
import com.univerdidad.planesservices.Services.PlanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planes")
public class PlanesController {

    @Autowired
    private PlanService planService;

    // S - Search
    @GetMapping("/search")
    public List<PlanDTO> search(@RequestParam String nombre) {
        return planService.searchByNombre(nombre);
    }

    // C - Create
    @PostMapping
    public ResponseEntity<PlanDTO> create(@Valid @RequestBody PlanDTO dto) {
        PlanDTO created = planService.create(dto);
        return ResponseEntity.status(201).body(created);
    }

    // R - Read
    @GetMapping("/{id}")
    public ResponseEntity<PlanDTO> read(@PathVariable Long id) {
        try {
            PlanDTO plan = planService.getById(id);
            return ResponseEntity.ok(plan);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // U - Update
    @PutMapping("/{id}")
    public ResponseEntity<PlanDTO> update(@PathVariable Long id, @Valid @RequestBody PlanDTO dto) {
        try {
            PlanDTO updated = planService.update(id, dto);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // D - Delete (soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            planService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Listar todos los planes activos
    @GetMapping
    public ResponseEntity<List<PlanDTO>> getAll() {
        return ResponseEntity.ok(planService.getAll());
    }
}