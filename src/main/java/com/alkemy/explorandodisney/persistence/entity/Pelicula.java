package com.alkemy.explorandodisney.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "peliculas")
public final class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pelicula")
    private Long idPelicula;
    private String imagen;
    @Column(unique = true)
    private String titulo;
    @Column(name="fecha_creacion")
    private LocalDate fechaCreacion;
    private Integer calificacion;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name="categoria_id")
    private Categoria categoria;


    @JoinTable(
            name = "personajes_peliculas",
            joinColumns = @JoinColumn(name = "pelicula_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="personaje_id", nullable = false)
    )
    @ManyToMany()
    private Set<Personaje> personajes;

    public Pelicula() {
    }

    public Long getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Long idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @JsonFormat(pattern = "dd-MM-yyyy")
    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public Set<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(Set<Personaje> personajes) {
        this.personajes = personajes;
    }
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
