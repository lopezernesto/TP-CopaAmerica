package Estructuras.Lineales;

public class Cola {
    private Nodo frente, fin;

    public Cola() {
    }

    public boolean poner(Object elem) {
        Nodo nuevo = new Nodo(elem, null);
        if (esVacia()) {
            frente = fin = nuevo;
        } else {
            fin.setEnlace(nuevo);
            fin = nuevo;
        }
        return true;
    }

    public boolean sacar() {
        boolean exit = false;
        if (!esVacia()) {
            exit = true;
            frente = frente.getEnlace();
            if (frente == null) {
                fin = null;
            }
        }
        return exit;
    }

    public boolean esVacia() {
        return frente == null;
    }

    public Object obtenerFrente() {
        Object ret = null;
        if (!esVacia()) {
            ret = frente.getElem();
        }
        return ret;
    }

    public void vaciar() {
        frente = fin = null;
    }

    public Cola clone() {
        Cola clonada = new Cola();
        if (!esVacia()) {
            Nodo aux = frente, nuevo = new Nodo(aux.getElem(), null), clon = nuevo;
            clonada.frente = clon;
            aux = aux.getEnlace();
            while (aux != null) {
                nuevo = new Nodo(aux.getElem(), null);
                clon.setEnlace(nuevo);
                clon = clon.getEnlace();
                clonada.fin = clon;
                aux = aux.getEnlace();
            }

        }
        return clonada;
    }

    public String toString() {
        String cad = "La cola está vacía";
        if (!esVacia()) {
            cad = "";
            Nodo aux = frente;
            while (aux != null) {
                cad += aux.getElem() + "|";
                aux = aux.getEnlace();
            }
        }
        return cad;
    }
}
