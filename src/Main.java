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
    public static void ABMciudades() {
        System.out.println("__________________________________________");
        System.out.println("Altas, bajas y modificaciones de Ciudades:");
        boolean exit = false;
        do {
            System.out.println("1) Crear Ciudades");
            System.out.println("2) Borrar Ciudades");
            System.out.println("3) Modificar Ciudades");
            System.out.println("0) Salir");
            System.out.print("Ingrese la opcion: ");
            opcion = sc.next().charAt(0);
            switch (opcion) {
                case '1':
                    agregarCiudades();
                    break;
                case 2:
                    // borrarCiudades();
                case 3:
                    // modificarCiudades();
                    break;
                case '0':
                    exit = true;
                    break;
                default:
                    System.out.println("La opcion ingresada es incorrecta");
                    break;
            }
        } while (!exit);
        System.out.println("__________________________________________");
    }

    public static void agregarCiudades() {
        boolean exit = false;
        opcion = 'y';
        String nombre;
        char sede;
        do {
            switch (opcion) {
                case 'y':
                    System.out.print("Ingrese nombre de la ciudad: ");
                    nombre = sc.next();
                    do {
                        System.out.print("La ciudad es sede? y/n: ");
                        sede = sc.next().charAt(0);
                        if (sede != 'y' && sede != 'n') {
                            System.out.println("Ingrese unicamente 'y' para si y 'n' para no");
                        }
                    } while (sede != 'y' && sede != 'n');
                    ciudades.insertarVertice(nombre, exit);
                    do {
                        System.out.println("Desea continuar? y/n: ");
                        opcion = sc.next().charAt(0);
                        if (opcion != 'y' && opcion != 'n') {
                            System.out.println("Ingrese unicamente 'y' para si y 'n' para no");
                        }
                    } while (opcion != 'y' && opcion != 'n');
                    break;
                case 'n':
                    exit = true;
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
        } while (!exit);

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
