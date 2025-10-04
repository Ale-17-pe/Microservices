package com.universidad.facturacion.Repository;

import com.universidad.facturacion.Model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
    List<Factura> findByUsuarioId(Long usuarioId);
    List<Factura> findByPagoId(Long pagoId);
}
