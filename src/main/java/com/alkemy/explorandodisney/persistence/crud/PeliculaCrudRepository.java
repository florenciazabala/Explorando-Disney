package com.alkemy.explorandodisney.persistence.crud;

import com.alkemy.explorandodisney.persistence.entity.Pelicula;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PeliculaCrudRepository extends CrudRepository<Pelicula,Long> {
    //** Create **
    @Modifying
    @Query(value = "insert into peliculas (titulo,fecha_creacion,calificacion,categoria_id) values (:titulo, :fechaCreacion, :calificacion, :categoria)",nativeQuery = true)
    void save(@Param("titulo") String titulo,@Param("fechaCreacion") LocalDate fechaCreacion, @Param("calificacion") Integer calificacion,
                  @Param("categoria") Long categoria);
    //void createRelationCharacters(Long idPelicula, Long idPersonaje);
    //** Read **
    List<Pelicula> findAll();
    List<Pelicula> findByTituloContaining(String titulo);
    Optional<Pelicula> findByTitulo(String titulo);
    Optional<Pelicula> findByIdPelicula(Long idPelicula);
    // Consulta JPQL
    @Query(value = "select  p from Pelicula p join p.categoria c where c.nombre= :categoria")
    List<Pelicula> findByCategory(@Param("categoria") String categoria);
    @Query(value = "select p from Pelicula p order by p.fechaCreacion desc")
    List<Pelicula> findOrderByFechaCreacionDesc();
    @Query(value = "select p from Pelicula p order by p.fechaCreacion asc")
    List<Pelicula> findOrderByFechaCreacionAsc();

    //** Update **
    @Modifying
    @Query(value = "update peliculas p set p.titulo= :titulo, p.fecha_creacion= :fechaCreacion,p.calificacion= :calificacion, p.categoria_id= :categoria " +
            "where p.id_pelicula= :idPelicula",nativeQuery = true)
    void updateMovie(@Param("titulo") String titulo,@Param("fechaCreacion") LocalDate fechaCreacion, @Param("calificacion") Integer calificacion,
                   @Param("categoria") Long categoria, @Param("idPelicula") Long id);

    @Modifying(clearAutomatically = true)
    @Query("Update Pelicula  p set p.imagen = :imagen where p.idPelicula= :idPelicula")
    void updateImage(@Param("imagen") String imagen, @Param("idPelicula") Long idPelicula);

    //** Delete **
    @Modifying
    void deleteByIdPelicula(Long idPelicula);
    @Modifying
    void deleteByTitulo(String titulo);
    @Modifying
    @Query( value = "delete from personajes_peliculas where pelicula_id= :idPelicula", nativeQuery = true)
    void deleteRelation (@Param("idPelicula") Long idPersonaje);
}
