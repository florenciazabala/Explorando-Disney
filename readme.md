# Explorando Disney - Challange

El nudo del proyecto se centró en la relación bidireccional y múltiple existente entre los personajes y las películas.
Al acceder a los datos de la película pueden verse el listado de personajes relacionados y de manera inversa al acceder a los datos del personaje pueden verse los títulos de las películas enlas que aparece.
Sin embargo, al momento de crear estas entidades consideré a los personajes como los elementos constitutivos de las películas por lo tanto en primer lugar puede crearse la entidad película y posteriormente al crear los personajes se indica a que películas pertenecen.
Por lo tanto las relaciones pueden manipularse de las siguientes formas:
    - Al crear el personaje: se puede agregar el listado de las películas 
    - Al editarlo: pueden agregarse o cambiarse títulos
    - Adicionalmente agregué otro endpoint que permite manejar la relación desde el lado de las películas, pasando como parámetro el título de la película y en el cuerpo de la petición el listado de nombres de los personajes.

Esta es la forma en la consideré más óptima manejar la relación.

## Documentación 📋

## Ejecución

## Construido con 🛠️
