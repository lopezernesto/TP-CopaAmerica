package Estructuras.Lineales;

public class Pila {

    private Nodo tope;

    public Pila() {

    }

    public boolean apilar(Object elem) {
        Nodo nuevo = new Nodo(elem, tope);
        tope = nuevo;
        return true;
    }

    public boolean desapilar() {
        boolean exit = false;
        if (!esVacia()) {
            exit = true;
            tope = tope.getEnlace();
        }
        return exit;
    }

    public boolean esVacia() {
        return tope == null;
    }

    public void vaciar() {
        tope = null;
    }

    public Object obtenerTope() {
        Object ret = null;
        if (!esVacia()) {
            ret = tope.getElem();
        }
        return ret;
    }

    public Pila clone() {
        Pila clonada = new Pila();
        if (!esVacia()) {
            Nodo aux = tope, clon = new Nodo(aux.getElem(), null);
            clonada.tope = clon;
            aux = aux.getEnlace();
            while (aux != null) {
                Nodo nuevo = new Nodo(aux.getElem(), null);
                clon.setEnlace(nuevo);
                clon = clon.getEnlace();
                aux = aux.getEnlace();
            }
        }
        return clonada;
    }

    public String toString() {
        String cad = "La pila está vacía";
        if (!esVacia()) {
            cad = tope.getElem().toString() + " ";
            Nodo aux = tope.getEnlace();
            while (aux != null) {
                cad += aux.getElem().toString() + " ";
                aux = aux.getEnlace();
            }
        }
        return cad;
    }
}
