package com.Gestor.Calificaciones.Controllers;

import com.Gestor.Calificaciones.Entity.Curso;
import com.Gestor.Calificaciones.Services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public String listarCursos(Model model) {
        model.addAttribute("cursos", cursoService.listarCursos());
        return "cursos/lista"; // Renderiza lista.html en /cursos
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioCrearCurso(Model model) {
        model.addAttribute("curso", new Curso());
        return "cursos/formulario"; // Renderiza formulario.html
    }

    @PostMapping
    public String guardarCurso(@ModelAttribute("curso") Curso curso) {
        cursoService.guardarCurso(curso);
        return "redirect:/cursos"; // Redirige a la lista de cursos
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarCurso(@PathVariable("id") Long id, Model model) {
        Optional<Curso> curso = cursoService.cursoPorId(id);
        if (curso.isPresent()) {
            model.addAttribute("curso", curso.get());
            return "cursos/actualizar"; // Renderiza actualizar.html
        } else {
            return "redirect:/cursos"; // Redirige si no se encuentra el curso
        }
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarCurso(@PathVariable("id") Long id, @ModelAttribute("curso") Curso curso) {
        cursoService.actualizarCurso(id, curso);
        return "redirect:/cursos"; // Redirige después de actualizar
    }


    @GetMapping("/eliminar/{id}")
    public String eliminarCurso(@PathVariable Long id) {
        cursoService.eliminarCurso(id);
        return "redirect:/cursos"; // Redirige después de eliminar
    }
}
