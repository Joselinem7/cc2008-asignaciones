CC2008 - Programacion Orientada a Objetos

Contenido:

ejercicio-1: venta de boletos The Eras Tour.
ejercicio-2: editor de imagenes con arreglos bidimensionales de objetos.
laboratorio-2: mision espacial, arreglo de modulos, ArrayList de planetas y excepciones.
ejercicio-5: UVG Card Battle, herencia, polimorfismo y MVC.

Cada carpeta contiene:
- analisis-y-diseno.pdf: analisis y diagramas UML.
- src/: archivos .java.
- LEEME.txt: instrucciones para compilar y ejecutar.
- analisis-editable.txt: texto del analisis para facilitar su revision.

El editor incluye la imagen base, los resultados de los filtros y comandos.txt.
Su punto de partida es https://github.com/sebdeveloper6952/cc2008-exercise-2

Cada trabajo se compila por separado, desde su propia carpeta:
mkdir -p bin
javac -encoding UTF-8 -d bin src/*.java
java -cp bin Main

En el editor deben agregarse los argumentos de imagen y filtro. Consultar su LEEME.
Se requiere Java 17 o posterior. No hay dependencias externas.

Los datos se mantienen en memoria durante la ejecucion.
El ejercicio 6 no se incluye porque ya estaba resuelto.
