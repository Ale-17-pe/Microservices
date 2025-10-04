package com.univerdidad.pagosservice.Repository;

import com.univerdidad.pagosservice.Model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagoRespository extends JpaRepository<Pago, Long> {
    List<Pago> findByUsuarioId(Long usuarioId);
    List<Pago> findByPlanId(Long planId);
    List<Pago> findByEstado(String estado);
}
