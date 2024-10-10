package com.Gestor.Calificaciones.Repository;

import com.Gestor.Calificaciones.Entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    List<Estudiante> findByCursoId(Long cursoId);
}

