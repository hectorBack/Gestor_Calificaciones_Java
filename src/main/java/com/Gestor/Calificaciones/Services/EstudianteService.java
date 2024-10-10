package com.Gestor.Calificaciones.Services;

import com.Gestor.Calificaciones.Entity.Calificacion;
import com.Gestor.Calificaciones.Entity.Curso;
import com.Gestor.Calificaciones.Entity.Estudiante;
import com.Gestor.Calificaciones.Repository.CalificacionRepository;
import com.Gestor.Calificaciones.Repository.CursoRepository;
import com.Gestor.Calificaciones.Repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CalificacionRepository calificacionRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public List<Estudiante> listarEstudiantes() {
        return estudianteRepository.findAll();
    }

    //lo solucione yo
    public Optional<Estudiante> buscarEstudiantePorId(Long id){
        return estudianteRepository.findById(id);
    }

    public Estudiante guardarEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public void eliminarEstudiante(Long id) {
        List<Calificacion> calificaciones = calificacionRepository.findByEstudianteId(id);
        calificacionRepository.deleteAll(calificaciones); // Elimina todas las calificaciones asociadas al estudiante
        estudianteRepository.deleteById(id); // Elimina el estudiante
    }

    public List<Estudiante> listarEstudiantesPorCurso(Long cursoId) {
        return estudianteRepository.findByCursoId(cursoId);
    }

    public Estudiante actualizarEstudiante(Long id, Estudiante estudianteActualizado) {
        // Busca al estudiante por ID
        Optional<Estudiante> optionalEstudiante = estudianteRepository.findById(id);

        // Verifica si el estudiante existe
        if (optionalEstudiante.isPresent()) {
            Estudiante estudianteExistente = optionalEstudiante.get();

            // Actualiza los campos necesarios
            estudianteExistente.setNombre(estudianteActualizado.getNombre());
            estudianteExistente.setApellido(estudianteActualizado.getApellido());
            estudianteExistente.setFechaNacimiento(estudianteActualizado.getFechaNacimiento());

            // Actualiza el curso si es necesario
            if (estudianteActualizado.getCurso() != null) {
                // Verifica si el curso existe antes de asignarlo
                Optional<Curso> optionalCurso = cursoRepository.findById(estudianteActualizado.getCurso().getId());
                if (optionalCurso.isPresent()) {
                    estudianteExistente.setCurso(optionalCurso.get()); // Actualiza el curso del estudiante
                } else {
                    throw new RuntimeException("Curso no encontrado con el ID: " + estudianteActualizado.getCurso().getId());
                }
            }

            // Guarda los cambios en el estudiante
            return estudianteRepository.save(estudianteExistente);
        } else {
            // Lanza una excepción si el estudiante no existe
            throw new RuntimeException("Estudiante no encontrado con el ID: " + id);
        }

    }
}
