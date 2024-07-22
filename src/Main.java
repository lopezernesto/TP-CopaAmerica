
import java.util.Random;
import java.util.Scanner;
import Dominio.Equipo;
import Dominio.Partido;
import Estructuras.TablaHash;
import Estructuras.AVL.ArbolAVL;
import Estructuras.Grafo.Grafo;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static char opcion;
    private static Grafo ciudades = new Grafo();
    private static ArbolAVL equipos = new ArbolAVL();
    private static TablaHash partidos = new TablaHash();

    public static void menu() {
        System.out.println("MENU DE OPCIONES:");
        // System.out.println("*****************************************************");
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("|                             1) ABM Ciudades                              |");
        System.out.println("|                              2) ABM Equipos                              |");
        System.out.println("|                            3) Altas Partidos                             |");
        System.out.println("|                        4) Consulta sobre Equipos                         |");
        System.out.println("|           5) Consulta sobre Partidos                                     |");
        System.out.println("|           6) Consulta sobre Viajes desde punto A hasta punto B           |");
        System.out.println("|           7) Listar Equipos por goles a favor                            |");
        System.out.println("|           8) Mostrar Sistema                                             |");
        System.out.println("|           0) Salir                                                       |");
        // System.out.println("-----------------------------------------------------");
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
            System.out.println("______________________");
            System.out.println("1) Crear Ciudades");
            System.out.println("2) Borrar Ciudades");
            System.out.println("3) Modificar Ciudades");
            System.out.println("0) Salir");
            System.out.println("______________________");
            System.out.print("Ingrese la opcion: ");
            opcion = sc.next().charAt(0);
            switch (opcion) {
                case '1':
                    agregarCiudades();
                    break;
                case '2':
                    borrarCiudades();
                case '3':
                    modificarCiudades();
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
     * Metodo utilizado para agregar la cantidad de ciudades que se desee
     * Para crear una ciudad son necesarias dos variables:
     * 1) Nombre de la ciudad
     * 2) Si es sede o no (true/false)
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
                    nombre = sc.next();
                    do {
                        // Sede sera un booleano, por lo tanto solo puede ser y/n
                        System.out.print("La ciudad es sede? y/n: ");
                        sede = sc.next().toLowerCase().charAt(0);
                        if (sede != 'y' && sede != 'n') {
                            System.out.println("Ingrese unicamente 'y' para si y 'n' para no");
                        }
                    } while (sede != 'y' && sede != 'n');
                    // Si sede='y' se crea un booleano con true, sino false
                    // Variable necesaria para crear la ciudad
                    boolean aux = (sede == 'y') ? true : false;
                    if (!ciudades.insertarVertice(nombre, aux)) {
                        System.out.println("No se pudo crear la ciudad porque ya existe una con ese nombre");
                    }
                    do {
                        // Luego pregunto si desea continuar
                        System.out.print("Desea continuar? y/n: ");
                        opcion = sc.next().toLowerCase().charAt(0);
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
                    nombre = sc.next();
                    if (!ciudades.eliminarVertice(nombre)) {
                        System.out.println("No existe una ciudad con ese nombre");
                    }
                    do {
                        System.out.print("Desea continuar? y/n: ");
                        opcion = sc.next().toLowerCase().charAt(0);
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
        int tiempo = 0;
        do {
            System.out.println("______________________");
            System.out.println("1) Agregar camino");
            System.out.println("2) Eliminar camino");
            System.out.println("0) Salir");
            System.out.println("______________________");
            System.out.print("Ingrese la opcion: ");
            opcion = sc.next().charAt(0);
            // Como tanto para eliminar y agregar necesito el nombre de las dos ciudades
            if (opcion > '0' && opcion < '3') {
                // Si la opcion sea alguna de esas, pido las dos ciudades antes de operar
                System.out.print("Ingrese el nombre de la primer ciudad: ");
                nombreA = sc.next();
                System.out.print("Ingrese el nombre de la segunda ciudad: ");
                nombreB = sc.next();
            }
            switch (opcion) {
                case '1':
                    do {
                        // El tiempo de vuelo(int) es requerido para crear el camino
                        System.out.print("Ingrese el tiempo de vuelo: ");
                        // Utilizo una expresion regular para verificar que solo sean numeros
                        String respuesta = sc.next();
                        if (respuesta.matches("\\d+")) {
                            tiempo = Integer.parseInt(respuesta);
                            aux = true;
                        } else {
                            System.out.println("Error: El valor ingresado no es un numero.");
                        }
                    } while (!aux);
                    if (!ciudades.insertarArco(nombreA, nombreB, tiempo)) {
                        errorM();
                    }
                    break;
                case '2':
                    if (!ciudades.eliminarArco(nombreA, nombreB)) {
                        errorM();
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
        System.out.println("----------------------------------Error:----------------------------------");
    }

    /*
     * A partir de aca seran operaciones con Equipos
     */
    private static void ABMequipos() {
        System.out.println("__________________________________________");
        System.out.println("Altas, bajas y modificaciones de Equipos:");
        boolean exit = false;
        do {
            System.out.println("______________________");
            System.out.println("1) Crear Equipos");
            System.out.println("2) Borrar Equipos");
            System.out.println("3) Modificar Equipos");
            System.out.println("0) Salir");
            System.out.println("______________________");
            System.out.print("Ingrese la opcion: ");
            opcion = sc.next().charAt(0);
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
        Equipo e;
        do {
            switch (opcion) {
                // Mientras el usuario ingrese 'y' va a seguir ejecutandose
                case 'y':
                    System.out.print("Ingrese el nombre del equipo: ");
                    nombre = sc.next();
                    do {
                        // Solo hay 4 grupos (A, B, C, D)
                        System.out.print("Ingrese el grupo: ");
                        grupo = sc.next().toUpperCase().charAt(0);
                        if (grupo != 'A' && grupo != 'B' && grupo != 'C' && grupo != 'D') {
                            System.out.println("Ingrese unicamente (A,B,C,D)");
                        }
                    } while (grupo != 'A' && grupo != 'B' && grupo != 'C' && grupo != 'D');
                    System.out.print("Quien es el DT?: ");
                    dt = sc.next();
                    e = new Equipo(nombre, dt, grupo);
                    if (!equipos.insertar(e)) {
                        System.out.println("No se pudo crear el equipo porque ya existe uno con ese nombre");
                    }
                    do {
                        // Luego pregunto si desea continuar
                        System.out.print("Desea continuar? y/n: ");
                        opcion = sc.next().toLowerCase().charAt(0);
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
        do {
            switch (opcion) {
                case 'y':
                    System.out.print("Ingrese el nombre del equipo: ");
                    nombre = sc.next();
                    Equipo e = equipos.recuperar(nombre.toLowerCase());
                    if (e != null) {
                        equipos.eliminar(e);
                    } else {
                        System.out.println("No se encontro un equipo con ese nombre");
                    }
                    do {
                        // Luego pregunto si desea continuar
                        System.out.print("Desea continuar? y/n: ");
                        opcion = sc.next().toLowerCase().charAt(0);
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
        Equipo e = null;
        String nombreA = "";
        do {
            System.out.println("______________________");
            System.out.println("1) Cambiar nombre");
            System.out.println("2) Cambiar el tecnico");
            System.out.println("3) Cambiar de grupo");
            System.out.println("0) Salir");
            System.out.println("______________________");
            System.out.print("Ingrese la opcion: ");
            opcion = sc.next().charAt(0);
            if (opcion > '0' && opcion < '4') {
                System.out.print("Ingrese el nombre del equipo: ");
                nombreA = sc.next();
                e = equipos.recuperar(nombreA.toLowerCase());
            }
            switch (opcion) {
                case '1':
                    // 'e' es el equipo original
                    if (e != null) {
                        System.out.print("Ingrese el nuevo nombre: ");
                        nombreA = sc.next();
                        // Si desea cambiar el nombre verifico que no haya un equipo con ese nombre
                        if (!equipos.pertenece(nombreA.toLowerCase())) {
                            // Si no lo hay elimino al equipo, le cambio el nombre y lo agrego
                            equipos.eliminar(e);
                            e.setNombre(nombreA);
                            equipos.insertar(e);
                        } else {
                            System.out.println("Ya existe un equipo con ese nombre");
                        }
                    } else {
                        System.out.println("No se encontro un equipo con ese nombre");
                    }
                    break;
                case '2':
                    if (e != null) {
                        System.out.print("Ingrese el nombre del nuevo DT: ");
                        String dt = sc.next();
                        e.setEntrenador(dt);
                    } else {
                        System.out.println("No se encontro un equipo con ese nombre");
                    }
                    break;
                case '3':
                    if (e != null) {
                        char grupo;
                        // Solo hay 4 grupos (A, B, C, D)
                        System.out.print("Ingrese el grupo: ");
                        grupo = sc.next().toUpperCase().charAt(0);
                        if (grupo != 'A' && grupo != 'B' && grupo != 'C' && grupo != 'D') {
                            System.out.println("Ingrese unicamente (A,B,C,D)");
                        } else {
                            e.setGrupo(grupo);
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
            System.out.println("______________________");
            System.out.println("1) Info del equipo");
            System.out.println("2) Equipos en rango");
            System.out.println("0) Salir");
            System.out.println("______________________");
            System.out.print("Ingrese la opcion: ");
            opcion = sc.next().charAt(0);
            switch (opcion) {
                case '1':
                    System.out.print("Ingrese el nombre del equipo: ");
                    String nombreA = sc.next();
                    Equipo e;
                    e = equipos.recuperar(nombreA.toLowerCase());
                    if (e != null) {
                        System.out.println(e.toString());
                    } else {
                        System.out.println("No se encontro un equipo con ese nombre");
                    }
                    break;
                case '2':
                    System.out.print("Ingrese la primer palabra: ");
                    String min = sc.next();
                    System.out.print("Ingrese la segunda palabra: ");
                    String max = sc.next();
                    if (min.compareTo(max) >= 0) {
                        String aux = min;
                        min = max;
                        max = aux;
                    }
                    // Se envia min y max en minuscula
                    System.out.println(equipos.listarRango(min.toLowerCase(), max.toLowerCase()).toString());
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
        String primer, segundo;
        Equipo uno, dos;
        boolean exit = false;
        opcion = 'y';
        do {
            switch (opcion) {
                case 'y':
                    System.out.print("Ingrese el nombre del primer equipo: ");
                    primer = sc.next();
                    System.out.print("Ingrese el nombre del segundo equipo: ");
                    segundo = sc.next();
                    uno = equipos.recuperar(primer.toLowerCase());
                    dos = equipos.recuperar(segundo.toLowerCase());
                    System.out.println("Rondas validas: 'grupo' 'cuartos' 'semis' final");
                    System.out.print("Ingrese la ronda: ");
                    String ronda = sc.next().toLowerCase();
                    if (rondaValida(ronda) && uno != null && dos != null) {
                        // puedeInsertar es un metodo de clase que me indica si es posible crear el
                        // partido dados unos datos basicos
                        boolean puedeInsertar = Partido.puedeInsertar(uno, dos, ronda);
                        if (puedeInsertar) {
                            int resultadoEquipo1 = 0, resultadoEquipo2 = 0;
                            boolean aux = false;
                            String respuesta;
                            do {
                                System.out.print("Ingrese el resultado de: " + primer + " en el partido: ");
                                // Utilizo una expresion regular para verificar que solo sean numeros
                                respuesta = sc.next();
                                if (respuesta.matches("\\d+")) {
                                    resultadoEquipo1 = Integer.parseInt(respuesta);
                                    aux = true;
                                } else {
                                    System.out.println("Error: El valor ingresado no es un numero positivo.");
                                }
                            } while (!aux);
                            do {
                                System.out.print("Ingrese el resultado de: " + segundo + " en el partido: ");
                                respuesta = sc.next();
                                if (respuesta.matches("\\d+")) {
                                    resultadoEquipo2 = Integer.parseInt(respuesta);
                                    aux = true;
                                } else {
                                    System.out.println("Error: El valor ingresado no es un numero positivo.");
                                }
                            } while (!aux);
                            Partido p = new Partido(uno, dos, ronda, null, respuesta, resultadoEquipo1,
                                    resultadoEquipo2);
                            if (!partidos.insertar(p)) {
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
                        opcion = sc.next().toLowerCase().charAt(0);
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
        System.out.println("----------------------------------Error:----------------------------------");
    }

    /*
     * Dados dos equipos, si existen, devuelve los partidos que jugaron entre si
     * 
     * Obs: Independientemente del orden lexicografico, devuelve los partidos
     * jugados entre si (si es que tienen)
     */
    public static void consultaPartidos() {
        System.out.print("Ingrese el nombre del primer equipo: ");
        String primer = sc.next().toLowerCase();
        System.out.print("Ingrese el nombre del segundo equipo: ");
        String segundo = sc.next().toLowerCase();
        Equipo uno = equipos.recuperar(primer), dos = equipos.recuperar(segundo);
        if (uno != null && dos != null && !primer.equals(segundo)) {
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            Partido p = new Partido(uno, dos);
            System.out.println(partidos.recuperar(p));
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

        } else {
            System.out.println("Verifique que los nombres sean correctos y que no sean el mismo");
        }
    }

    public static void main(String[] args) {
        boolean exit = false;
        Equipo arg = new Equipo("Argentina", "asd", 'A');
        Equipo col = new Equipo("Colombia", "asd", 'A');
        Equipo bra = new Equipo("Brasil", "asd", 'B');
        Equipo uru = new Equipo("Uruguay", "asd", 'C');
        Equipo pan = new Equipo("Panamá", "xd", 'C');
        Equipo chi = new Equipo("Chile", "asdasd", 'A');
        equipos.insertar(bra);
        equipos.insertar(arg);
        equipos.insertar(col);
        equipos.insertar(pan);
        equipos.insertar(uru);
        equipos.insertar(chi);

        do {
            menu();
            System.out.print("Ingrese la opcion: ");
            opcion = sc.next().charAt(0);
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
                    break;
                case '7':
                    break;
                case '8':
                    break;
                case '0':
                    System.out.println("--------Finalizado--------");
                    exit = true;
                    break;
                default:
                    System.out.println("La opcion ingresada es incorrecta");
                    break;
            }

        } while (!exit);
        System.out.println(ciudades.toString());

        System.out.println(equipos.toString());
    }
}
