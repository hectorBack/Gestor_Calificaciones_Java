package com.Gestor.Calificaciones.Services;

import com.Gestor.Calificaciones.Entity.Calificacion;
import com.Gestor.Calificaciones.Entity.Curso;
import com.Gestor.Calificaciones.Repository.CalificacionRepository;
import com.Gestor.Calificaciones.Repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CalificacionRepository calificacionRepository;

    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    public Curso guardarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Optional<Curso> cursoPorId(Long id) {
        return cursoRepository.findById(id);
    }

    public void eliminarCurso(Long id) {
        List<Calificacion> calificaciones = calificacionRepository.findByCursoId(id);
        calificacionRepository.deleteAll(calificaciones); // Elimina todas las calificaciones asociadas al curso
        cursoRepository.deleteById(id); // Elimina el curso
    }

    public void actualizarCurso(Long id, Curso curso) {
        Optional<Curso> cursoExistente = cursoRepository.findById(id);
        if (cursoExistente.isPresent()) {
            Curso cursoActualizado = cursoExistente.get();
            cursoActualizado.setNombre(curso.getNombre());
            cursoActualizado.setDescripcion(curso.getDescripcion());
            cursoActualizado.setProfesor(curso.getProfesor());
            cursoRepository.save(cursoActualizado);
        }
    }
}