package com.Gestor.Calificaciones.Controllers;

import com.Gestor.Calificaciones.Entity.Calificacion;
import com.Gestor.Calificaciones.Services.CalificacionService;
import com.Gestor.Calificaciones.Services.CursoService;
import com.Gestor.Calificaciones.Services.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/calificaciones")
public class CalificacionController {

    @Autowired
    private CalificacionService calificacionService;
    @Autowired
    private EstudianteService estudianteService;
    @Autowired
    private CursoService cursoService;

    @GetMapping
    public String listarCalificaciones(Model model) {
        model.addAttribute("calificaciones", calificacionService.listarCalificaciones());
        return "calificaciones/lista"; // Renderiza lista.html en /calificaciones
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioCrearCalificacion(Model model) {
        model.addAttribute("calificacion", new Calificacion());
        model.addAttribute("estudiantes", estudianteService.listarEstudiantes());
        model.addAttribute("cursos", cursoService.listarCursos());
        return "calificaciones/formulario"; // Renderiza formulario.html
    }

    @PostMapping
    public String guardarCalificacion(@ModelAttribute("calificacion") Calificacion calificacion) {
        calificacionService.guardarCalificacion(calificacion);
        return "redirect:/calificaciones"; // Redirige a la lista de calificaciones
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarCalificacion(@PathVariable("id") Long id, Model model) {
        Optional<Calificacion> calificacion = calificacionService.buscarCalificacionPorId(id);
        if (calificacion.isPresent()) {
            model.addAttribute("calificacion", calificacion.get());
            return "calificaciones/actualizar"; // Renderiza la página actualizar.html
        } else {
            return "redirect:/calificaciones"; // Redirige si no se encuentra la calificación
        }
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarCalificacion(@PathVariable("id") Long id,
                                         @ModelAttribute("calificacion") Calificacion calificacion) {
        calificacionService.actualizarCalificacion(id, calificacion);
        return "redirect:/calificaciones"; // Redirige a la lista de calificaciones
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCalificacion(@PathVariable Long id) {
        calificacionService.eliminarCalificacion(id);
        return "redirect:/calificaciones"; // Redirige después de eliminar
    }
}

