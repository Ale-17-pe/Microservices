package com.example.asistenciasservices.Services;

import com.example.asistenciasservices.Dto.AsistenciaDto;
import com.example.asistenciasservices.Model.Asistencia;
import com.example.asistenciasservices.Repository.AsistenciaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    private AsistenciaDto toDto(Asistencia a) {
        return AsistenciaDto.builder()
                .id(a.getId())
                .usuarioId(a.getUsuarioId())
                .horaEntrada(a.getHoraEntrada())
                .horaSalida(a.getHoraSalida())
                .build();
    }

    private Asistencia toEntity(AsistenciaDto dto) {
        Asistencia a = new Asistencia();
        a.setId(dto.getId());
        a.setUsuarioId(dto.getUsuarioId());
        a.setHoraEntrada(dto.getHoraEntrada());
        a.setHoraSalida(dto.getHoraSalida());
        return a;
    }

    public List<AsistenciaDto> getAll() {
        return asistenciaRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public AsistenciaDto getById(Long id) {
        Asistencia a = asistenciaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Asistencia no encontrada"));
        return toDto(a);
    }

    public AsistenciaDto registrarEntrada(Long usuarioId) {
        Asistencia asistencia = Asistencia.builder()
                .usuarioId(usuarioId)
                .horaEntrada(LocalDateTime.now())
                .build();
        Asistencia saved = asistenciaRepository.save(asistencia);
        return toDto(saved);
    }

    public AsistenciaDto registrarSalida(Long asistenciaId) {
        Asistencia existing = asistenciaRepository.findById(asistenciaId)
                .orElseThrow(() -> new EntityNotFoundException("Registro de entrada no encontrado"));
        existing.setHoraSalida(LocalDateTime.now());
        Asistencia updated = asistenciaRepository.save(existing);
        return toDto(updated);
    }

    public List<AsistenciaDto> findByUsuarioId(Long usuarioId) {
        return asistenciaRepository.findByUsuarioId(usuarioId).stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<AsistenciaDto> findByFecha(LocalDate fecha) {
        LocalDateTime start = fecha.atStartOfDay();
        LocalDateTime end = fecha.plusDays(1).atStartOfDay();
        return asistenciaRepository.findByHoraEntradaBetween(start, end).stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<AsistenciaDto> findByUsuarioYFecha(Long usuarioId, LocalDate fecha) {
        LocalDateTime start = fecha.atStartOfDay();
        LocalDateTime end = fecha.plusDays(1).atStartOfDay();
        return asistenciaRepository.findByUsuarioIdAndHoraEntradaBetween(usuarioId, start, end).stream().map(this::toDto).collect(Collectors.toList());
    }

    public void delete(Long id) {
        asistenciaRepository.deleteById(id);
    }
}
