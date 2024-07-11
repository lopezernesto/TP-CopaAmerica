package Estructuras.Lineales;

public class Lista {
    private Nodo cabecera;
    private int longitud;

    public Lista() {
        longitud = 0;
    }

    public boolean insertar(Object elem, int pos) {
        boolean exit = false;
        if (pos > 0 && pos <= longitud + 1) {
            exit = true;
            if (pos == 1) {
                cabecera = new Nodo(elem, cabecera);
            } else {
                int i = 2;
                Nodo aux = cabecera;
                while (i <= pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                Nodo nuevo = new Nodo(elem, aux.getEnlace());
                aux.setEnlace(nuevo);
            }
            longitud++;
        }
        return exit;
    }

    public boolean eliminar(int pos) {
        boolean exit = false;
        if (pos > 0 && pos <= longitud) {
            exit = true;
            if (pos == 1) {
                cabecera = cabecera.getEnlace();
            } else {
                int i = 2;
                Nodo aux = cabecera;
                while (i <= pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                aux.setEnlace(aux.getEnlace().getEnlace());
            }
            longitud--;
        }
        return exit;
    }

    public Object recuperar(int pos) {
        Object ret = null;
        if (!esVacia()) {
            if (pos > 0 && pos <= longitud) {
                if (pos == 1) {
                    ret = cabecera.getElem();
                } else {
                    int i = 2;
                    Nodo aux = cabecera;
                    while (i <= pos) {
                        aux = aux.getEnlace();
                        i++;
                    }
                    ret = aux.getElem();
                }
            }
        }
        return ret;
    }

    public int localizar(Object elem) {
        int pos = -1;
        if (!esVacia()) {
            Nodo aux = cabecera;
            int i = 1;
            do {
                if (aux.getElem().equals(elem)) {
                    pos = i;
                    i = longitud + 1;
                }
                i++;
                aux = aux.getEnlace();
            } while (i <= longitud);
        }
        return pos;
    }

    public int longitud() {
        return longitud;
    }

    public boolean esVacia() {
        return (longitud == 0);
    }

    public void vaciar() {
        longitud = 0;
        cabecera = null;
    }

    public Lista clone() {
        Lista clonada = new Lista();
        if (!esVacia()) {
            Nodo aux = cabecera;
            clonada.cabecera = new Nodo(aux.getElem(), null);
            Nodo clon = clonada.cabecera;
            aux = aux.getEnlace();
            while (aux != null) {
                Nodo nuevo = new Nodo(aux.getElem(), null);
                clon.setEnlace(nuevo);
                clon = clon.getEnlace();
                aux = aux.getEnlace();
            }
            clonada.longitud = longitud;
        }
        return clonada;
    }

    public String toString() {
        String cad = "La lista está vacía";
        if (!esVacia()) {
            cad = "[";
            Nodo aux = cabecera;
            while (aux != null) {
                cad += aux.getElem() + " ";
                aux = aux.getEnlace();
            }
            cad += "]";
        }
        return cad;
    }

    /*
     * Este metodo obtiene una nueva lista con los elementos de las posiciones
     * que sean multiplos de 'num'
     */
    public Lista obtenerMultiplos(int num) {
        Lista l = new Lista();
        if (!esVacia()) {
            // clon es el nodo que recorre mi nueva lista
            // aux recorre la lista original
            Nodo aux = cabecera, clon = null;
            int i = 1, longitud = 1;
            while (aux != null) {
                if (i % num == 0) {
                    Nodo nuevo = new Nodo(aux.getElem(), null);
                    if (longitud > 1) {
                        clon.setEnlace(nuevo);
                        clon = clon.getEnlace();
                    } else {
                        l.cabecera = nuevo;
                        clon = l.cabecera;
                    }
                    longitud++;
                }
                aux = aux.getEnlace();
                i++;
            }
            l.longitud = longitud;
        }
        return l;
    }

    // Elimina la cantidad de apariciones de 'elem' en la lista
    public void eliminarApariciones(Object elem) {
        int cant = 0;
        Nodo actual = cabecera, previo = null;
        while (actual != null) {
            // previo=null indica que lo que estoy en la cabecera
            if (actual.getElem().equals(elem)) {
                cant++;
                if (previo == null) {
                    cabecera = cabecera.getEnlace();
                    actual = cabecera;
                } else {
                    actual = actual.getEnlace();
                    previo.setEnlace(actual);
                }
            } else {
                previo = actual;
                actual = actual.getEnlace();
            }
        }
        longitud -= cant;
    }

    // Inserta elemento y lo repite cada X posiciones
    public void insertarYRepetir(Object elem, int salto) {
        Nodo aux = cabecera;
        int cont = salto, i = 0;
        while (aux != null) {
            if (i == 0) {
                cabecera = new Nodo(elem, aux);
                i++;
            }
            if (cont == salto + 1) {
                aux.setEnlace(new Nodo(elem, aux.getEnlace()));
                cont = 0;
            }
            aux = aux.getEnlace();
            cont++;
        }
    }

    // Lo mismo que el anterior pero recursivo
    public void insertarYRepetir2(Object elem, int salto) {
        if (!esVacia()) {
            int cont = salto;
            insertarYRepetirAux(cabecera, elem, salto, cont);
        }
    }

    private void insertarYRepetirAux(Nodo n, Object elem, int salto, int cont) {
        if (n != null) {
            if (n == cabecera) {
                cabecera = new Nodo(elem, n.getEnlace());
                insertarYRepetirAux(n.getEnlace(), elem, salto, cont + 1);
            } else {
                if (cont == salto) {
                    n.setElem(new Nodo(elem, n.getEnlace()));
                    insertarYRepetirAux(n.getEnlace(), elem, salto, cont + 1);
                } else {
                    insertarYRepetirAux(n.getEnlace(), elem, salto, cont + 1);
                }
            }

        }
    }

    // Mueve el elemento de "pos" a la anteultima posicion
    public boolean moverAAnteultimaPosicion(int pos) {
        boolean exit = false;
        if (longitud() > 0 && pos > 0 && pos <= longitud() && pos != longitud() - 1) {
            exit = true;
            Nodo elem;
            Nodo aux = cabecera;
            int i = 1;
            // Si esta en la cabecera
            if (pos == 1) {
                elem = cabecera;
                cabecera = cabecera.getEnlace();
                while (i <= longitud() - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                elem.setEnlace(aux.getEnlace());
                aux.setEnlace(elem);

            } else {
                // Si esta en la ultima posicion
                if (pos == longitud()) {
                    while (i < longitud() - 2) {
                        aux = aux.getEnlace();
                        i++;
                    }
                    // aux es mi anterior al anteultimo
                    elem = aux.getEnlace().getEnlace();
                    aux.getEnlace().setEnlace(null);
                    elem.setEnlace(aux.getEnlace());
                    aux.setEnlace(elem);
                } else {
                    // Si esta dentro dentro de la Lista
                    while (i < pos - 1) {
                        aux = aux.getEnlace();
                        i++;
                    }
                    elem = aux.getEnlace();
                    aux.setEnlace(aux.getEnlace().getEnlace());
                    i++;
                    while (i < longitud() - 1) {
                        aux = aux.getEnlace();
                        i++;
                    }
                    elem.setEnlace(aux.getEnlace());
                    aux.setEnlace(elem);
                }
            }
        }
        return exit;
    }

    // Dada una Lista L2 devuelve a la lista intercalada con los elementos de L2
    public Lista intercalar(Lista lista) {
        Lista intercalada = new Lista();
        Nodo original = cabecera, segunda = lista.cabecera, ret;
        // si original es vacia y mi otra lista no?
        if (original != null && segunda != null) {
            intercalada.cabecera = new Nodo(original.getElem(), null);
            ret = intercalada.cabecera;
            int cant = intercalarAux(original.getEnlace(), segunda, ret);
            intercalada.longitud = cant;
        }
        return intercalada;
    }

    private int intercalarAux(Nodo original, Nodo segunda, Nodo resultado) {
        int cant = 0;
        while (original != null || segunda != null) {
            if (segunda != null) {
                resultado.setEnlace(new Nodo(segunda.getElem(), null));
                resultado = resultado.getEnlace();
                segunda = segunda.getEnlace();
                cant++;
            }
            if (original != null) {
                resultado.setEnlace(new Nodo(original.getElem(), null));
                resultado = resultado.getEnlace();
                original = original.getEnlace();
                cant++;
            }
        }
        return cant;
    }

    // Invertir sin estructuras y de un recorrido
    public void invertir() {
        Nodo aux = cabecera, nuevo = null, anterior = null, siguiente = aux.getEnlace();
        while (aux != null) {
            System.out.println("Estoy en: " + aux.getElem());

            nuevo = aux;
            nuevo.setEnlace(anterior);
            anterior = nuevo;
            aux = siguiente;
            if (siguiente != null) {
                siguiente = siguiente.getEnlace();
            }
        }
        cabecera = nuevo;
    }

    public void invertir2() {
        if (cabecera != null) {
            invertirAux(cabecera, cabecera.getEnlace(), null);
        }

    }

    public void invertirAux(Nodo actual, Nodo siguiente, Nodo anterior) {
        if (actual != null) {
            if (siguiente == null) {
                actual.setEnlace(anterior);
                cabecera = actual;
            } else {
                actual.setEnlace(anterior);
                invertirAux(siguiente, siguiente.getEnlace(), actual);
            }
        }
    }
}