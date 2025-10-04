package com.univerdidad.noticacionesservices.Service;

import com.univerdidad.noticacionesservices.Dto.NotificacionDto;
import com.univerdidad.noticacionesservices.Model.Notificacion;
import com.univerdidad.noticacionesservices.Repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    private NotificacionDto toDto(Notificacion notif) {
        return NotificacionDto.builder()
                .id(notif.getId())
                .destinatario(notif.getDestinatario())
                .tipo(notif.getTipo())
                .asunto(notif.getAsunto())
                .cuerpo(notif.getCuerpo())
                .estado(notif.getEstado())
                .fechaCreacion(notif.getFechaCreacion())
                .build();
    }

    private Notificacion toEntity(NotificacionDto dto) {
        Notificacion notif = new Notificacion();
        notif.setId(dto.getId());
        notif.setDestinatario(dto.getDestinatario());
        notif.setTipo(dto.getTipo());
        notif.setAsunto(dto.getAsunto());
        notif.setCuerpo(dto.getCuerpo());
        notif.setEstado(dto.getEstado());
        notif.setFechaCreacion(dto.getFechaCreacion());
        return notif;
    }

    public List<NotificacionDto> getAll() {
        return notificacionRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public NotificacionDto getById(String id) {
        Notificacion notif = notificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));
        return toDto(notif);
    }

    public NotificacionDto create(NotificacionDto dto) {
        Notificacion entity = toEntity(dto);
        entity.setEstado("PENDIENTE");
        // Aquí normalmente llamarías a un servicio de email/SMS
        // Por ahora, simulamos el envío
        entity.setEstado("ENVIADO");
        Notificacion saved = notificacionRepository.save(entity);
        return toDto(saved);
    }

    public NotificacionDto update(String id, NotificacionDto dto) {
        if (!notificacionRepository.existsById(id)) {
            throw new RuntimeException("Notificación no encontrada");
        }
        dto.setId(id);
        Notificacion updated = notificacionRepository.save(toEntity(dto));
        return toDto(updated);
    }

    public void delete(String id) {
        notificacionRepository.deleteById(id);
    }

    // Búsquedas
    public List<NotificacionDto> findByDestinatario(String destinatario) {
        return notificacionRepository.findByDestinatario(destinatario).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<NotificacionDto> findByTipo(String tipo) {
        return notificacionRepository.findByTipo(tipo).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<NotificacionDto> findByEstado(String estado) {
        return notificacionRepository.findByEstado(estado).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
