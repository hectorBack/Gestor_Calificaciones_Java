package com.Gestor.Calificaciones.Repository;

import com.Gestor.Calificaciones.Entity.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {
    List<Calificacion> findByEstudianteId(Long estudianteId);
    List<Calificacion> findByCursoId(Long cursoId);
}
