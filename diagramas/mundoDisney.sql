-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mundo-disney
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mundo-disney
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mundo-disney` DEFAULT CHARACTER SET utf8 ;
USE `mundo-disney` ;

-- -----------------------------------------------------
-- Table `mundo-disney`.`catetegorias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mundo-disney`.`categorias` (
  `id_categoria` INT(11) NOT NULL AUTO_INCREMENT,
  `imagen` VARCHAR(250) NULL,
  `nombre` VARCHAR(250) NOT NULL,
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC) ,
  PRIMARY KEY (`id_categoria`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mundo-disney`.`peliculas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mundo-disney`.`peliculas` (
  `id_pelicula` INT(11) NOT NULL  AUTO_INCREMENT,
  `imagen` VARCHAR(250) NULL,
  `titulo` VARCHAR(250) NOT NULL,
  `fecha_creacion` DATE NOT NULL,
  `calificacion` TINYINT UNSIGNED NOT NULL,
  `categoria_id` INT NOT NULL,
  UNIQUE INDEX `Titulo_UNIQUE` (`titulo` ASC),
  PRIMARY KEY (`id_pelicula`, `categoria_id`),
  INDEX `fk_pelicula_serie_catetegoria_idx` (`categoria_id` ASC),
  CONSTRAINT `fk_pelicula_serie_catetegoria`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `mundo-disney`.`catetegorias` (`id_categoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mundo-disney`.`personajes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mundo-disney`.`personajes` (
  `id_personaje` INT(11) NOT NULL  AUTO_INCREMENT,
  `imagen` VARCHAR(250) NULL,
  `nombre` VARCHAR(250) NOT NULL,
  `edad` DECIMAL UNSIGNED NOT NULL,
  `peso` INT(11) UNSIGNED NOT NULL,
  `historia` TEXT NOT NULL,
  PRIMARY KEY (`id_personaje`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mundo-disney`.`personajes_peliculas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mundo-disney`.`personajes_peliculas` (
  `pelicula_id` INT(11) NOT NULL,
  `personaje_id` INT(11) NOT NULL,
  PRIMARY KEY (`pelicula_id`, `personaje_id`),
  INDEX `fk_personajes_peliculas_series_peliculas_series1_idx` (`pelicula_id` ASC),
  INDEX `fk_personajes_peliculas_series_personajes1_idx` (`personaje_id` ASC),
  CONSTRAINT `fk_personajes_peliculas_series_peliculas_series1`
    FOREIGN KEY (`pelicula_id`)
    REFERENCES `mundo-disney`.`peliculas` (`id_pelicula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personajes_peliculas_series_personajes1`
    FOREIGN KEY (`personaje_id`)
    REFERENCES `mundo-disney`.`personajes` (`id_personaje`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
