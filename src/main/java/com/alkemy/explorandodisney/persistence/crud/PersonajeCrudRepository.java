package com.alkemy.explorandodisney.persistence.crud;

import com.alkemy.explorandodisney.persistence.entity.Personaje;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface PersonajeCrudRepository extends CrudRepository<Personaje, Long> {
        //*** Create ***
        @Modifying
        @Query(value = "insert into personajes (nombre,edad,peso,historia) values (:nombre,:edad,:peso,:historia)",nativeQuery = true)
        void save(@Param("nombre") String nombre,@Param("edad") Integer edad,@Param("peso") Double peso,@Param("historia") String historia);
        //*** Read ***
        List<Personaje> findAll();
        List<Personaje> findByNombreContaining(String nombre);
        List<Personaje> findByEdadBetween(Integer edadMenor, Integer edadMayor);
        List<Personaje> findByPesoBetween(Double pesoMenor,Double pesoMayor);
        // Consulta JPQL
        @Query(value = "select  p from Personaje p join p.peliculas pp where pp.titulo= :titulo")
        List<Personaje> findByMovie(@Param("titulo") String titulo);
       Optional<Personaje> findByIdPersonaje(Long idPersonaje);
        Optional<Personaje> findByNombre(String nombre);
        //*** Update ***
        @Modifying
        @Query(value = "update Personaje p set p.nombre= :nombre, p.edad= :edad,p.peso= :peso,p.historia= :historia " +
                "where p.idPersonaje= :idPersonaje")
        void updateCharacter(@Param("nombre") String nombre,@Param("edad") Integer edad,@Param("peso") Double peso,@Param("historia") String historia,@Param("idPersonaje") Long id);

        @Modifying(clearAutomatically = true)
        @Query("Update Personaje  p set p.imagen = :imagen where p.idPersonaje= :idPersonaje")
        void updateCharacterImage(@Param("imagen") String imagen, @Param("idPersonaje") Long idPersonaje);
        //*** Delete ***
        @Modifying
        void deleteByIdPersonaje(Long idPersonaje);
        @Modifying
        void deleteByNombre(String nombre);

        /// Relation
        @Query( value = "select count(*) from personajes_peliculas  where exists( select * from personajes_peliculas  where personaje_id=:idPersonaje and pelicula_id= :idPelicula) limit 1", nativeQuery = true)
        //@Query(value = "SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM personajes_peliculas p WHERE p.personaje_id=:idPersonaje and p.pelicula_id= :idPelicula",nativeQuery = true)
        Integer exists(@Param("idPersonaje") Long idPersonaje,@Param("idPelicula")Long idPelicula);
        @Modifying
        @Query( value = "insert into personajes_peliculas  (personaje_id, pelicula_id) values (:idPersonaje ,:idPelicula)", nativeQuery = true)
        void saveRelation(@Param("idPersonaje") Long idPersonaje,@Param("idPelicula")Long idPelicula);
        @Modifying
        @Query( value = "delete from personajes_peliculas where personaje_id= :idPersonaje", nativeQuery = true)
        void deleteRelation (@Param("idPersonaje") Long idPersonaje);

        @Modifying
        @Query(value = "delete from personajes_peliculas where personaje_id= :idPersonaje and pelicula_id= :idPelicula",nativeQuery = true)
        void deleteRelationCharacterMovie(@Param("idPersonaje") Long idPersonaje,@Param("idPelicula") Long idPelicula);
}
