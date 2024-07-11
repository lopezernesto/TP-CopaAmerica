package Estructuras;

import Estructuras.Lineales.Nodo;

public class TablaHash {

    private Nodo[] arreglo = new Nodo[13];
    private int cant;

    public TablaHash() {
        cant = 0;
    }

    public boolean insertar(Object elem) {
        boolean exit = false;
        int pos = elem.hashCode() % 13;
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

    public boolean eliminar(Object elem) {
        boolean exit = false;
        int pos = elem.hashCode() % 13;
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

    public boolean pertenece(Object elem) {
        boolean exit = false;
        int pos = elem.hashCode() % 13;
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
