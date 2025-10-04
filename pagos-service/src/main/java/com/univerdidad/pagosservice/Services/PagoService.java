package com.univerdidad.pagosservice.Services;

import com.univerdidad.pagosservice.Client.*;
import com.univerdidad.pagosservice.Dto.NotificacionDto;
import com.univerdidad.pagosservice.Dto.PagoDto;
import com.univerdidad.pagosservice.Dto.PlanDto;
import com.univerdidad.pagosservice.Dto.UsuarioDto;
import com.univerdidad.pagosservice.Model.Pago;
import com.univerdidad.pagosservice.Repository.PagoRespository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagoService {

    @Autowired
    private PagoRespository pagoRepository;

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private PlanClient planClient;

    private PagoDto toDto(Pago pago) {
        return PagoDto.builder()
                .id(pago.getId())
                .usuarioId(pago.getUsuarioId())
                .planId(pago.getPlanId())
                .monto(pago.getMonto())
                .estado(pago.getEstado())
                .fechaPago(pago.getFechaPago())
                .build();
    }
    private Pago toEntity(PagoDto dto) {
        Pago pago = new Pago();
        pago.setId(dto.getId());
        pago.setUsuarioId(dto.getUsuarioId());
        pago.setPlanId(dto.getPlanId());
        pago.setMonto(dto.getMonto());
        pago.setEstado(dto.getEstado());
        pago.setFechaPago(dto.getFechaPago());
        return pago;
    }
    public List<PagoDto> getAll() {
        return pagoRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public PagoDto getById(Long id) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pago no encontrado"));
        return toDto(pago);
    }

    public PagoDto create(PagoDto dto) {
        // Validar que usuario y plan existan (opcional, pero recomendado)
        usuarioClient.obtenerUsuario(dto.getUsuarioId());
        planClient.obtenerPlan(dto.getPlanId());

        Pago entity = toEntity(dto);
        Pago saved = pagoRepository.save(entity);
        return toDto(saved);
    }

    public PagoDto update(Long id, PagoDto dto) {
        if (!pagoRepository.existsById(id)) {
            throw new EntityNotFoundException("Pago no encontrado");
        }
        dto.setId(id);
        Pago updated = pagoRepository.save(toEntity(dto));
        return toDto(updated);
    }

    public void delete(Long id) {
        pagoRepository.deleteById(id);
    }

    // Búsquedas
    public List<PagoDto> findByUsuarioId(Long usuarioId) {
        return pagoRepository.findByUsuarioId(usuarioId).stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<PagoDto> findByPlanId(Long planId) {
        return pagoRepository.findByPlanId(planId).stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<PagoDto> findByEstado(String estado) {
        return pagoRepository.findByEstado(estado).stream().map(this::toDto).collect(Collectors.toList());
    }

    // Método para obtener datos enriquecidos (usuario + plan + pago)
    public PagoEnriquecidoDto getEnriquecido(Long pagoId) {
        PagoDto pago = this.getById(pagoId);
        UsuarioDto usuario = usuarioClient.obtenerUsuario(pago.getUsuarioId());
        PlanDto plan = planClient.obtenerPlan(pago.getPlanId());

        return new PagoEnriquecidoDto(pago, usuario, plan);
    }

    // Clase interna para respuesta enriquecida
    @lombok.Data
    public static class PagoEnriquecidoDto {
        private PagoDto pago;
        private UsuarioDto usuario;
        private PlanDto plan;

        public PagoEnriquecidoDto(PagoDto pago, UsuarioDto usuario, PlanDto plan) {
            this.pago = pago;
            this.usuario = usuario;
            this.plan = plan;
        }
    }
    private void enviarNotificacion(PagoDto pago) {
        RestTemplate restTemplate = new RestTemplate();
        NotificacionDto notif = NotificacionDto.builder()
                .destinatario("usuario@example.com")
                .tipo("EMAIL")
                .asunto("Pago registrado")
                .cuerpo("Pago de $" + pago.getMonto() + " confirmado.")
                .build();
        restTemplate.postForObject("http://localhost:8904/api/notificaciones", notif, NotificacionDto.class);
}
}
