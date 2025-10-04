package com.Universidad.entrenadorserver.Service;

import com.Universidad.entrenadorserver.Dto.EntrenadorDto;
import com.Universidad.entrenadorserver.Model.Entrenador;
import com.Universidad.entrenadorserver.Repository.EntrenadorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntrenadorService {
    @Autowired
    private EntrenadorRepository entrenadorRepository;

    private EntrenadorDto toDto(Entrenador e) {
        return EntrenadorDto.builder()
                .id(e.getId())
                .nombre(e.getNombre())
                .apellidos(e.getApellidos())
                .email(e.getEmail())
                .telefono(e.getTelefono())
                .especialidad(e.getEspecialidad())
                .activo(e.getActivo())
                .build();
    }

    private Entrenador toEntity(EntrenadorDto dto) {
        Entrenador e = new Entrenador();
        e.setId(dto.getId());
        e.setNombre(dto.getNombre());
        e.setApellidos(dto.getApellidos());
        e.setEmail(dto.getEmail());
        e.setTelefono(dto.getTelefono());
        e.setEspecialidad(dto.getEspecialidad());
        e.setActivo(dto.getActivo());
        return e;
    }

    public List<EntrenadorDto> getAll() {
        return entrenadorRepository.findByActivo(true).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public EntrenadorDto getById(Long id) {
        Entrenador e = entrenadorRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new EntityNotFoundException("Entrenador no encontrado con ID: " + id));
        return toDto(e);
    }

    public EntrenadorDto create(EntrenadorDto dto) {
        Entrenador entity = toEntity(dto);
        Entrenador saved = entrenadorRepository.save(entity);
        return toDto(saved);
    }

    public EntrenadorDto update(Long id, EntrenadorDto dto) {
        Entrenador existing = entrenadorRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new EntityNotFoundException("Entrenador no encontrado con ID: " + id));
        existing.setNombre(dto.getNombre());
        existing.setApellidos(dto.getApellidos());
        existing.setEmail(dto.getEmail());
        existing.setTelefono(dto.getTelefono());
        existing.setEspecialidad(dto.getEspecialidad());
        existing.setActivo(dto.getActivo());
        Entrenador updated = entrenadorRepository.save(existing);
        return toDto(updated);
    }

    public void delete(Long id) {
        Entrenador existing = entrenadorRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new EntityNotFoundException("Entrenador no encontrado con ID: " + id));
        existing.setActivo(false); // Soft delete
        entrenadorRepository.save(existing);
    }

    public List<EntrenadorDto> searchByNombre(String nombre) {
        return entrenadorRepository.findByNombreContainingIgnoreCase(nombre).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<EntrenadorDto> searchByEspecialidad(String especialidad) {
        return entrenadorRepository.findByEspecialidadContainingIgnoreCase(especialidad).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
