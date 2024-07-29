package Estructuras;

import Dominio.Equipo;
import Dominio.Partido;
import Estructuras.Lineales.Lista;
import Estructuras.Lineales.Nodo;

public class TablaHash {

    private Nodo[] arreglo = new Nodo[37];
    private int cant;

    public TablaHash() {
        cant = 0;
    }

    public boolean insertar(Object elem) {
        boolean exit = false, encontrado = false;
        int pos = elem.hashCode() % 37;
        Nodo aux = arreglo[pos];
        Object p;
        while (!encontrado && aux != null) {
            p = aux.getElem();
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

    public boolean eliminar(Object elem) {
        boolean exit = false;
        int pos = elem.hashCode() % 37;
        Nodo aux = arreglo[pos];
        // Llevo referencia del nodo anterior para enlazarlo
        Nodo anterior = null;
        Object p;
        while (!exit && aux != null) {
            p = aux.getElem();
            if (p.equals(elem)) {
                exit = true;
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

    /*
     * elem es un partido
     */
    public boolean pertenece(Object elem) {
        boolean exit = false;
        int pos = elem.hashCode() % 37;
        Nodo aux = arreglo[pos];
        Object p;
        while (!exit && aux != null) {
            p = aux.getElem();
            if (p.equals(elem))
                exit = true;
            aux = aux.getEnlace();
        }
        return exit;
    }

    public boolean esVacio() {
        return cant == 0;
    }

    /*
     * Genera una Lista con los elementos ordenados segun la posicion de su hash
     */

    public Lista listar() {
        Lista l = new Lista();
        if (cant > 0) {
            int contador = 0, pos = 0;
            while (contador < cant) {
                if (arreglo[pos] != null) {
                    Nodo aux = arreglo[pos];
                    while (aux != null) {
                        l.insertar(aux.getElem(), 1);
                        aux = aux.getEnlace();
                        contador++;
                    }
                }
                pos++;
            }
        }
        return l;
    }

    /*
     * elem es el Partido entre dos equipos
     * 
     * Obs: como maximo dos equipos se enfrentan dos veces
     */
    public Lista recuperar(Object elem) {
        Lista l = new Lista();
        int pos = elem.hashCode() % 37;
        Nodo aux = arreglo[pos];
        if (aux != null) {
            while (aux != null) {
                l.insertar(aux.getElem(), 1);
                aux = aux.getEnlace();
            }
        }
        return l;
    }

    /*
     * A partir de aca son codigos para el test
     */
    public int recuperarTest(String primero, String segundo) {
        Equipo uno = new Equipo(primero, null, 'X');
        Equipo dos = new Equipo(segundo, null, 'X');
        Partido p = new Partido(uno, dos);
        int pos = p.hashCode() % 37;
        return pos;
    }

    public String recuperar1(Equipo uno, Equipo dos) {
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
}
