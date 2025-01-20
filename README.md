# Copa América 2024 - Sistema de Información

Este proyecto implementa un sistema para la **Copa América de Fútbol 2024** a disputarse en **Estados Unidos**. El objetivo principal es ofrecer información a los hinchas sobre el evento y sugerir rutas de viaje entre ciudades para que puedan asistir a los partidos de su equipo favorito.

El sistema gestiona información sobre **ciudades**, **equipos**, **partidos**, y **rutas aéreas** utilizando estructuras de datos eficientes como grafos etiquetados, árboles AVL y tablas hash.

---

## Funcionalidades

### Gestión de Datos
- **Ciudades**: 
  - Almacena nombre, disponibilidad de alojamiento y si es sede de la Copa.
  - Altas, bajas y modificaciones.
- **Equipos**:
  - Almacena nombre, director técnico, grupo, puntos, goles a favor, goles en contra y diferencia de goles.
  - Altas, bajas y modificaciones.
- **Partidos**:
  - Registra dos equipos, instancia, ciudad, estadio y resultado del partido.
  - Alta de partidos.

### Consultas
1. **Equipos**:
   - Dado un país, muestra puntos, goles a favor, goles en contra y diferencia de goles.
   - Listado de equipos cuyo nombre esté en un rango alfabético.
2. **Partidos**:
   - Consulta si dos equipos jugaron entre sí y muestra los resultados.
3. **Viajes**:
   - Ruta más corta (en tiempo) entre dos ciudades.
   - Ruta con la menor cantidad de escalas.
   - Ruta más corta que excluye una ciudad específica.
   - Todos los caminos posibles entre dos ciudades, filtrando aquellos con disponibilidad de alojamiento en el destino o en escalas intermedias.

### Listados y Debugging
- **Equipos ordenados por goles a favor** (estructuras auxiliares para eficiencia).
- **Mostrar sistema completo**: permite verificar las estructuras cargadas (grafo, AVL, HashMap).

---

## Estructuras de Datos Utilizadas

1. **Árbol AVL**:
   - Para almacenar los equipos, ordenados alfabéticamente por nombre.
   - Garantiza balanceo y eficiencia en operaciones de búsqueda, inserción y eliminación.

2. **Grafo Etiquetado**:
   - Representa el mapa de ciudades con rutas aéreas y tiempos de vuelo.
   - Las rutas tienen etiquetas indicando el tiempo estimado de vuelo en minutos.

3. **Tabla Hash**:
   - Implementada con hash abierto para almacenar partidos.
   - Las claves son los equipos participantes y el valor incluye los datos del partido.

4. **Archivos de Texto**:
   - Carga inicial desde un archivo con formato específico.
   - Generación de un archivo `.log` para registrar el estado del sistema y las operaciones realizadas.

---

## Requisitos de la Carga Inicial

- **16 equipos** (en orden desordenado para provocar rotaciones en el AVL).
- **20 partidos**.
- **20 ciudades**.
- **40 rutas aéreas** (grafo etiquetado).

---

## Instrucciones de Uso

1. **Ejecución del Programa**:
   - Al iniciar el programa, se carga automáticamente la información desde un archivo de texto (formato especificado más abajo).
   - Se muestra un menú interactivo que permite realizar las operaciones especificadas.

2. **Formato del Archivo de Carga**:
   - **Equipo**: `E: NOMBRE; DT; GRUPO`
   - **Partido**: `P: EQ1; EQ2; INSTANCIA; CIUDAD; ESTADIO; GOLES1; GOLES2`
   - **Ciudad**: `C: NOMBRE; ALOJAMIENTO; SEDE`
   - **Ruta**: `R: CIUDAD_ORIGEN; CIUDAD_DESTINO; TIEMPO`

   **Ejemplo**:
   ```plaintext
   E: ARGENTINA; SCALONI; A
   P: ARGENTINA; CHILE; GRUPO; MIAMI; HARD ROCK STADIUM; 2; 1
   C: MIAMI; TRUE; TRUE
   R: MIAMI; ORLANDO; 60
