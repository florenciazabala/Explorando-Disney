# Explorando Disney - Challange

El nudo del proyecto se centró en la relación bidireccional y múltiple existente entre los personajes y las películas.
Al acceder a los datos de la película pueden verse el listado de personajes relacionados y de manera inversa al acceder a los datos del personaje pueden verse los títulos de las películas en las que participa.
Sin embargo, al momento de crear estas entidades consideré a los personajes como los elementos constitutivos de las películas por lo tanto en primer lugar puede crearse la entidad película y posteriormente al crear los personajes se indica a que películas pertenecen.

Por lo tanto, las relaciones pueden manipularse de las siguientes maneras:

* Al crear el personaje: se puede agregar el listado de las películas relacionadas
* Al editarlo: pueden agregarse o removerse títulos
* Adicionalmente agregué otro endpoint que permite manejar la relación desde el lado de las películas, pasando como parámetro el título de la película y en el cuerpo de la petición el listado de nombres de los personajes

Esta es la forma en la consideré más óptima manejar la relación.

## Documentación 📋

* Postman: en la carpeta 'Postman' se ecuentra el archivo .json todos los endpoints documentados.
* Swagger: una vez desplegado el proyecto se podrá acceder mediante la url: http://localhost:8080/exploring-disney-world/api/swagger-ui.html <br>
  *Swagger no soporta hasta el momento endpoints iguales con diferentes parámetros por lo que no pueden probarse correctamente todos los filtros de búsqueda creados.*

## Ejecución 🔧

     java -jar -Dspring.profiles.active=pdn build/libs/explorando-disney-1.0.jar
> 
## Construido con 🛠️

* Spring
* Gradle
* MySQL