package com.Gestor.Calificaciones.Repository;

import com.Gestor.Calificaciones.Entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}