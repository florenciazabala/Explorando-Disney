-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-08-2021 a las 04:59:22
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `mundo-disney`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `id_categoria` int(11) NOT NULL,
  `imagen` varchar(250) DEFAULT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id_categoria`, `imagen`, `nombre`) VALUES
(1, NULL, 'comedia'),
(2, NULL, 'accion/ aventuras'),
(3, NULL, 'documental'),
(4, NULL, 'infantil'),
(5, NULL, 'ciencia ficcion'),
(6, NULL, 'fantasia'),
(7, NULL, 'drama');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `peliculas`
--

CREATE TABLE `peliculas` (
  `id_pelicula` int(11) NOT NULL,
  `imagen` varchar(250) DEFAULT NULL,
  `titulo` varchar(250) NOT NULL,
  `fecha_creacion` date NOT NULL,
  `calificacion` tinyint(3) UNSIGNED NOT NULL,
  `categoria_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `peliculas`
--

INSERT INTO `peliculas` (`id_pelicula`, `imagen`, `titulo`, `fecha_creacion`, `calificacion`, `categoria_id`) VALUES
(1, 'pictures/movies/1-movie-2021-08-04-02-00-18.jpeg', 'Star Wars I: La amenaza fantasma', '1999-03-12', 5, 5),
(2, 'pictures/movies/2-movie-2021-08-04-02-09-18.jpeg', 'Star Wars IV: Una nueva esperanza', '1977-03-12', 5, 5),
(3, 'pictures/movies/3-movie-2021-08-04-18-33-08.jpeg', 'Star Wars V: El imperio contra ataca', '1980-03-12', 4, 5),
(4, 'pictures/movies/4-movie-2021-08-04-18-33-54.jpeg', 'Star Wars VI: el regreso del Jedi', '1983-03-12', 5, 5),
(5, 'pictures/movies/5-movie-2021-08-04-02-02-19.jpeg', 'Star Wars II:El ataque de los clones', '2002-03-12', 4, 5),
(6, 'pictures/movies/6-movie-2021-08-04-02-04-26.jpeg', 'Star Wars III: La venganza de los Sith', '2005-03-12', 4, 5),
(7, 'pictures/movies/7-movie-2021-08-04-18-34-03.jpeg', 'Star Wars VIII: el despertar de la fuerza', '2015-03-12', 3, 5),
(8, 'pictures/movies/8-movie-2021-08-04-18-36-33.jpeg', 'Alicia en el país de las maravillas', '2010-09-15', 3, 6),
(9, 'pictures/movies/9-movie-2021-08-04-18-35-55.jpeg', 'Mandalorian', '2021-09-22', 3, 2),
(11, 'pictures/movies/11-Toy Story-2021-08-22-04-52-45.jpeg', 'Toy Story', '1995-12-09', 4, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personajes`
--

CREATE TABLE `personajes` (
  `id_personaje` int(11) NOT NULL,
  `imagen` varchar(600) DEFAULT NULL,
  `nombre` varchar(250) NOT NULL,
  `edad` int(11) UNSIGNED NOT NULL,
  `peso` decimal(10,2) UNSIGNED NOT NULL,
  `historia` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `personajes`
--

INSERT INTO `personajes` (`id_personaje`, `imagen`, `nombre`, `edad`, `peso`, `historia`) VALUES
(1, 'pictures/characters/1-Chewbacca-2021-08-03-16-13-01.jpeg', 'Chewbacca', 50, '300.00', 'Es un wookiee, un bípedo alto, peludo y robusto, especie inteligente del planeta Kashyyyk. \nChewbacca es el leal amigo y socio de Han Solo, y sirve como copiloto en la nave espacial de Solo, el Halcón Milenario.'),
(2, 'pictures/characters/2-Yoda-2021-08-03-18-53-04.jpeg', 'Yoda', 900, '10.00', 'Yoda era uno de los más renombrados y poderosos maestros Jedi durante toda la historia de la Galaxia,\n y uno de los pocos Jedis de la República Galáctica en sobrevivir hasta la Guerra Civil Galáctica.'),
(3, 'pictures/characters/3-Darth Vader-2021-08-04-18-38-51.jpeg', 'Darth Vader', 30, '60.00', 'Anakin Skywalker, más tarde Darth Vader, es el personaje ficticio central de la famosa saga de Star Wars del director George Lucas. \n   La saga se centra en su conocimiento de la Fuerza, su caída al lado Oscuro y, finalmente, su redención y muerte.'),
(4, 'pictures/characters/4-R2D2-2021-08-04-18-39-02.jpeg', 'R2D2', 100, '200.00', 'Es un droide astromecánico, contraparte y amigo de C-3PO'),
(5, 'pictures/characters/5-Rey-2021-08-03-18-49-19.jpeg', 'Rey', 20, '45.00', ' Rey es una chatarrera solitaria que se enreda en la guerra entre la Resistencia y la Primera Orden. Ella descubre su potencial como usuario de la Fuerza, \nen su búsqueda para buscar al único Maestro Jedi existente Luke Skywalker para salvar la galaxia.'),
(6, 'pictures/characters/6-Alicia-2021-08-03-19-09-08.jpeg', 'Alicia', 19, '50.00', 'Alicia, ahora una joven de 19 años de edad, regresa al País de las Maravillas para encontrar su verdadero destino \ny terminar con el gobierno de terror de la malvada reina Roja.'),
(7, 'pictures/characters/7-El sombrero-2021-08-04-18-38-31.jpeg', 'El sombrerero', 50, '71.40', 'La frase \'loco como un sombrerero\' se refiere al uso de ésta en el siglo XIX. Se deriva del hecho de que los sombrereros acababan efectivamente locos en aquellos tiempos, la causa del mercurio que se empleaba en aquél entonces para tratar la felpa de los sombreros.'),
(11, 'pictures/characters/11-Buzz Lightyear-2021-08-25-04-02-46.jpeg', 'Buzz Lightyear', 30, '0.20', 'Ejerce el rol de un superhéroe espacial de juguete y el de una figura de acción'),
(20, 'pictures/characters/20-Woody-2021-08-22-04-54-24.jpeg', 'Woody', 38, '0.10', 'Ejerce el rol de un sherif de juguete'),
(22, NULL, 'Mike Wazowski', 22, '42.00', 'Es un vivaraz monstruo verde de un solo ojo. Es el mejor amigo de Sulley y su ayudante en la empresa \"Monstruos S.A.\". Es el novio de Celia Mae. También parece ser quien se encarga del papeleo de Roz.'),
(24, NULL, 'Boo', 3, '8.00', 'Boo es una niña que, un día, entró a Monstruópolis, el mundo de los monstruos, en la fábrica de sustos, el edificio donde asustan a los niños para obtener energía a partir de sus gritos.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personajes_peliculas`
--

CREATE TABLE `personajes_peliculas` (
  `pelicula_id` int(11) NOT NULL,
  `personaje_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `personajes_peliculas`
--

INSERT INTO `personajes_peliculas` (`pelicula_id`, `personaje_id`) VALUES
(1, 1),
(1, 3),
(1, 4),
(2, 1),
(2, 2),
(2, 3),
(2, 4),
(3, 1),
(3, 2),
(3, 3),
(3, 4),
(4, 1),
(4, 2),
(4, 3),
(4, 4),
(5, 1),
(5, 2),
(5, 3),
(5, 4),
(6, 1),
(6, 2),
(6, 3),
(6, 4),
(7, 1),
(7, 5),
(8, 6),
(8, 7),
(9, 2),
(11, 11),
(11, 20);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `mail` varchar(100) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `clave` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`mail`, `usuario`, `clave`) VALUES
('alkemy@gmail.com', 'alkemy', 0x3344323645313438323135383343453434323545334533464339363241303842),
('zabalaflorencia.it@gmail.com', 'FlorZ', 0x3935423337313037383134384441363738433945383637303830313645433131);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id_categoria`),
  ADD UNIQUE KEY `nombre_UNIQUE` (`nombre`);

--
-- Indices de la tabla `peliculas`
--
ALTER TABLE `peliculas`
  ADD PRIMARY KEY (`id_pelicula`,`categoria_id`),
  ADD UNIQUE KEY `Titulo_UNIQUE` (`titulo`),
  ADD KEY `fk_pelicula_serie_catetegoria_idx` (`categoria_id`);

--
-- Indices de la tabla `personajes`
--
ALTER TABLE `personajes`
  ADD PRIMARY KEY (`id_personaje`),
  ADD UNIQUE KEY `nombre_UNIQUE` (`nombre`);

--
-- Indices de la tabla `personajes_peliculas`
--
ALTER TABLE `personajes_peliculas`
  ADD PRIMARY KEY (`pelicula_id`,`personaje_id`),
  ADD KEY `fk_personajes_peliculas_series_peliculas_series1_idx` (`pelicula_id`),
  ADD KEY `fk_personajes_peliculas_series_personajes1_idx` (`personaje_id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`mail`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `id_categoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `peliculas`
--
ALTER TABLE `peliculas`
  MODIFY `id_pelicula` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `personajes`
--
ALTER TABLE `personajes`
  MODIFY `id_personaje` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `peliculas`
--
ALTER TABLE `peliculas`
  ADD CONSTRAINT `fk_pelicula_serie_catetegoria` FOREIGN KEY (`categoria_id`) REFERENCES `categorias` (`id_categoria`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `personajes_peliculas`
--
ALTER TABLE `personajes_peliculas`
  ADD CONSTRAINT `fk_personajes_peliculas_series_peliculas_series1` FOREIGN KEY (`pelicula_id`) REFERENCES `peliculas` (`id_pelicula`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_personajes_peliculas_series_personajes1` FOREIGN KEY (`personaje_id`) REFERENCES `personajes` (`id_personaje`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
