package com.univerdidad.planesservices.Services;

import com.univerdidad.planesservices.DTO.PlanDTO;
import com.univerdidad.planesservices.Model.Plan;
import com.univerdidad.planesservices.Repository.PlanRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

    private PlanDTO toDto(Plan plan) {
        return PlanDTO.builder()
                .id(plan.getId())
                .nombre(plan.getNombre())
                .descripcion(plan.getDescripcion())
                .precio(plan.getPrecio())
                .duracionDias(plan.getDuracionDias())
                .activo(plan.getActivo())
                .build();
    }

    private Plan toEntity(PlanDTO dto) {
        Plan plan = new Plan();
        plan.setId(dto.getId());
        plan.setNombre(dto.getNombre());
        plan.setDescripcion(dto.getDescripcion());
        plan.setPrecio(dto.getPrecio());
        plan.setDuracionDias(dto.getDuracionDias());
        plan.setActivo(dto.getActivo());
        return plan;
    }

    public List<PlanDTO> getAll() {
        return planRepository.findByActivo(true).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public PlanDTO getById(Long id) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plan no encontrado con ID: " + id));
        return toDto(plan);
    }

    public PlanDTO create(PlanDTO dto) {
        Plan entity = toEntity(dto);
        Plan saved = planRepository.save(entity);
        return toDto(saved);
    }

    public PlanDTO update(Long id, PlanDTO dto) {
        Plan existing = planRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plan no encontrado con ID: " + id));

        existing.setNombre(dto.getNombre());
        existing.setDescripcion(dto.getDescripcion());
        existing.setPrecio(dto.getPrecio());
        existing.setDuracionDias(dto.getDuracionDias());
        existing.setActivo(dto.getActivo());

        Plan updated = planRepository.save(existing);
        return toDto(updated);
    }

    public void delete(Long id) {
        Plan existing = planRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plan no encontrado con ID: " + id));
        existing.setActivo(false); // Soft delete
        planRepository.save(existing);
    }

    public List<PlanDTO> searchByNombre(String nombre) {
        return planRepository.findByNombreContainingAndActivo(nombre,true   ).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
