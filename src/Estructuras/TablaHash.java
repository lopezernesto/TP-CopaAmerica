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
        Partido p;
        while (!encontrado && aux != null) {
            p = (Partido) aux.getElem();
            if (p.equals(elem))
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
     * Entran dos Equipos
     * Como Nodo utiliza la clase Object, lo casteo a Partido
     * 
     * Obs: como maximo dos equipos se enfrentan dos veces
     */
    public String recuperar(Equipo uno, Equipo dos) {
        Partido p = new Partido(uno, dos);
        String ret = "No hay partidos entre esos dos equipos";
        // Como el hashcode se usa solo con el nombre
        int pos = p.hashCode() % 37;
        Nodo aux = arreglo[pos];
        if (aux != null) {
            ret = "";
            p = (Partido) aux.getElem();
            String otro = p.toString() + "\n";
            Partido x;
            while (aux.getEnlace() != null) {
                x = (Partido) aux.getEnlace().getElem();
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
        Equipo uno = new Equipo(primero, null, 'X');
        Equipo dos = new Equipo(segundo, null, 'X');
        Partido p = new Partido(uno, dos);
        int pos = p.hashCode() % 37;
        return pos;
    }

    public boolean eliminar(Partido elem) {
        boolean exit = false;
        int pos = elem.hashCode() % 37;
        Nodo aux = arreglo[pos];
        // Llevo referencia del nodo anterior para enlazarlo
        Nodo anterior = null;
        Partido p;
        while (!exit && aux != null) {
            p = (Partido) aux.getElem();
            if (p.equals(elem)) {
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
        Partido p;
        while (!exit && aux != null) {
            p = (Partido) aux.getElem();
            exit = p.equals(elem);
            aux = aux.getEnlace();
        }
        return exit;
    }

    public boolean esVacio() {
        return cant == 0;
    }
}
