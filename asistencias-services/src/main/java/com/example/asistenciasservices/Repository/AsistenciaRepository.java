package com.example.asistenciasservices.Repository;

import com.example.asistenciasservices.Model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AsistenciaRepository  extends JpaRepository<Asistencia, Long> {
    List<Asistencia> findByUsuarioId(Long usuarioId);
    List<Asistencia> findByUsuarioIdAndHoraEntradaBetween(Long usuarioId, LocalDateTime start, LocalDateTime end);
    List<Asistencia> findByHoraEntradaBetween(LocalDateTime start, LocalDateTime end);
}
