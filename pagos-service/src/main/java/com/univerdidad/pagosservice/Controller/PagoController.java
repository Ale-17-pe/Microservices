package com.univerdidad.pagosservice.Controller;

import com.univerdidad.pagosservice.Dto.PagoDto;
import com.univerdidad.pagosservice.Services.PagoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {
    @Autowired
    private PagoService pagoService;

    @GetMapping("/search")
    public List<PagoDto> search(
            @RequestParam(required = false) Long usuarioId,
            @RequestParam(required = false) Long planId,
            @RequestParam(required = false) String estado) {

        if (usuarioId != null) return pagoService.findByUsuarioId(usuarioId);
        if (planId != null) return pagoService.findByPlanId(planId);
        if (estado != null) return pagoService.findByEstado(estado);
        return pagoService.getAll();
    }

    @PostMapping
    public ResponseEntity<PagoDto> create(@Valid @RequestBody PagoDto dto) {
        PagoDto created = pagoService.create(dto);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoDto> read(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(pagoService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/detalle")
    public ResponseEntity<PagoService.PagoEnriquecidoDto> readEnriquecido(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(pagoService.getEnriquecido(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoDto> update(@PathVariable Long id, @Valid @RequestBody PagoDto dto) {
        try {
            return ResponseEntity.ok(pagoService.update(id, dto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            pagoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
