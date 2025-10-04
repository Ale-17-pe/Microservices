package com.universidad.facturacion.Service;

import com.universidad.facturacion.Client.PagoClient;
import com.universidad.facturacion.Client.PlanClient;
import com.universidad.facturacion.Client.UsuarioClient;
import com.universidad.facturacion.Dto.FacturaDto;
import com.universidad.facturacion.Dto.PagoDto;
import com.universidad.facturacion.Dto.PlanDto;
import com.universidad.facturacion.Dto.UsuarioDto;
import com.universidad.facturacion.Model.Factura;
import com.universidad.facturacion.Repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private PagoClient pagoClient;

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private PlanClient planClient;

    private FacturaDto toDto(Factura f) {
        return FacturaDto.builder()
                .id(f.getId())
                .pagoId(f.getPagoId())
                .usuarioId(f.getUsuarioId())
                .nombreCliente(f.getNombreCliente())
                .emailCliente(f.getEmailCliente())
                .descripcionPlan(f.getDescripcionPlan())
                .monto(f.getMonto())
                .estadoPago(f.getEstadoPago())
                .fechaEmision(f.getFechaEmision())
                .numeroFactura(f.getNumeroFactura())
                .build();
    }

    public FacturaDto generarFactura(Long pagoId) {
        // 1. Obtener pago
        PagoDto pago = pagoClient.obtenerPago(pagoId);

        // 2. Obtener usuario y plan
        UsuarioDto usuario = usuarioClient.obtenerUsuario(pago.getUsuarioId());
        PlanDto plan = planClient.obtenerPlan(pago.getPlanId());

        // 3. Crear factura
        Factura factura = Factura.builder()
                .pagoId(pagoId)
                .usuarioId(pago.getUsuarioId())
                .nombreCliente(usuario.getNombre() + " " + usuario.getApellido())
                .emailCliente(usuario.getEmail())
                .descripcionPlan(plan.getNombre() + " - " + plan.getDescripcion())
                .monto(pago.getMonto())
                .estadoPago(pago.getEstado())
                .numeroFactura("FAC-" + System.currentTimeMillis())
                .build();

        Factura saved = facturaRepository.save(factura);
        return toDto(saved);
    }

    public List<FacturaDto> getFacturasPorUsuario(Long usuarioId) {
        return facturaRepository.findByUsuarioId(usuarioId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public FacturaDto getFacturaPorPago(Long pagoId) {
        return facturaRepository.findByPagoId(pagoId).stream()
                .map(this::toDto)
                .findFirst()
                .orElse(null);
    }
}
