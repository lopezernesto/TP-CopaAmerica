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
        boolean exit = false, encontrado = false;
        int pos = elem.hashCode() % 37;
        Nodo aux = arreglo[pos];
        while (!encontrado && aux != null) {
            if (aux.getElem().equals(elem))
                encontrado = true;
            aux = aux.getEnlace();
        }
        // Si no lo encontro lo inserta, enlazandolo con el que estaba antes en esa pos
        if (!encontrado) {
            arreglo[pos] = new Nodo(elem, arreglo[pos]);
            cant++;
            exit = true;
        }
        return exit;
    }

    /*
     * Entran dos "Equipos" solos cargados con nombre, ya ordenados eq1<eq2
     * Como Nodo utiliza la clase Object, lo casteo a Partido
     * 
     * Obs: como maximo dos equipos se enfrentan dos veces
     */
    public String recuperar(Equipo uno, Equipo dos, String ronda) {
        Partido p = new Partido(uno, dos, ronda);
        String ret = "No hay partidos entre esos dos equipos";
        // Como el hashcode se usa solo con el nombre
        int pos = p.hashCode() % 37;
        Nodo aux = arreglo[pos];
        if (aux != null) {
            ret = "";
            String otro = aux.getElem().toString() + "\n";
            p = aux.getElem();
            Partido x;
            while (aux.getEnlace() != null) {
                x = aux.getEnlace().getElem();
                otro += x.toString() + "\n";
                if (x.verifPartido(p)) {
                    ret += x.toString() + "\n";
                }
                aux = aux.getEnlace();
            }
            System.out.println("------");
            System.out.println(otro);
            System.out.println("------");
            ret += p.toString();
        }
        return ret;
    }

    /*
     * Para el test
     */
    public int recuperarTest(String primero, String segundo) {
        Equipo uno = new Equipo(primero);
        Equipo dos = new Equipo(segundo);
        Partido p = new Partido(uno, dos, null);
        // Como el hashcode se usa solo con el nombre
        int pos = p.hashCode() % 37;
        return pos;
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
