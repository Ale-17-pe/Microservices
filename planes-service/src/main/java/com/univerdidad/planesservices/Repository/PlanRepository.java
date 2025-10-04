package com.univerdidad.planesservices.Repository;

import com.univerdidad.planesservices.Model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    List<Plan> findByActivo(boolean activo);
    List<Plan> findByNombreContainingAndActivo(String nombre,  Boolean activo);
}
