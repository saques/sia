# SIA - TPE Búsqueda - Grupo 8


-Instalar interfaces
* mvn install:install-file -Dfile=GPS.jar -DgroupId=ar.com.itba.sia -DartifactId=gps -Dversion=1.0 -Dpackaging=jar

-Compilar módulo del problema
* mvn clean package (Esto genera una librería maven con el groupId ar.edu.itba.sia.chainreaction y el artifactId problem)

-Ejecutar engine
* Dentro del módulo engine, hay un archivo App.java que contiene el main. Dentro de dicho código fuente se puede observar como se instancian las heurísticas y el problema.

-Informe y presentación
*Dentro de report/, se encontrará el .pdf del informe, el .tex de la fuente, el .pdf de la presentación y las tablas anexas al informe en formatos excel y .pdf.
