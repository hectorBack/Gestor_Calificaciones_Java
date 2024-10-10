package com.Gestor.Calificaciones.Controllers;

import com.Gestor.Calificaciones.Entity.Estudiante;
import com.Gestor.Calificaciones.Services.CursoService;
import com.Gestor.Calificaciones.Services.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public String listarEstudiantes(Model model) {
        model.addAttribute("estudiantes", estudianteService.listarEstudiantes());
        return "estudiantes/lista"; // Renderiza lista.html en /estudiantes
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioCrearEstudiante(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        model.addAttribute("cursos", cursoService.listarCursos());  // Aquí pasas la lista de cursos
        return "estudiantes/formulario"; // Renderiza formulario.html
    }

    @PostMapping
    public String guardarEstudiante(@ModelAttribute("estudiante") Estudiante estudiante) {
        estudianteService.guardarEstudiante(estudiante);
        return "redirect:/estudiantes"; // Redirige a la lista de estudiantes
    }

    // Mostrar formulario para editar un estudiante existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarEstudiante(@PathVariable("id") Long id, Model model) {
        Optional<Estudiante> estudiante = estudianteService.buscarEstudiantePorId(id);
        if (estudiante.isPresent()) {
            model.addAttribute("estudiante", estudiante.get());
            model.addAttribute("cursos", cursoService.listarCursos()); // Listar los cursos para seleccionar uno nuevo
            return "estudiantes/actualizar"; // Apunta a la plantilla Thymeleaf estudiantes/actualizar.html
        } else {
            return "redirect:/estudiantes"; // Redirigir si no se encuentra el estudiante
        }
    }

    // Actualizar un estudiante existente
    @PostMapping("/actualizar/{id}")
    public String actualizarEstudiante(@PathVariable("id") Long id, @ModelAttribute("estudiante") Estudiante estudiante) {
        estudianteService.actualizarEstudiante(id, estudiante);
        return "redirect:/estudiantes"; // Redirigir a la lista de estudiantes después de actualizar
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable Long id) {
        estudianteService.eliminarEstudiante(id);
        return "redirect:/estudiantes"; // Redirige después de eliminar
    }
}

