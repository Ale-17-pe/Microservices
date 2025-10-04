package com.Universidad.entrenadorserver.Repository;

import com.Universidad.entrenadorserver.Model.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntrenadorRepository extends JpaRepository<Entrenador,Integer> {
    List<Entrenador> findByNombreContainingIgnoreCase(String nombre);
    List<Entrenador> findByEspecialidadContainingIgnoreCase(String especialidad);
    List<Entrenador> findByActivo(Boolean activo);

}
