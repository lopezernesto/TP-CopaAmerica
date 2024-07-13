import java.util.Scanner;

import Estructuras.Grafo.Grafo;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static char opcion;
    private static Grafo ciudades = new Grafo();

    public static void menu() {
        System.out.println("MENU DE OPCIONES:");
        System.out.println("-----------------------------------------------------");
        System.out.println("1) ABM Ciudades");
        System.out.println("2) ABM Equipos");
        System.out.println("3) Altas Partidos");
        System.out.println("4) Consulta sobre Equipos");
        System.out.println("5) Consulta sobre Partidos");
        System.out.println("6) Consulta sobre Viajes desde punto A hasta punto B");
        System.out.println("7) Listar Equipos por goles a favor");
        System.out.println("8) Mostrar Sistema");
        System.out.println("0) Salir");
        System.out.println("-----------------------------------------------------");
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
     * Para crear una ciudad son necesarias dos variables
     * 1) Nombre de la ciudad 2)Si es sede o no (true/false)
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
                    System.out.print("Ingrese nombre de la ciudad: ");
                    nombre = sc.next();
                    do {
                        // Sede sera un booleano, por lo tanto solo puede ser y/n
                        System.out.print("La ciudad es sede? y/n: ");
                        sede = sc.next().charAt(0);
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
                        System.out.println("Desea continuar? y/n: ");
                        opcion = sc.next().charAt(0);
                        if (opcion != 'y' && opcion != 'n') {
                            System.out.println("Opcion incorrecta");
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
                    System.out.print("Ingrese nombre de la ciudad: ");
                    nombre = sc.next();
                    if (!ciudades.eliminarVertice(nombre)) {
                        System.out.println("No existe una ciudad con ese nombre");
                    }
                    do {
                        System.out.println("Desea continuar? y/n: ");
                        opcion = sc.next().charAt(0);
                        if (opcion != 'y' && opcion != 'n') {
                            System.out.println("Opcion incorrecta");
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
            System.out.println("1) Agregar camino");
            System.out.println("2) Eliminar camino");
            System.out.println("0) Salir");
            System.out.print("Ingrese la opcion: ");
            opcion = sc.next().charAt(0);
            // Como tanto para eliminar y agregar necesito el nombre de las dos ciudades
            if (opcion != '0') {
                // Si la opcion sea alguna de esas, pido las dos ciudades antes de operar
                System.out.print("Ingrese nombre de la primer ciudad: ");
                nombreA = sc.next();
                System.out.print("Ingrese el nombre de la segunda ciudad: ");
                nombreB = sc.next();
            }
            switch (opcion) {
                case '1':
                    do {
                        // El tiempo de vuelo(int) es requerido para crear el camino
                        System.out.print("Ingrese el tiempo de vuelo: ");
                        // Utilizo un try-catch ya que puede ingresar una cadena no solo de numeros
                        try {
                            // Y al utilizar el parseInt me tiraria error
                            tiempo = Integer.parseInt(sc.next());
                            aux = true;
                        } catch (NumberFormatException e) {
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

    public static void main(String[] args) {
        boolean exit = false;
        do {
            menu();
            System.out.print("Ingrese la opcion: ");
            opcion = sc.next().charAt(0);
            switch (opcion) {
                case '1':
                    ABMciudades();
                    break;
                case '2':
                    break;
                case '3':
                    break;
                case '4':
                    break;
                case '5':
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
    }
}
