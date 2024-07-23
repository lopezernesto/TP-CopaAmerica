package Tests.SegundaOpcionListar;

import Dominio.Equipo;
import Estructuras.Lineales.Lista;

/* 
 * Debido a las pruebas realizadas se llego a la conclucion que
 * No es factible utilizar este AVL.
 * 
 * Se guardara para futuras pruebas
 */
public class AVLEspecifico {
    private NodoAVLEspecifico raiz;

    /*
     * Este AVL solo es para insertar elementos ya que se crea a partir del AVL
     * original
     * 
     * La diferencia con el AVL original es que cada nodo apunta a una lista de
     * nodos, en caso de que existan equipos con igual goles a favor
     * 
     * Por eso se utiliza un NodoAVLEspecifico
     * Que no es mas que un NodoAVL pero con un enlace al siguiente
     */
    public AVLEspecifico() {

    }

    // Entra un Equipo
    public boolean insertar(Comparable elem) {
        Equipo e = (Equipo) elem;
        EquipoGoles equipo = new EquipoGoles(e.getNombre(), e.getGolesFavor());
        boolean exit = false;
        if (raiz == null) {
            // Si raiz es nula, lo inserto ahi
            raiz = new NodoAVLEspecifico(equipo);
            exit = true;
        } else {
            int temp = raiz.getElem().compareTo(equipo);
            // Sino busco donde insertarlo, si el elemnto ya existe no lo inserta
            // HI --> true | HD --> false
            if (temp != 0) {
                if (temp > 0) {
                    exit = insertarAux(raiz, raiz.getIzquierdo(), true, equipo);
                } else {
                    exit = insertarAux(raiz, raiz.getDerecho(), false, equipo);
                }
            } else {
                // Si temp==0 es porque son equipos con igual goles a favor
                NodoAVLEspecifico aux = raiz;
                while (aux.getSiguiente() != null) {
                    // Me muevo hasta el ultimo hermano derecho
                    aux = aux.getSiguiente();
                }
                // luego inserto al nuevo elemento
                aux.setSiguiente(new NodoAVLEspecifico(equipo));
                // En este caso mi variable 'exit' no se cambia a true porque no es necesario
                // hacer rotaciones
            }
        }
        // Si inserto, recalcula altura de raiz y la rebalancea
        if (exit) {
            raiz.recalcularAltura();
            balancearRaiz(raiz);
        }
        return exit;
    }

    private boolean insertarAux(NodoAVLEspecifico padre, NodoAVLEspecifico n, boolean hijo, EquipoGoles equipo) {
        boolean exit = false;
        // Si n es null es porque no se encontro al elem que debo insertar
        if (n == null) {
            exit = true;
            // Entonces creo el nodo y luego lo enlazo
            n = new NodoAVLEspecifico(equipo);
            // Si hijo es true lo enlazo al HI
            if (hijo) {
                padre.setIzquierdo(n);
            } else {
                padre.setDerecho(n);
            }

        } else {
            int temp = n.getElem().compareTo(equipo);
            if (temp != 0) {
                if (temp > 0) {
                    exit = insertarAux(n, n.getIzquierdo(), true, equipo);
                } else {
                    exit = insertarAux(n, n.getDerecho(), false, equipo);
                }
            } else {
                // Si temp==0 es porque son equipos con igual goles a favor
                NodoAVLEspecifico aux = n;
                while (aux.getSiguiente() != null) {
                    // Me muevo hasta el ultimo hermano derecho
                    aux = aux.getSiguiente();
                }
                // luego inserto al nuevo elemento
                aux.setSiguiente(new NodoAVLEspecifico(equipo));
                // En este caso mi variable 'exit' no se cambia a true porque no es necesario
                // hacer rotaciones
            }
        }
        // Si se pudo insertar rebalanceo
        if (exit) {
            n.recalcularAltura();
            balancear(padre, n, hijo);
        }

        return exit;
    }

    private boolean balancearRaiz(NodoAVLEspecifico nodo) {
        int balance = balance(nodo);
        if (balance < -1 || balance > 1) {
            // Si el balance es +- 2 entonces hay que aplicar rotaciones
            raiz = aplicarRotaciones(nodo);
        }
        raiz.recalcularAltura();
        return true;
    }

    private boolean balancear(NodoAVLEspecifico padre, NodoAVLEspecifico n, boolean hijo) {
        int balance = balance(n);
        // Si el balance es 2 o -2
        if (balance < -1 || balance > 1) {
            // hay que rotar al nodo
            NodoAVLEspecifico balanceado = aplicarRotaciones(n);
            if (hijo) {
                padre.setIzquierdo(balanceado);
            } else {
                padre.setDerecho(balanceado);
            }
            padre.recalcularAltura();
        }

        return true;
    }

    private int balance(NodoAVLEspecifico nodo) {
        int altIzq = -1, altDer = -1;
        if (nodo.getIzquierdo() != null) {
            altIzq = nodo.getIzquierdo().getAltura();
        }
        if (nodo.getDerecho() != null) {
            altDer = nodo.getDerecho().getAltura();
        }

        return altIzq - altDer;
    }

    private NodoAVLEspecifico aplicarRotaciones(NodoAVLEspecifico nodo) {
        NodoAVLEspecifico balanceado = null;
        int balanceNodo = balance(nodo);
        if (balanceNodo == -2 && nodo.getDerecho() != null) {
            // Balance -2 significa caido a la derecha
            int balanceHijoDerecho = balance(nodo.getDerecho());
            if (balanceHijoDerecho == -1) {
                // Si el balance del hijo tiene el mismo signo, es giro simple
                balanceado = giroIzquierda(nodo);
            } else {
                // Sino es giro doble
                balanceado = dobleDerechaIzquierda(nodo);
            }
        } else {
            if (balanceNodo == 2 && nodo.getIzquierdo() != null) {
                // Balance 2 esta caido a la izquierda
                int balanceHijoIzquierda = balance(nodo.getIzquierdo());
                if (balanceHijoIzquierda == 1) {
                    balanceado = giroDerecha(nodo);
                } else {
                    balanceado = dobleIzquierdaDerecha(nodo);
                }
            }
        }
        return balanceado;
    }

    private NodoAVLEspecifico dobleDerechaIzquierda(NodoAVLEspecifico padre) {
        NodoAVLEspecifico hijoDerecha = giroDerecha(padre.getDerecho());
        padre.setDerecho(hijoDerecha);
        NodoAVLEspecifico nuevoPadre = giroIzquierda(padre);
        // Ahora recalculamos alturas
        padre.recalcularAltura();
        hijoDerecha.recalcularAltura();
        return nuevoPadre;
    }

    private NodoAVLEspecifico dobleIzquierdaDerecha(NodoAVLEspecifico padre) {
        NodoAVLEspecifico hijoIzquierdo = giroIzquierda(padre.getIzquierdo());
        padre.setIzquierdo(hijoIzquierdo);
        NodoAVLEspecifico nuevoPadre = giroDerecha(padre);

        // Ahora recalculasmos alturas
        padre.recalcularAltura();
        hijoIzquierdo.recalcularAltura();
        return nuevoPadre;
    }

    private NodoAVLEspecifico giroDerecha(NodoAVLEspecifico padre) {
        NodoAVLEspecifico hijoIzquierdo = padre.getIzquierdo();
        // Guardamos el hijo derecho de HI
        NodoAVLEspecifico temp = null;
        if (hijoIzquierdo.getDerecho() != null) {
            temp = hijoIzquierdo.getDerecho();
        }
        // Ahora el hijo derecho del HI es el padre
        hijoIzquierdo.setDerecho(padre);
        // y el hijo izquierdo del padre es el hijo derecho guardado
        padre.setIzquierdo(temp);
        // Luego de la rotacion recalculamos alturas
        padre.recalcularAltura();
        hijoIzquierdo.recalcularAltura();
        return hijoIzquierdo;
    }

    private NodoAVLEspecifico giroIzquierda(NodoAVLEspecifico padre) {
        NodoAVLEspecifico hijoDerecho = padre.getDerecho();
        // Guardamos al hijo izquierdo de nuetro HD
        NodoAVLEspecifico temp = null;
        if (hijoDerecho.getIzquierdo() != null) {
            temp = hijoDerecho.getIzquierdo();
        }

        // Ahora el hijo izquierdo del HD es el padre
        hijoDerecho.setIzquierdo(padre);
        // y el hijo derecho del padre es el hijo izquierdo guardado
        padre.setDerecho(temp);

        // Recalculamos las alturas
        padre.recalcularAltura();
        hijoDerecho.recalcularAltura();
        return hijoDerecho;
    }

    public Lista listar() {
        Lista lista = new Lista();
        if (raiz != null) {
            listarAux(raiz, lista);
        }
        return lista;
    }

    private void listarAux(NodoAVLEspecifico n, Lista lista) {
        if (n != null) {
            listarAux(n.getIzquierdo(), lista);
            NodoAVLEspecifico aux = n;
            lista.insertar(n.getElem(), 1);
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
                lista.insertar(aux.getElem(), 1);
            }
            listarAux(n.getDerecho(), lista);
        }
    }

    public String toString() {
        String cad = "Arbol vacio";
        if (raiz != null) {
            cad = toStringAux(raiz);
        }
        return cad;
    }

    public String toStringAux(NodoAVLEspecifico n) {
        String cad = "";
        if (n != null) {
            NodoAVLEspecifico aux = n;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
                cad += "(" + ((EquipoGoles) aux.getElem()).getNombre() + ") - ";
            }
            cad += "(" + ((EquipoGoles) n.getElem()).getNombre() + ") -> ";
            if (n.getIzquierdo() != null) {
                cad += "HI: " + ((EquipoGoles) n.getIzquierdo().getElem()).getNombre() + "  ";
            } else {
                cad += "HI: -  ";
            }
            if (n.getDerecho() != null) {
                cad += "HD: " + ((EquipoGoles) n.getDerecho().getElem()).getNombre() + "\n";
            } else {
                cad += "HD: - \n";
            }
            cad += toStringAux(n.getIzquierdo());
            cad += toStringAux(n.getDerecho());
        }
        return cad;
    }

}
