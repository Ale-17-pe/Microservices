package com.universidad.facturacion.Controller;

import com.universidad.facturacion.Dto.FacturaDto;
import com.universidad.facturacion.Service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    // Generar factura a partir de un pago
    @PostMapping("/generar/{pagoId}")
    public ResponseEntity<FacturaDto> generarFactura(@PathVariable Long pagoId) {
        FacturaDto factura = facturaService.generarFactura(pagoId);
        return ResponseEntity.status(201).body(factura);
    }

    // Listar facturas de un usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<FacturaDto>> facturasPorUsuario(@PathVariable Long usuarioId) {
        List<FacturaDto> facturas = facturaService.getFacturasPorUsuario(usuarioId);
        return ResponseEntity.ok(facturas);
    }

    // Obtener factura por pago
    @GetMapping("/pago/{pagoId}")
    public ResponseEntity<FacturaDto> facturaPorPago(@PathVariable Long pagoId) {
        FacturaDto factura = facturaService.getFacturaPorPago(pagoId);
        if (factura != null) {
            return ResponseEntity.ok(factura);
        }
        return ResponseEntity.notFound().build();
    }
}
