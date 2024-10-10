package com.Gestor.Calificaciones.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    private Double calificacion;
    private LocalDate fecha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Calificacion(Long id, Estudiante estudiante, Curso curso, Double calificacion, LocalDate fecha) {
        this.id = id;
        this.estudiante = estudiante;
        this.curso = curso;
        this.calificacion = calificacion;
        this.fecha = fecha;
    }

    public Calificacion() {
    }
}
