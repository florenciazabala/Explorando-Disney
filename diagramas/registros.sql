
USE `mundo-disney` ;
-- Inserts

INSERT INTO catetegorias (nombre) VALUES("comedia");
INSERT INTO catetegorias (nombre) VALUES("acción/ aventuras");
INSERT INTO catetegorias (nombre) VALUES("ciencia ficción");
INSERT INTO catetegorias (nombre) VALUES("documental");
INSERT INTO catetegorias (nombre) VALUES("infantil");
INSERT INTO catetegorias (nombre) VALUES("fantasia");

INSERT INTO peliculas (titulo,fecha_creacion,calificacion,categoria_id) values ("Star Wars IV: Una nueva esperanza","1977-03-12",5,5);
INSERT INTO peliculas (titulo,fecha_creacion,calificacion,categoria_id) values ("Star Wars V: El imperio contra ataca","1980-03-12",4,5);
INSERT INTO peliculas (titulo,fecha_creacion,calificacion,categoria_id) values ("Star Wars VI: el regreso del Jedi","1983-03-12",5,5);
INSERT INTO peliculas (titulo,fecha_creacion,calificacion,categoria_id) values ("Star Wars I: La amenaza fantasma","1999-03-12",5,5);
INSERT INTO peliculas (titulo,fecha_creacion,calificacion,categoria_id) values ("Star Wars II:El ataque de los clones","2002-03-12",4,5);
INSERT INTO peliculas (titulo,fecha_creacion,calificacion,categoria_id) values ("Star Wars III: La venganza de los Sith","2005-03-12",4,5);
INSERT INTO peliculas (titulo,fecha_creacion,calificacion,categoria_id) values ("Star Wars VIII: el despertar de la fuerza","2015-03-12",3,5);
INSERT INTO peliculas (titulo,fecha_creacion,calificacion,categoria_id) values ("Alicia en el país de las maravillas","2010-09-22",3,6);
INSERT INTO peliculas (titulo,fecha_creacion,calificacion,categoria_id) values ("Mandalorian","2021-09-22",3,2);

INSERT INTO personajes (nombre,edad,peso,historia) VALUES ("Chewbacca",50,300,"Es un wookiee, un bípedo alto, peludo y robusto, especie inteligente del planeta Kashyyyk. 
Chewbacca es el leal amigo y socio de Han Solo, y sirve como copiloto en la nave espacial de Solo, el Halcón Milenario.");
INSERT INTO personajes (nombre,edad,peso,historia) VALUES("Yoda",900,10,"Yoda era uno de los más renombrados y poderosos maestros Jedi durante toda la historia de la Galaxia,
 y uno de los pocos Jedis de la República Galáctica en sobrevivir hasta la Guerra Civil Galáctica.");
 INSERT INTO personajes (nombre,edad,peso,historia) VALUES("Darth Vader",30,60,"Anakin Skywalker, más tarde Darth Vader, es el personaje ficticio central de la famosa saga de Star Wars del director George Lucas. 
   La saga se centra en su conocimiento de la Fuerza, su caída al lado Oscuro y, finalmente, su redención y muerte.");
INSERT INTO personajes (nombre,edad,peso,historia) VALUES("Rey",20,45," Rey es una chatarrera solitaria que se enreda en la guerra entre la Resistencia y la Primera Orden. Ella descubre su potencial como usuario de la Fuerza, 
en su búsqueda para buscar al único Maestro Jedi existente Luke Skywalker para salvar la galaxia.");
INSERT INTO personajes (nombre,edad,peso,historia) VALUES("R2D2",100,200,"Es un droide astromecánico, contraparte y amigo de C-3PO");
INSERT INTO personajes (nombre,edad,peso,historia) VALUES("Alicia",19,50,"Alicia, ahora una joven de 19 años de edad, regresa al País de las Maravillas para encontrar su verdadero destino 
y terminar con el gobierno de terror de la malvada reina Roja.");
INSERT INTO personajes (nombre,edad,peso,historia) VALUES("El sombrero",50,70,"La frase 'loco como un sombrerero' se refiere al uso de ésta en el siglo XIX. Se deriva del hecho de que los sombrereros acababan efectivamente locos en aquellos tiempos, 
a causa del mercurio que se empleaba en aquél entonces para tratar la felpa de los sombreros.");

-- Chewbacca
INSERT INTO personajes_peliculas (pelicula_id,personaje_id) VALUES (1,1);
INSERT INTO personajes_peliculas (pelicula_id,personaje_id) VALUES (2,1);
INSERT INTO personajes_peliculas (pelicula_id,personaje_id) VALUES (3,1);
INSERT INTO personajes_peliculas (pelicula_id,personaje_id) VALUES (4,1);
INSERT INTO personajes_peliculas (pelicula_id,personaje_id) VALUES (5,1);
INSERT INTO personajes_peliculas (pelicula_id,personaje_id) VALUES (6,1);
INSERT INTO personajes_peliculas (pelicula_id,personaje_id) VALUES (7,1);

-- Yoda
INSERT INTO personajes_peliculas (pelicula_id,personaje_id) VALUES (1,2);
INSERT INTO personajes_peliculas (pelicula_id,personaje_id) VALUES (2,2);
INSERT INTO personajes_peliculas (pelicula_id,personaje_id) VALUES (3,2);
INSERT INTO personajes_peliculas (pelicula_id,personaje_id) VALUES (4,2);
INSERT INTO personajes_peliculas (pelicula_id,personaje_id) VALUES (5,2);
INSERT INTO personajes_peliculas (pelicula_id,personaje_id) VALUES (6,2);
INSERT INTO personajes_peliculas (pelicula_id,personaje_id) VALUES (9,2);

-- Darth Vader
INSERT INTO personajes_peliculas (pelicula_id,personaje_id) VALUES (1,3);
INSERT INTO personajes_peliculas (pelicula_id,personaje_id) VALUES (2,3);
INSERT INTO personajes_peliculas (pelicula_id,personaje_id) VALUES (3,3);
INSERT INTO personajes_peliculas (pelicula_id,personaje_id) VALUES (4,3);
INSERT INTO personajes_peliculas (pelicula_id,personaje_id) VALUES (5,3);
INSERT INTO personajes_peliculas (pelicula_id,personaje_id) VALUES (6,3);

-- R2D2

INSERT INTO personajes_peliculas (pelicula_id,personaje_id) VALUES (1,4);
INSERT INTO personajes_peliculas (pelicula_id,personaje_id) VALUES (2,4);
INSERT INTO personajes_peliculas (pelicula_id,personaje_id) VALUES (3,4);
INSERT INTO personajes_peliculas (pelicula_id,personaje_id) VALUES (4,4);
INSERT INTO personajes_peliculas (pelicula_id,personaje_id) VALUES (5,4);
INSERT INTO personajes_peliculas (pelicula_id,personaje_id) VALUES (6,4);

-- Rey

INSERT INTO personajes_peliculas (pelicula_id,personaje_id) VALUES (7,5);

-- Alicia
INSERT INTO personajes_peliculas (pelicula_id,personaje_id) VALUES (8,6);
-- El sombrerero
INSERT INTO personajes_peliculas (pelicula_id,personaje_id) VALUES (8,7);

-- Detalle personaje
SELECT pj.imagen,pj.nombre,pj.edad,pj.peso,pj.historia,p.titulo from peliculas as p 
join fetch personajes_peliculas as pp
on p.id_pelicula=pp.pelicula_id 
join fetch personajes as pj
on pj.id_personaje=pp.personaje_id where pj.id_personaje=1;

SELECT * from peliculas as p 
join fetch personajes_peliculas as pp
on p.id_pelicula=pp.pelicula_id 
join fetch personajes as pj
on pj.id_personaje=pp.personaje_id where pj.id_personaje=1;

-- Buscar personajes por peliculas en las que participaron
SELECT pj.imagen, pj.nombre from peliculas as p 
join personajes_peliculas as pp
on p.id_pelicula=pp.pelicula_id join 
 personajes as pj
on pj.id_personaje=pp.personaje_id where p.titulo=?;

-- Detalle personaje
SELECT p.titulo,pj.nombre from peliculas as p 
join personajes_peliculas as pp
on p.id_pelicula=pp.pelicula_id join 
 personajes as pj
on pj.id_personaje=pp.personaje_id where pj.id_personaje=?;
