package Estructuras;

import Dominio.Equipo;
import Dominio.Partido;
import Estructuras.Lineales.Nodo;

public class TablaHash {

    private Nodo[] arreglo = new Nodo[37];
    private int cant;

    public TablaHash() {
        cant = 0;
    }

    public boolean insertar(Partido elem) {
        boolean exit = false;
        int pos = elem.hashCode() % 37;
        Nodo aux = arreglo[pos];

        while (!exit && aux != null) {
            exit = aux.getElem().equals(elem);
            aux = aux.getEnlace();
        }
        // Si no lo encontro lo inserta, enlazandolo con el que estaba antes en esa pos
        if (!exit) {
            arreglo[pos] = new Nodo(elem, arreglo[pos]);
            cant++;
        }
        return !exit;
    }

    /*
     * Entran dos "Equipos" solos cargados con nombre, ya ordenados eq1<eq2
     * Como Nodo utiliza la clase Object, lo casteo a Partido
     * 
     * Obs: como maximo dos equipos se enfrentan dos veces
     */
    public String recuperar(Equipo uno, Equipo dos) {
        Partido p = new Partido(uno, dos, null);
        String ret = "No hay partidos entre esos dos equipos";
        // Como el hashcode se usa solo con el nombre
        int pos = p.hashCode() % 37;
        if (arreglo[pos] != null) {
            ret = "";
            p = (Partido) arreglo[pos].getElem();
            if (arreglo[pos].getEnlace() != null) {
                Partido x = (Partido) arreglo[pos].getEnlace().getElem();
                ret += x.toString() + "\n";
            }
            ret += p.toString();
        }
        return ret;
    }

    public boolean eliminar(Partido elem) {
        boolean exit = false;
        int pos = elem.hashCode() % 37;
        Nodo aux = arreglo[pos];
        // Llevo referencia del nodo anterior para enlazarlo
        Nodo anterior = null;

        while (!exit && aux != null) {
            exit = aux.getElem().equals(elem);
            if (exit) {
                cant--;
                // Si encuentra al elemento
                if (anterior != null)
                    // y el anterior no es nulo, enlaza el anterior con el siguiente del elem
                    anterior.setEnlace(aux.getEnlace());
                else {
                    // si el anterior es nulo es porque estoy en el primer elemento
                    arreglo[pos] = aux.getEnlace();
                }
            } else {
                // Si no encuentro al elem sigo recorriendo
                anterior = aux;
                aux = aux.getEnlace();
            }
        }
        return exit;
    }

    public boolean pertenece(Partido elem) {
        boolean exit = false;
        int pos = elem.hashCode() % 37;
        Nodo aux = arreglo[pos];
        while (!exit && aux != null) {
            exit = aux.getElem().equals(elem);
            aux = aux.getEnlace();
        }
        return exit;
    }

    public boolean esVacio() {
        return cant == 0;
    }
}
