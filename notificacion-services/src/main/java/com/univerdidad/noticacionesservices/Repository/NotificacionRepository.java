package com.univerdidad.noticacionesservices.Repository;

import com.univerdidad.noticacionesservices.Model.Notificacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionRepository extends MongoRepository<Notificacion, String> {
    List<Notificacion> findByDestinatario(String destinatario);
    List<Notificacion> findByTipo(String tipo);
    List<Notificacion> findByEstado(String estado);
}
