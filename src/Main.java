import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import Dominio.Ciudad;
import Dominio.Equipo;
import Dominio.Partido;
import Estructuras.TablaHash;
import Estructuras.AVL.ArbolAVL;
import Estructuras.Grafo.Grafo;
import Estructuras.Lineales.Lista;
import Tests.SegundaOpcionListar.AVLEspecifico;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static char opcion;
    private static Grafo ciudades = new Grafo();
    private static ArbolAVL equipos = new ArbolAVL();
    private static TablaHash partidos = new TablaHash();

    public static void menu() {
        System.out.println("MENU DE OPCIONES:");
        System.out.println("**************************************************************************");
        System.out.println("|                             1) ABM Ciudades                              |");
        System.out.println("|                              2) ABM Equipos                              |");
        System.out.println("|                            3) Altas Partidos                             |");
        System.out.println("|                        4) Consulta sobre Equipos                         |");
        System.out.println("|                        5) Consulta sobre Partidos                        |");
        System.out.println("|           6) Consulta sobre Viajes desde punto A hasta punto B           |");
        System.out.println("|                   7) Listar Equipos por goles a favor                    |");
        System.out.println("|                            8) Mostrar Sistema                            |");
        System.out.println("|                                 0) Salir                                 |");
        System.out.println("****************************************************************************");

    }

    /*
     * A partir de aca seran operaciones con Ciudades
     */
    private static void ABMciudades() {
        System.out.println("__________________________________________");
        System.out.println("Altas, bajas y modificaciones de Ciudades:");
        boolean exit = false;
        do {
            System.out.println("-------------------------");
            System.out.println("|   1) Crear Ciudades   |");
            System.out.println("|   2) Borrar Ciudades  |");
            System.out.println("| 3) Modificar Ciudades |");
            System.out.println("|        0) Salir       |");
            System.out.println("-------------------------");
            System.out.print("Ingrese la opcion: ");
            opcion = sc.nextLine().charAt(0);
            switch (opcion) {
                case '1':
                    agregarCiudades();
                    break;
                case '2':
                    borrarCiudades();
                    break;
                case '3':
                    modificarCiudades();
                    break;
                case '0':
                    exit = true;
                    System.out.println("__________________________________________");
                    break;
                default:
                    System.out.println("La opcion ingresada es incorrecta");
                    break;
            }
        } while (!exit);
    }

    /*
     * Metodo utilizado para agregar la cantidad de ciudades que se desee
     * Para crear una ciudad son necesarias dos variables:
     * 1) Nombre de la ciudad
     * 2) Si es sede o no (true/false)
     * De por si al crear una ciudad tiene alojamiento disponible
     */
    private static void agregarCiudades() {
        boolean exit = false;
        // Establezco opcion='y' para que inmediatamente me deje agregar una ciudad
        opcion = 'y';
        String nombre;
        char sede;
        do {
            switch (opcion) {
                // Mientras el usuario ingrese 'y' va a seguir ejecutandose
                case 'y':
                    System.out.print("Ingrese el nombre de la ciudad: ");
                    nombre = sc.nextLine();
                    do {
                        // Sede sera un booleano, por lo tanto solo puede ser y/n
                        System.out.print("La ciudad es sede? y/n: ");
                        sede = sc.nextLine().toLowerCase().charAt(0);
                        if (sede != 'y' && sede != 'n') {
                            System.out.println("Ingrese unicamente 'y' para si y 'n' para no");
                        }
                    } while (sede != 'y' && sede != 'n');
                    // Si sede='y' se crea un booleano con true, sino false
                    // Variable necesaria para crear la ciudad
                    boolean esSede = (sede == 'y') ? true : false;
                    Ciudad ciudad = new Ciudad(nombre, true, esSede);
                    if (!ciudades.insertarVertice(ciudad)) {
                        System.out.println("No se pudo crear la ciudad porque ya existe una con ese nombre");
                    } else {
                        escribirArchivo("Se agrego la ciudad: " + nombre);
                    }
                    do {
                        // Luego pregunto si desea continuar
                        System.out.print("Desea continuar? y/n: ");
                        opcion = sc.nextLine().toLowerCase().charAt(0);
                        if (opcion != 'y' && opcion != 'n') {
                            System.out.println("La opcion ingresada es incorrecta");
                        }
                    } while (opcion != 'y' && opcion != 'n');
                    break;
                case 'n':
                    // En caso de no querer continuar se modifica la variable de corte
                    exit = true;
                    break;
                default:
                    // Nunca entra en el default porque esta en un bucle hasta ingresar y/n
                    break;
            }
        } while (!exit);
    }

    /*
     * Emplea la misma logica que el ejercicio anterior pero para eliminar ciudades
     * Solo es necesario el nombre, entra en un bucle mientras 'opcion' sea
     * distinta de 'y' o 'n'
     */
    private static void borrarCiudades() {
        boolean exit = false;
        opcion = 'y';
        String nombre;
        do {
            switch (opcion) {
                case 'y':
                    System.out.print("Ingrese el nombre de la ciudad: ");
                    nombre = sc.nextLine();
                    Ciudad ciudad = new Ciudad(nombre);
                    if (!ciudades.eliminarVertice(ciudad)) {
                        System.out.println("No existe una ciudad con ese nombre");
                    } else {
                        escribirArchivo("Se borro la ciudad: " + nombre);
                    }
                    do {
                        System.out.print("Desea continuar? y/n: ");
                        opcion = sc.nextLine().toLowerCase().charAt(0);
                        if (opcion != 'y' && opcion != 'n') {
                            System.out.println("La opcion ingresada es incorrecta");
                        }
                    } while (opcion != 'y' && opcion != 'n');
                    break;
                case 'n':
                    exit = true;
                    break;
                default:
                    break;
            }
        } while (!exit);
    }

    /*
     * Modifica cosas relacionadas a las ciudades, como puede ser agregar un camino
     * entre una ciudad y otra, eliminarlos, etc
     */
    private static void modificarCiudades() {
        boolean exit = false, aux = false;
        String nombreA = "", nombreB = "";
        Ciudad primerCiudad = null, segundaCiudad = null;
        int tiempo = 0;
        do {
            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
            System.out.println("|     1) Agregar camino    |");
            System.out.println("|    2) Eliminar camino    |");
            System.out.println("| 3) Registrar alojamiento |");
            System.out.println("|         0) Salir         |");
            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
            System.out.print("Ingrese la opcion: ");
            opcion = sc.nextLine().charAt(0);
            // Como tanto para eliminar y agregar necesito el nombre de las dos ciudades
            if (opcion > '0' && opcion < '3') {
                // Si la opcion sea alguna de esas, pido las dos ciudades antes de operar
                System.out.print("Ingrese el nombre de la primer ciudad: ");
                nombreA = sc.nextLine();
                System.out.print("Ingrese el nombre de la segunda ciudad: ");
                nombreB = sc.nextLine();
                primerCiudad = new Ciudad(nombreA);
                segundaCiudad = new Ciudad(nombreB);
            }
            switch (opcion) {
                case '1':
                    do {
                        // El tiempo de vuelo(int) es requerido para crear el camino
                        System.out.print("Ingrese el tiempo de vuelo: ");
                        // Utilizo una expresion regular para verificar que solo sean numeros
                        String respuesta = sc.nextLine();
                        if (respuesta.matches("\\d+")) {
                            tiempo = Integer.parseInt(respuesta);
                            aux = true;
                        } else {
                            System.out.println("Error: El valor ingresado no es un numero.");
                        }
                        // aux es mi variable de corte para cuando ingreso valor correcto
                    } while (!aux);
                    if (!ciudades.insertarArco(primerCiudad, segundaCiudad, tiempo)) {
                        errorM();
                    } else {
                        escribirArchivo("Se agrego un camino entre " + nombreA + " y " + nombreB);
                    }
                    break;
                case '2':
                    if (!ciudades.eliminarArco(primerCiudad, segundaCiudad)) {
                        errorM();
                    } else {
                        escribirArchivo("Se borro el camino entre " + nombreA + " y " + nombreB);
                    }
                    break;
                case '3':
                    System.out.print("Ingrese el nombre de la ciudad: ");
                    nombreA = sc.nextLine();
                    primerCiudad = new Ciudad(nombreA);
                    primerCiudad = (Ciudad) ciudades.recuperarVertice(primerCiudad);
                    if (primerCiudad != null && primerCiudad.isAlojamiento()) {
                        String respuesta;
                        do {
                            System.out.print("Ingrese la cantidad de personas que desee alojar: ");
                            respuesta = sc.nextLine();
                            if (respuesta.matches("\\d+")) {
                                if (!primerCiudad.reservar(Integer.parseInt(respuesta))) {
                                    errorM();
                                } else {
                                    escribirArchivo(
                                            "Se registraron " + respuesta + " personas en la ciudad " + nombreA);
                                }
                                aux = true;
                            } else {
                                System.out.println("Error: El valor ingresado no es un numero positivo.");
                            }
                        } while (!aux);

                    }
                    break;
                case '0':
                    exit = true;
                    break;
                default:
                    System.out.println("La opcion ingresada es incorrecta");
                    break;
            }
        } while (!exit);
    }

    /*
     * Mensaje de error para el metodo de Modificar Ciudades
     */
    private static void errorM() {
        System.out.println("----------------------------------Error:----------------------------------");
        System.out.println("Ocurrio un error, verifique lo siguiente:");
        System.out.println("1) Exista al menos una ciudad");
        System.out.println("2) Ingreso ciudades validas");
        System.out.println("3) Ya exista un camino entre esas ciudades (en caso de querer agregarlo)");
        System.out.println("4) No existia un camino entre esas ciudades (en caso de querer eliminarlo)");
        System.out.println("5) La ciudad ingresada no tiene alojamiento disponible");
        System.out.println("----------------------------------Error:----------------------------------");
    }

    /*
     * Muestra informacion sobre los caminos entre dos ciudades
     */
    public static void consultaViajes() {
        boolean exit = false;
        String nombreA = "", nombreB = "";
        Ciudad ciudadOrigen = null, ciudadDestino = null;
        Lista lista;
        do {
            System.out.println("###################################################");
            System.out.println("|               Entre dos Ciudades:               |");
            System.out.println("|            1) Camino de menor tiempo            |");
            System.out.println("|          2) Camino con menos ciudades           |");
            System.out.println("| 3) Camino de menor tiempo excluyendo una ciudad |");
            System.out.println("|               4) Todos los caminos              |");
            System.out.println("|                    0) Salir                     |");
            System.out.println("###################################################");
            System.out.print("Ingrese la opcion: ");
            opcion = sc.nextLine().charAt(0);
            if (opcion > '0' && opcion < '5') {
                System.out.print("Ingrese el nombre de la ciudad de origen: ");
                nombreA = sc.nextLine();
                ciudadOrigen = new Ciudad(nombreA);
                System.out.print("Ingrese el nombre de la ciudad de destino: ");
                nombreB = sc.nextLine();
                ciudadDestino = new Ciudad(nombreB);
            }
            switch (opcion) {
                case '1':
                    lista = ciudades.caminoMasCorto(ciudadOrigen, ciudadDestino);
                    if (!lista.esVacia()) {
                        System.out.println("El camino mas corto entre " + nombreA + " y " + nombreB + " es:");
                        System.out.println(lista.toString());
                    } else {
                        errorC();
                    }
                    break;
                case '2':
                    lista = ciudades.listarCaminoMinCiudades(ciudadOrigen, ciudadDestino);
                    if (!lista.esVacia()) {
                        System.out.println("El camino con menos ciudades entre " + nombreA + " y " + nombreB + " es:");
                        System.out.println(lista.toString());
                    } else {
                        errorC();
                    }
                    break;
                case '3':
                    System.out.print("Ingrese la ciudad que desea excluir: ");
                    String nombreC = sc.nextLine();
                    Ciudad ciudadExcluida = new Ciudad(nombreC);
                    lista = ciudades.caminoMasCortoSin(ciudadOrigen, ciudadDestino, ciudadExcluida);
                    if (!lista.esVacia()) {
                        System.out.println("El camino mas corto entre " + nombreA + " y " + nombreB + " excluyendo a "
                                + nombreC + " es:");
                        System.out.println(lista.toString());
                    } else {
                        errorC();
                    }
                    break;
                case '4':
                    lista = ciudades.listarCaminos(ciudadOrigen, ciudadDestino);
                    if (!lista.esVacia()) {
                        Lista camino;
                        int i;
                        System.out.println("Los caminos entre " + nombreA + " y " + nombreB + " son:");
                        for (i = 1; i <= lista.longitud(); i++) {
                            camino = (Lista) lista.recuperar(i);
                            System.out.println(camino.toString());
                        }
                        System.out.println("Filtro: Caminos que solo haya alojamiento en la ciudad de destino:");
                        for (i = 1; i <= lista.longitud(); i++) {
                            camino = (Lista) lista.recuperar(i);
                            // Verifico la ciudad destino (la ultima ciudad)
                            Ciudad destino = (Ciudad) camino.recuperar(camino.longitud());
                            if (destino.isAlojamiento())
                                // Si la ciudad de destino tiene aljamiento, muestro el camino
                                System.out.println(camino.toString());
                        }
                        System.out.println("Filtro: Caminos que haya alojamiento en al menos una de las ciudades:");
                        for (i = 1; i <= lista.longitud(); i++) {
                            camino = (Lista) lista.recuperar(i);
                            // Verifico que exista al menos una ciudad de ese camino
                            // Que tenga alojamiento disponible
                            boolean salir = false;
                            int j = 1;
                            // Si encontre al menos una o si llegue al final, salgo
                            while (!salir && j <= camino.longitud()) {
                                Ciudad ciudad = (Ciudad) camino.recuperar(j);
                                // Si al menos una de las ciudades tiene alojamiento muestro el camino
                                if (ciudad.isAlojamiento()) {
                                    salir = true;
                                    System.out.println(camino.toString());
                                }
                                j++;
                            }
                        }
                    } else {
                        errorC();
                    }
                    break;
                case '0':
                    exit = true;
                    break;
                default:
                    System.out.println("La opcion ingresada es incorrecta");
                    break;
            }
        } while (!exit);
    }

    private static void errorC() {
        System.out.println("----------------------------------Atencion:------------------------------------");
        System.out.println("La lista esta vacia, verifique lo siguiente:");
        System.out.println("1) Las ciudades ingresadas sean ciudades validas");
        System.out.println("2) Exista un camino entre esas dos ciudades");
        System.out.println("3) Exista un camino bajo las condiciones requeridas (Excluir la tercer ciudad)");
        System.out.println("----------------------------------Atencion:------------------------------------");
    }

    /*
     * A partir de aca seran operaciones con Equipos
     */
    private static void ABMequipos() {
        System.out.println("__________________________________________");
        System.out.println("Altas, bajas y modificaciones de Equipos:");
        boolean exit = false;
        do {
            System.out.println("------------------------");
            System.out.println("|   1) Crear Equipos   |");
            System.out.println("|  2) Borrar Equipos   |");
            System.out.println("| 3) Modificar Equipos |");
            System.out.println("|       0) Salir       |");
            System.out.println("------------------------");
            System.out.print("Ingrese la opcion: ");
            opcion = sc.nextLine().charAt(0);
            switch (opcion) {
                case '1':
                    agregarEquipos();
                    break;
                case '2':
                    borrarEquipos();
                case '3':
                    modificarEquipos();
                    break;
                case '0':
                    exit = true;
                    System.out.println("__________________________________________");
                    break;
                default:
                    System.out.println("La opcion ingresada es incorrecta");
                    break;
            }
        } while (!exit);
    }

    /*
     * Metodo utilizado para crear uno o mas equipos
     * A tener en cuenta:
     * 1) Los equipos se crean de 0. Sin GF, GC ni puntos
     * 2) Los puntos, gf, gc se van modificando a medida que se juegan partidos
     * 3) Para crear un equipo es necesario que no se repita el nombre del mismo
     */
    private static void agregarEquipos() {
        boolean exit = false;
        // Establezco opcion='y' para que inmediatamente me deje agregar un equipo
        opcion = 'y';
        String nombre, dt;
        char grupo;
        Equipo equipo;
        do {
            switch (opcion) {
                // Mientras el usuario ingrese 'y' va a seguir ejecutandose
                case 'y':
                    System.out.print("Ingrese el nombre del equipo: ");
                    nombre = sc.nextLine();
                    do {
                        // Solo hay 4 grupos (A, B, C, D)
                        System.out.print("Ingrese el grupo: ");
                        grupo = sc.nextLine().toUpperCase().charAt(0);
                        if (grupo != 'A' && grupo != 'B' && grupo != 'C' && grupo != 'D') {
                            System.out.println("Ingrese unicamente (A,B,C,D)");
                        }
                    } while (grupo != 'A' && grupo != 'B' && grupo != 'C' && grupo != 'D');
                    System.out.print("Quien es el DT?: ");
                    dt = sc.nextLine();
                    equipo = new Equipo(nombre, dt, grupo);
                    if (!equipos.insertar(equipo)) {
                        System.out.println("No se pudo crear el equipo porque ya existe uno con ese nombre");
                    } else {
                        escribirArchivo("Se agrego el equipo " + nombre);
                    }
                    do {
                        // Luego pregunto si desea continuar
                        System.out.print("Desea continuar? y/n: ");
                        opcion = sc.nextLine().toLowerCase().charAt(0);
                        if (opcion != 'y' && opcion != 'n') {
                            System.out.println("La opcion ingresada es incorrecta");
                        }
                    } while (opcion != 'y' && opcion != 'n');
                    break;
                case 'n':
                    // En caso de no querer continuar se modifica la variable de corte
                    exit = true;
                    break;
                default:
                    // Nunca entra en el default porque esta en un bucle hasta ingresar y/n
                    break;
            }
        } while (!exit);
    }

    /*
     * En este metodo se podran borrar la cantidad de equipos que se desee
     * Se busca al equipo con ese nombre, en caso de encontrarlo lo elimina
     */
    public static void borrarEquipos() {
        boolean exit = false;
        opcion = 'y';
        String nombre;
        Equipo equipo;
        do {
            switch (opcion) {
                case 'y':
                    System.out.print("Ingrese el nombre del equipo: ");
                    nombre = sc.nextLine();
                    equipo = new Equipo(nombre);
                    equipo = (Equipo) equipos.recuperar(equipo);
                    if (equipo != null) {
                        equipos.eliminar(equipo);
                        escribirArchivo("Se borro el equipo " + nombre);
                    } else {
                        System.out.println("No se encontro un equipo con ese nombre");
                    }
                    do {
                        // Luego pregunto si desea continuar
                        System.out.print("Desea continuar? y/n: ");
                        opcion = sc.nextLine().toLowerCase().charAt(0);
                        if (opcion != 'y' && opcion != 'n') {
                            System.out.println("La opcion ingresada es incorrecta");
                        }
                    } while (opcion != 'y' && opcion != 'n');
                    break;
                case 'n':
                    exit = true;
                    break;
                default:
                    break;
            }
        } while (!exit);
    }

    /*
     * Las unicas modificaciones que se pueden hacer entre equipos son las de
     * cambiar el nombre, grupo o el dt, en caso de querer modificar puntos o goles,
     * debera hacerse a traves de un partido
     */
    public static void modificarEquipos() {
        boolean exit = false;
        Equipo equipo = null;
        String nombreA = "";
        do {
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%");
            System.out.println("|   1) Cambiar nombre   |");
            System.out.println("| 2) Cambiar el tecnico |");
            System.out.println("|  3) Cambiar de grupo  |");
            System.out.println("|        0) Salir       |");
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%");
            System.out.print("Ingrese la opcion: ");
            opcion = sc.nextLine().charAt(0);
            if (opcion > '0' && opcion < '4') {
                System.out.print("Ingrese el nombre del equipo: ");
                nombreA = sc.nextLine();
                equipo = new Equipo(nombreA);
                equipo = (Equipo) equipos.recuperar(equipo);
            }
            switch (opcion) {
                case '1':
                    if (equipo != null) {
                        // Aux solo utilizado para el .log
                        String aux = nombreA;
                        System.out.print("Ingrese el nuevo nombre: ");
                        nombreA = sc.nextLine();
                        // Si desea cambiar el nombre verifico que no haya un equipo con ese nombre
                        Equipo nuevo = new Equipo(nombreA);
                        if (!equipos.pertenece(nuevo)) {
                            // Si no lo hay elimino al equipo, le cambio el nombre y lo agrego
                            equipos.eliminar(equipo);
                            equipo.setNombre(nombreA);
                            equipos.insertar(equipo);
                            escribirArchivo("Se cambio el nombre de " + aux + " por el siguiente: " + nombreA);
                        } else {
                            System.out.println("Ya existe un equipo con ese nombre");
                        }
                    } else {
                        System.out.println("No se encontro un equipo con ese nombre");
                    }
                    break;
                case '2':
                    if (equipo != null) {
                        System.out.print("Ingrese el nombre del nuevo DT: ");
                        String dt = sc.nextLine();
                        escribirArchivo("Se cambio el dt de " + equipo.getNombre() + ". Antes era "
                                + equipo.getEntrenador() + " y ahora es " + dt);
                        equipo.setEntrenador(dt);

                    } else {
                        System.out.println("No se encontro un equipo con ese nombre");
                    }
                    break;
                case '3':
                    if (equipo != null) {
                        char grupo;
                        // Solo hay 4 grupos (A, B, C, D)
                        System.out.print("Ingrese el grupo: ");
                        grupo = sc.nextLine().toUpperCase().charAt(0);
                        if (grupo != 'A' && grupo != 'B' && grupo != 'C' && grupo != 'D') {
                            System.out.println("Ingrese unicamente (A,B,C,D)");
                        } else {
                            escribirArchivo("Se cambio el grupo de " + equipo.getNombre() + ". Antes era "
                                    + equipo.getGrupo() + " ahora es " + grupo);
                            equipo.setGrupo(grupo);
                        }
                    } else {
                        System.out.println("No se encontro un equipo con ese nombre");
                    }
                    break;
                case '0':
                    exit = true;
                    break;
                default:
                    System.out.println("La opcion ingresada es incorrecta");
                    break;
            }
        } while (!exit);

    }

    /*
     * Muestra informacion de los equipos (Nombre, grupo, puntos, GF, GC, Dif gol)
     * 
     * Y tambien muestra una Lista con equipos cuyo nombres esten entre dos palabras
     * Para evitar problemas, se compara envian las palabras en minusculas
     * y se compara a los nombres del equipo en minuscula
     */
    public static void consultaEquipos() {
        boolean exit = false;
        do {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("| 1) Info del equipo  |");
            System.out.println("| 2) Equipos en rango |");
            System.out.println("|      0) Salir       |");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.print("Ingrese la opcion: ");
            opcion = sc.nextLine().charAt(0);
            switch (opcion) {
                case '1':
                    System.out.print("Ingrese el nombre del equipo: ");
                    String nombreA = sc.nextLine();
                    Equipo equipo = new Equipo(nombreA);
                    equipo = (Equipo) equipos.recuperar(equipo);
                    if (equipo != null) {
                        System.out.println(equipo.mostrarInfo());
                    } else {
                        System.out.println("No se encontro un equipo con ese nombre");
                    }
                    break;
                case '2':
                    System.out.print("Ingrese la primer palabra: ");
                    String min = sc.nextLine();
                    System.out.print("Ingrese la segunda palabra: ");
                    String max = sc.nextLine();
                    // Si las cadenas no estan ordenadas, las ordeno
                    if (min.compareTo(max) >= 0) {
                        String aux = min;
                        min = max;
                        max = aux;
                    }
                    // Creo equipo para compararlos de la misma manera (en minuscula)
                    Equipo uno = new Equipo(min), dos = new Equipo(max);
                    System.out.println(equipos.listarRango(uno, dos).toString());
                    System.out.println("La primer palabra es: " + min);
                    System.out.println("La segunda palabra es: " + max);
                    break;
                case '0':
                    exit = true;
                    break;
                default:
                    System.out.println("La opcion ingresada es incorrecta");
                    break;
            }
        } while (!exit);
    }

    /*
     * Dado el AVL de Equipos:
     * Obtiene una TablaHash de Equipos, ordenados por sus goles a favor
     * 
     * Listar() de TablaHash devuelve una Lista de elementos ordenados por su
     * hashCode. En el caso de Equipo, el hashCode son sus GF
     * Como la cantidad de equipos<tamaño de TablaHash la lista ya esta ordenada
     * cuando la devuelve
     * 
     * Para cada elemento de la lista muestra su nombre y la cantidad de GF
     */

    public static void listarPorGF() {
        long startTime = System.nanoTime();
        TablaHash th = equipos.ordenarPorGF();
        Lista lista = th.listar();
        int longitud = lista.longitud();
        for (int i = 1; i <= longitud; i++) {
            Equipo equipo = (Equipo) lista.recuperar(i);
            System.out.println(equipo.mostrarGoles());
        }
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Metodo TablaHash tardo: " + (double) duration / 1000000000 + " segundos");
    }

    /*
     * A partir de aca seran operaciones con Partidos
     */

    /*
     * Metodo para crear partido entre dos equipos
     * Cosas a tener en cuenta:
     * 1) Pueden haber entre 0 y 2 partidos unicamente con dos mismos equipos
     * 2) No importa si se alterna entre mayusculas y minusculas los nombres
     * 3) En caso de ya existir un partido, no lo crea
     * 4) Dos partidos son iguales si se repiten los equipos y la ronda
     * 5) No pueden insertarse partidos por fase de grupo y que los equipos sean de
     * distintos grupos
     * 6) Si un equipo 'A' ya jugo los cuartos, no puede tener otro partido de
     * cuartos por mas que sea con otro equipo distinto al que ya jugo
     */
    public static void establecerPartido() {
        String nombreA, nombreB;
        Equipo primerEquipo, segundoEquipo;
        boolean exit = false;
        opcion = 'y';
        do {
            switch (opcion) {
                case 'y':
                    System.out.print("Ingrese el nombre del primer equipo: ");
                    nombreA = sc.nextLine();
                    System.out.print("Ingrese el nombre del segundo equipo: ");
                    nombreB = sc.nextLine();
                    primerEquipo = new Equipo(nombreA);
                    segundoEquipo = new Equipo(nombreB);
                    primerEquipo = (Equipo) equipos.recuperar(primerEquipo);
                    segundoEquipo = (Equipo) equipos.recuperar(segundoEquipo);
                    System.out.println("Rondas validas: 'grupo' 'cuartos' 'semis' final");
                    System.out.print("Ingrese la ronda: ");
                    String ronda = sc.nextLine().toLowerCase();
                    if (rondaValida(ronda) && equiposValidos(primerEquipo, segundoEquipo)) {
                        // puedeInsertar es un metodo de clase que me indica si es posible crear el
                        // partido dados unos datos basicos
                        boolean puedeInsertar = Partido.puedeInsertar(primerEquipo, segundoEquipo, ronda);
                        if (puedeInsertar) {
                            int resultadoEquipo1 = 0, resultadoEquipo2 = 0;
                            boolean aux = false;
                            String respuesta;
                            do {
                                System.out.print("Ingrese el resultado de: " + nombreA + " en el partido: ");
                                // Utilizo una expresion regular para verificar que solo sean numeros
                                respuesta = sc.nextLine();
                                if (respuesta.matches("\\d+")) {
                                    resultadoEquipo1 = Integer.parseInt(respuesta);
                                    aux = true;
                                } else {
                                    System.out.println("Error: El valor ingresado no es un numero positivo.");
                                }
                            } while (!aux);
                            do {
                                System.out.print("Ingrese el resultado de: " + nombreB + " en el partido: ");
                                respuesta = sc.nextLine();
                                if (respuesta.matches("\\d+")) {
                                    resultadoEquipo2 = Integer.parseInt(respuesta);
                                    aux = true;
                                } else {
                                    System.out.println("Error: El valor ingresado no es un numero positivo.");
                                }
                            } while (!aux);
                            // La ciudad debe ser sede
                            System.out.print("Ingrese el nombre de la ciudad: ");
                            respuesta = sc.nextLine();
                            Ciudad ciudad = new Ciudad(respuesta);
                            ciudad = (Ciudad) ciudades.recuperarVertice(ciudad);
                            if (ciudad != null && ciudad.isSede()) {
                                System.out.print("Ingrese el nombre del estadio: ");
                                respuesta = sc.nextLine();
                                Partido p = new Partido(primerEquipo, segundoEquipo, ronda, ciudad, respuesta,
                                        resultadoEquipo1, resultadoEquipo2);
                                if (!partidos.insertar(p)) {
                                    errorE();
                                } else {
                                    escribirArchivo("Se ingreso un partido entre " + primerEquipo + " y "
                                            + segundoEquipo + " por " + ronda);
                                }
                            } else {
                                errorE();
                            }
                        } else {
                            errorE();
                        }

                    } else {
                        errorE();
                    }
                    do {
                        // Luego pregunto si desea continuar
                        System.out.print("Desea continuar? y/n: ");
                        opcion = sc.nextLine().toLowerCase().charAt(0);
                        if (opcion != 'y' && opcion != 'n') {
                            System.out.println("La opcion ingresada es incorrecta");
                        }
                    } while (opcion != 'y' && opcion != 'n');
                    break;
                case 'n':
                    exit = true;
                    break;
                default:
                    break;
            }
        } while (!exit);

    }

    /*
     * Si el usuario ingreso un string diferente de los establecidos, retorna falso
     */
    private static boolean rondaValida(String ronda) {
        boolean exit = false;
        if (ronda.equals("grupo") || ronda.equals("cuartos") || ronda.equals("semis") || ronda.equals("final")) {
            exit = true;
        }
        return exit;
    }

    /*
     * Verifica que dos equipos no sean nulos y no sean el mismo equipo
     */
    private static boolean equiposValidos(Equipo primerEquipo, Equipo segundoEquipo) {
        return (primerEquipo != null && segundoEquipo != null && !primerEquipo.equals(segundoEquipo));
    }

    /*
     * Mensaje de error para el metodo que crea Partidos
     */
    private static void errorE() {
        System.out.println("----------------------------------Error:----------------------------------");
        System.out.println("Ocurrio un error, verifique lo siguiente:");
        System.out.println("1) Ingreso Equipos validos");
        System.out.println("2) Ingreso una ronda valida");
        System.out.println("3) Ya haya un partido de alguno de los equipos en una ronda eliminatoria");
        System.out.println("Recuerde que si, por ej, Argentina jugo 'cuartos', no puede volver a jugar 'cuartos'");
        System.out.println("4) El partido este repetido");
        System.out.println("5) Si es un partido de 'grupos' ambos equipos deben ser del mismo grupo");
        System.out.println("6) La ciudad ingresada para el partido sea valida y ademas sede");
        System.out.println("7) Los equipos ingresados sean el mismo equipo");
        System.out.println("----------------------------------Error:----------------------------------");
    }

    /*
     * Dados dos equipos, si existen, devuelve los partidos que jugaron entre si
     * 
     * Obs: Independientemente del orden lexicografico, devuelve los partidos
     * jugados entre si (si es que tienen)
     */
    public static void consultaPartidos() {
        String separador = "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%";
        System.out.print("Ingrese el nombre del primer equipo: ");
        String primer = sc.nextLine().toLowerCase();
        System.out.print("Ingrese el nombre del segundo equipo: ");
        String segundo = sc.nextLine().toLowerCase();
        Equipo primerEquipo = new Equipo(primer), segundoEquipo = new Equipo(segundo);
        // Debo recuperarlos por si no ingreso el nombre exactamente como fue registrado
        primerEquipo = (Equipo) equipos.recuperar(primerEquipo);
        segundoEquipo = (Equipo) equipos.recuperar(segundoEquipo);
        if (equiposValidos(primerEquipo, segundoEquipo)) {
            System.out.println(separador);
            // Crea un partido entre ambos equipos
            Partido aux = new Partido(primerEquipo, segundoEquipo);
            // Recuperar devuelve una lista con todos los partidos entre esos equipos
            Lista lista = partidos.recuperar(aux);
            int i = 1;
            while (i <= lista.longitud()) {
                // Para cada partido de la lista, lo muestra
                Partido partido = (Partido) lista.recuperar(i);
                if (partido.verifPartido(aux)) {
                    System.out.println(partido.toString());
                }
                i++;
            }
            System.out.println(separador);

        } else {
            System.out.println(separador);
            System.out.println("Verifique que los nombres sean correctos y que no sean el mismo");
            System.out.println(separador);
        }
    }

    /*
     * Este metodo es solo usado para Testear. Segun las pruebas es ineficiente
     * 
     * Dado el AVL de equipos que tengo, creo un AVL Especifico
     * Mi AVL Especifico solo puede insertar elementos
     * Se utiliza un metodo similar al de 'clone' llamado 'transformar'
     * 
     * El insertar de AVL Especifico ya hace los casteos necesarios:
     * Entra por parametro un Comparable (Equipo) y entonces lo castea a Equipo y
     * lugeo con el Equipo creo una instancia de mi clase 'EquipoGoles'
     * que solo guarda el nombre del equipo y sus GF.
     * El compareTo de esta nueva clase es justamente con los goles a favor.
     */
    public static void listarPorGF2() {
        long startTime = System.nanoTime();

        AVLEspecifico arbol = equipos.transformar();
        Lista l = arbol.listar();
        System.out.println(l.toString());
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Metodo AVL tardo: " + (double) duration / 1000000000 + " segundos");
        System.out.println("-------------------------------------");
    }

    public static void mostrarDatos() {
        System.out.println("******************Ciudades************************");
        System.out.println(ciudades.toString());
        System.out.println("******************Ciudades************************");
        System.out.println("%%%%%%%%%%%%%%%%%% Equipos %%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println(equipos.toString());
        System.out.println("%%%%%%%%%%%%%%%%%% Equipos %%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("-------------------Partidos-----------------------");
        System.out.println(partidos.toString());
        System.out.println("-------------------Partidos-----------------------");
    }

    public static void main(String[] args) {
        boolean exit = false;
        String salida = "salida.txt";
        // Este try-catch de filewriter es para vaciar a salida.txt cada vez que inicio
        // el programa
        try {
            FileWriter fw = new FileWriter(salida);
            fw.close();
        } catch (IOException e) {
            System.out.println("Ocurrió un error al vaciar el archivo");
            e.printStackTrace();
        }
        String entrada = "cargaInicial.txt";
        leerArchivo(entrada);
        escribirArchivo("Se termino la carga inicial");
        escribirArchivo("EQUIPOS:");
        escribirArchivo(equipos.toString());
        escribirArchivo("PARTIDOS:");
        escribirArchivo(partidos.toString());
        escribirArchivo("CIUDADES:");
        escribirArchivo(ciudades.toString());
        escribirArchivo("\n" + "A partir de aca se ingresaran las modificaciones:");
        do {
            menu();
            System.out.print("Ingrese la opcion: ");
            opcion = sc.nextLine().charAt(0);
            switch (opcion) {
                case '1':
                    ABMciudades();
                    break;
                case '2':
                    ABMequipos();
                    break;
                case '3':
                    establecerPartido();
                    break;
                case '4':
                    consultaEquipos();
                    break;
                case '5':
                    consultaPartidos();
                    break;
                case '6':
                    consultaViajes();
                    break;
                case '7':
                    listarPorGF(); // Metodo 1(Hash)
                    listarPorGF2(); // Metodo 2(AVL)
                    break;
                case '8':
                    mostrarDatos();
                    break;
                case '0':
                    System.out.println("--------Finalizado--------");
                    exit = true;
                    escribirArchivo("EQUIPOS:");
                    escribirArchivo(equipos.toString());
                    escribirArchivo("PARTIDOS:");
                    escribirArchivo(partidos.toString());
                    escribirArchivo("CIUDADES:");
                    escribirArchivo(ciudades.toString());
                    break;
                default:
                    System.out.println("La opcion ingresada es incorrecta");
                    break;
            }

        } while (!exit);
    }

    /*
     * De aca en adelante codigo para leer archivo
     */
    private static void leerArchivo(String ruta) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(ruta));
            String linea;

            while ((linea = br.readLine()) != null) {
                // System.out.println("Esta es mi linea: " + linea);
                procesarLinea(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static void procesarLinea(String linea) {
        // Las lineas son de la forma: TIPO: DATOS;DATOS
        char tipo = linea.charAt(0); // Toma el primer caracter
        String datos = linea.substring(2); // Toma el resto despues de "tipo:"

        StringTokenizer st = new StringTokenizer(datos, ";");
        Ciudad ciudad;
        String nombreCiudad;
        switch (tipo) {
            case 'E':
                String nombreEquipo = st.nextToken().trim();
                String nombreDT = st.nextToken().trim();
                char grupo = st.nextToken().toUpperCase().trim().charAt(0);
                Equipo equipo = new Equipo(nombreEquipo, nombreDT, grupo);
                if (!equipos.insertar(equipo)) {
                    System.out.println("No se inserto la linea " + linea);
                    System.out.println(false + " No se ingreso el equipo " + nombreEquipo);
                }
                break;
            case 'P':
                String equipo1 = st.nextToken().trim();
                String equipo2 = st.nextToken().trim();
                String ronda = st.nextToken().toLowerCase().trim();
                nombreCiudad = st.nextToken().trim();
                Equipo primerEquipo = (Equipo) equipos.recuperar(new Equipo(equipo1));
                Equipo segundoEquipo = (Equipo) equipos.recuperar(new Equipo(equipo2));
                ciudad = (Ciudad) ciudades.recuperarVertice(new Ciudad(nombreCiudad));
                if (equiposValidos(primerEquipo, segundoEquipo)) {
                    if (ciudad != null && ciudad.isSede()) {
                        String estadio = st.nextToken().trim();
                        int golesEquipo1 = Integer.parseInt(st.nextToken().trim());
                        int golesEquipo2 = Integer.parseInt(st.nextToken().trim());
                        Partido partido = new Partido(primerEquipo, segundoEquipo, ronda, ciudad, estadio, golesEquipo1,
                                golesEquipo2);
                        if (!partidos.insertar(partido)) {
                            System.out.println("No se inserto la linea " + linea);
                            System.out.println(false + " No se ingreso el partido entre: " + equipo1 + " y " + equipo2);
                        }
                    } else {
                        System.out.println("No se inserto la linea " + linea);
                        System.out.println(false + " No se encontro la ciudad o no es sede");
                    }
                } else {
                    System.out.println("No se inserto la linea " + linea);
                    System.out.println(false + " No se encontro a alguno de los dos equipos" + primerEquipo + "y "
                            + segundoEquipo);
                }
                break;
            case 'C':
                nombreCiudad = st.nextToken().trim();
                boolean alojamiento = Boolean.parseBoolean(st.nextToken().trim());
                boolean sede = Boolean.parseBoolean(st.nextToken().trim());
                ciudad = new Ciudad(nombreCiudad, alojamiento, sede);
                if (!ciudades.insertarVertice(ciudad)) {
                    System.out.println("No se inserto la linea " + linea);
                    System.out.println(false + " Ya hay una ciudad con este nombre: " + nombreCiudad);
                }
                break;
            case 'R':
                nombreCiudad = st.nextToken().trim();
                String nombreCiudad2 = st.nextToken().trim();
                ciudad = new Ciudad(nombreCiudad);
                Ciudad ciudad2 = new Ciudad(nombreCiudad2);
                int vuelo = Integer.parseInt(st.nextToken().trim());
                if (!ciudades.insertarArco(ciudad, ciudad2, vuelo)) {
                    System.out.println("No se inserto la linea " + linea);
                    System.out.println(false + " No se inserto una ruta entre: " + ciudad + " y " + ciudad2);
                }
                break;
            default:
                System.out.println("No se inserto la linea " + linea);
                System.out.println(false + " Tipo desconocido: " + tipo);
                break;
        }
    }

    /*
     * De aca en adelante codigo para escribir en un archivo
     */
    private static void escribirArchivo(String cadena) {
        String salida = "salida.txt";
        try {
            FileWriter fw = new FileWriter(salida, true);
            // Escribirmos la cadena y hacemos un salto de linea para la siguiente
            fw.write(cadena + "\n");
            fw.close();
        } catch (IOException e) {
            // No deberia pasar pero lo dejo para testear
            System.out.println("Ocurrio un error al escribir en el archivo");
            e.printStackTrace();
        }
    }
}
