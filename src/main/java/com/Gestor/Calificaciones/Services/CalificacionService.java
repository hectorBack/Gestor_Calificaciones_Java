package com.Gestor.Calificaciones.Services;

import com.Gestor.Calificaciones.Entity.Calificacion;
import com.Gestor.Calificaciones.Repository.CalificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalificacionService {

    @Autowired
    private CalificacionRepository calificacionRepository;

    public List<Calificacion> listarCalificaciones() {
        return calificacionRepository.findAll();
    }

    public Calificacion guardarCalificacion(Calificacion calificacion) {
        return calificacionRepository.save(calificacion);
    }

    public List<Calificacion> listarCalificacionesPorEstudiante(Long estudianteId) {
        return calificacionRepository.findByEstudianteId(estudianteId);
    }

    public List<Calificacion> listarCalificacionesPorCurso(Long cursoId) {
        return calificacionRepository.findByCursoId(cursoId);
    }

    public void eliminarCalificacion(Long id) {
        calificacionRepository.deleteById(id);
    }

    public Optional<Calificacion> buscarCalificacionPorId(Long id) {
        return calificacionRepository.findById(id);
    }

    public void actualizarCalificacion(Long id, Calificacion calificacionActualizada) {
        Optional<Calificacion> calificacionExistente = calificacionRepository.findById(id);
        if (calificacionExistente.isPresent()) {
            Calificacion calificacion = calificacionExistente.get();
            calificacion.setCalificacion(calificacionActualizada.getCalificacion());
            calificacion.setFecha(calificacionActualizada.getFecha());
            calificacionRepository.save(calificacion);
        }
    }
}
