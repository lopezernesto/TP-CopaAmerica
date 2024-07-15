package Estructuras.AVL;

import Dominio.Equipo;

public class ArbolAVL {
    private NodoAVL raiz;

    public ArbolAVL() {

    }

    public boolean insertar(Equipo elem) {
        boolean exit = false;
        if (raiz == null) {
            // Si raiz es nula, lo inserto ahi
            raiz = new NodoAVL(elem);
            exit = true;
        } else {
            int temp = raiz.getElem().compareTo(elem);
            // Sino busco donde insertarlo, si el elemnto ya existe no lo inserta
            // HI --> true | HD --> false
            if (temp != 0) {
                if (temp > 0) {
                    exit = insertarAux(raiz, raiz.getIzquierdo(), true, elem);
                } else {
                    exit = insertarAux(raiz, raiz.getDerecho(), false, elem);
                }
            }
        }
        // Si inserto, recalcula altura de raiz y la rebalancea
        if (exit) {
            raiz.recalcularAltura();
            balancearRaiz(raiz);
        }
        return exit;
    }

    private boolean insertarAux(NodoAVL padre, NodoAVL n, boolean hijo, Equipo elem) {
        boolean exit = false;
        // Si n es null es porque no se encontro al elem que debo insertar
        if (n == null) {
            exit = true;
            // Entonces creo el nodo y luego lo enlazo
            n = new NodoAVL(elem);
            // Si hijo es true lo enlazo al HI
            if (hijo) {
                padre.setIzquierdo(n);
            } else {
                padre.setDerecho(n);
            }

        } else {
            int temp = n.getElem().compareTo(elem);
            if (temp != 0) {
                if (temp > 0) {
                    exit = insertarAux(n, n.getIzquierdo(), true, elem);
                } else {
                    exit = insertarAux(n, n.getDerecho(), false, elem);
                }
            }
        }
        // Si se pudo insertar rebalanceo
        if (exit) {
            n.recalcularAltura();
            balancear(padre, n, hijo);
        }

        return exit;
    }

    public boolean eliminar(Equipo elem) {
        boolean exit = false;
        if (raiz != null) {
            int temp = raiz.getElem().compareTo(elem);
            if (temp == 0) {
                // Si el elem es la raiz, la borramos
                raiz = eliminarRaiz(raiz);
                exit = true;
            } else {
                // HI --> | true HD --> false
                if (temp > 0)
                    exit = eliminarAux(raiz, raiz.getIzquierdo(), elem, true);
                else {
                    exit = eliminarAux(raiz, raiz.getDerecho(), elem, false);
                }
            }
        }

        if (exit) {
            // Si se pudo eliminar, rebalanceamos
            raiz.recalcularAltura();
            balancearRaiz(raiz);
        }

        return exit;
    }

    /*
     * Metodo para eliminar un nodo diferente a la raiz
     */
    private boolean eliminarAux(NodoAVL padre, NodoAVL n, Equipo elem, boolean hijo) {
        boolean exit = false;
        int temp = n.getElem().compareTo(elem);
        if (temp == 0) {
            NodoAVL nuevo = null;
            // Segun la cantidad de hijos, elijo un candidato
            switch (cantHijos(n)) {
                case 0:
                    nuevo = null;
                    break;
                case 1:
                    nuevo = metodo1(n);
                    break;
                case 2:
                    nuevo = metodo2(n);
                    break;
                default:
                    break;
            }
            // Guardo el candidato en n (nodo que voy a borrar)
            n = nuevo;
            // Luego se lo enlazo al padre
            if (hijo) {
                padre.setIzquierdo(nuevo);
                exit = true;
            } else {
                padre.setDerecho(nuevo);
                exit = true;
            }
        } else {
            // Si temp > 0 recorro por izquierda
            if (temp > 0) {
                if (n.getIzquierdo() != null) {
                    exit = eliminarAux(n, n.getIzquierdo(), elem, true);
                }
            } else {
                // Sino recorro por derecha
                if (n.getDerecho() != null) {
                    exit = eliminarAux(n, n.getDerecho(), elem, false);
                }
            }
        }
        // Si se pudo eliminar y el candidato no es nulo, entonces rebalanceo
        if (exit && n != null) {
            n.recalcularAltura();
            balancear(padre, n, hijo);
        }
        return exit;
    }

    /*
     * Metodo de eliminacion cuando el nodo tiene 1 solo hijo
     */
    private NodoAVL metodo1(NodoAVL nodo) {
        NodoAVL exit = null;
        if (nodo.getIzquierdo() != null) {
            exit = nodo.getIzquierdo();
        } else {
            exit = nodo.getDerecho();
        }
        return exit;
    }

    /*
     * Metodo para buscar al candidato cuando se elimina un elem
     * y reemplazarlo por el mejor candidato encontrado
     * 
     * Retorna el subarbol con el elem ya eliminado
     */
    private NodoAVL metodo2(NodoAVL nodo) {
        NodoAVL candidato = null;
        boolean exit = false;
        // Obtengo los mejores candidatos
        NodoAVL candidatoIzq = mayorIzq(nodo.getIzquierdo());
        NodoAVL candidatoDer = menorDer(nodo.getDerecho());
        // Obtengo la cantidad de hijos de los candidatos para recorrer
        int hijosCandidatoIzq = cantHijos(candidatoIzq), hijosCandidatoDer = cantHijos(candidatoDer);
        if (hijosCandidatoIzq <= hijosCandidatoDer) {
            // Le voy seteando el elemento al que fui eliminando
            nodo.setElem(candidatoIzq.getElem());
            exit = eliminarAux(nodo, nodo.getIzquierdo(), candidatoIzq.getElem(), true);
        } else {
            nodo.setElem(candidatoDer.getElem());
            exit = eliminarAux(nodo, nodo.getDerecho(), candidatoDer.getElem(), false);
        }
        if (exit) {
            candidato = nodo;
        }
        return candidato;
    }

    /*
     * Retorna un Nodo AVL que sera mi nueva raiz
     */
    private NodoAVL eliminarRaiz(NodoAVL raiz) {
        NodoAVL nuevo = null;
        switch (cantHijos(raiz)) {
            case 0:
                nuevo = null;
                break;
            case 1:
                nuevo = metodo1(raiz);
                break;
            case 2:
                nuevo = metodo2(raiz);
                break;
            default:
                break;
        }
        return nuevo;
    }

    /*
     * Calculo la cantidad de hijos que tiene un nodo para
     * posteriormente ver que metodo debo utilizar para eliminarlo
     */
    private int cantHijos(NodoAVL nodo) {
        int cant = 0;
        if (nodo.getIzquierdo() != null && nodo.getDerecho() != null) {
            cant = 2;
        } else {
            if (raiz.getIzquierdo() != null || raiz.getDerecho() != null) {
                cant = 1;
            }
        }
        return cant;
    }

    /*
     * mayorIzq es el metodo quee recorre al hijo izquierdo
     * por la derecha hasta que ya no haya otro
     */
    private NodoAVL mayorIzq(NodoAVL n) {
        NodoAVL aux = n;
        while (aux.getDerecho() != null) {
            aux = aux.getDerecho();
        }
        return aux;
    }

    /*
     * menorDer es el metodo que recorre al hijo derecho
     * por la izquierda hasta que ya no haya otro
     */
    private NodoAVL menorDer(NodoAVL n) {
        NodoAVL aux = n;
        while (aux.getIzquierdo() != null) {
            aux = aux.getIzquierdo();
        }
        return aux;
    }

    /*
     * Metodo para balancear los subarboles
     */
    public boolean balancear(NodoAVL padre, NodoAVL n, boolean hijo) {
        int balance = balance(n);
        // Si el balance es 2 o -2
        if (balance < -1 || balance > 1) {
            // hay que rotar al nodo
            NodoAVL balanceado = aplicarRotaciones(n);
            if (hijo) {
                padre.setIzquierdo(balanceado);
            } else {
                padre.setDerecho(balanceado);
            }
            padre.recalcularAltura();
        }

        return true;
    }

    /*
     * Caso especial para balancear raiz
     */
    public boolean balancearRaiz(NodoAVL nodo) {
        int balance = balance(nodo);
        if (balance < -1 || balance > 1) {
            // Si el balance es +- 2 entonces hay que aplicar rotaciones
            raiz = aplicarRotaciones(nodo);
        }
        raiz.recalcularAltura();
        return true;
    }

    /***
     * Balance(n)=Alt(HI)-Alt(HD)
     * Si no tiene hijo, se considera altura -1
     */
    public int balance(NodoAVL nodo) {
        int altIzq = -1, altDer = -1;
        if (nodo.getIzquierdo() != null) {
            altIzq = nodo.getIzquierdo().getAltura();
        }
        if (nodo.getDerecho() != null) {
            altDer = nodo.getDerecho().getAltura();
        }

        return altIzq - altDer;
    }

    /*
     * Aplicamos la rotacion al nodo devuelve la raiz del subarbol balanceado
     * para posteriormente enlazarlo con el padre del nodo que se paso por parametro
     */
    private NodoAVL aplicarRotaciones(NodoAVL nodo) {
        NodoAVL balanceado = null;
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

    // Rotaciones

    /*
     * Hacemos dos giros: primero un simple con el hijo para la derecha para
     * acomodar el balance ya que el padre esta caido hacia la derecha y el hijo
     * hacia la izquierda
     * 
     * Luego hacemos un giro hacia la izquierda con el padre para acomodar el arbol
     * 
     * Devuelve la raiz del nuevo subarbol, ya balanceado
     */
    private NodoAVL dobleDerechaIzquierda(NodoAVL padre) {
        NodoAVL hijoDerecha = giroDerecha(padre.getDerecho());
        padre.setDerecho(hijoDerecha);
        NodoAVL nuevoPadre = giroIzquierda(padre);
        // Ahora recalculamos alturas
        padre.recalcularAltura();
        hijoDerecha.recalcularAltura();
        return nuevoPadre;
    }

    /*
     * Hacemos dos giros: primero un simple con el hijo para la izquierda para
     * acomodar el balance ya que el padre esta caido hacia la izquierda y el hijo
     * hacia la derecha
     * 
     * Luego hacemos un giro hacia la derecha con el padre para acomodar el arbol
     * 
     * Devuelve la raiz del nuevo subarbol, ya balanceado
     */
    private NodoAVL dobleIzquierdaDerecha(NodoAVL padre) {
        NodoAVL hijoIzquierdo = giroIzquierda(padre.getIzquierdo());
        padre.setIzquierdo(hijoIzquierdo);
        NodoAVL nuevoPadre = giroDerecha(padre);

        // Ahora recalculasmos alturas
        padre.recalcularAltura();
        hijoIzquierdo.recalcularAltura();
        return nuevoPadre;
    }

    /*
     * En este giro intercambian enlaces del padre y su hijo izquierdo:
     * El hijo derecho del HI pasa a ser el nuevo HI.
     * El HI del padre pasa a ser el padre y el padre el hijo derecho del HI
     * 
     * Retorna al HI (ahora padre) con los nuevos enlaces para luego enlazarlo con
     * el padre del nodo enviado
     *
     * Recalcula alturas y devuelve el nuevo padre
     */
    private NodoAVL giroDerecha(NodoAVL padre) {
        NodoAVL hijoIzquierdo = padre.getIzquierdo();
        // Guardamos el hijo derecho de HI
        NodoAVL temp = null;
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

    /*
     * En este giro sucede lo inverso al anterior, porque tanto el padre como el
     * hijo estan caidos para el otro lado
     * 
     * Ahora el HD del nodo enviado es el nuevo padre y lo retorna
     */
    private NodoAVL giroIzquierda(NodoAVL padre) {
        NodoAVL hijoDerecho = padre.getDerecho();
        // Guardamos al hijo izquierdo de nuetro HD
        NodoAVL temp = null;
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

    public boolean esVacio() {
        return raiz == null;
    }

    /*
     * Cambio el return de pertenece, ahora si pertenece devuelve el equipo en lugar
     * de un true/false
     */
    public Equipo pertenece(Equipo elem) {
        Equipo retorno = null;
        if (!esVacio()) {
            retorno = perteneceAux(raiz, elem);
        }
        return retorno;
    }

    private Equipo perteneceAux(NodoAVL n, Equipo elem) {
        Equipo retorno = null;
        if (n != null) {
            int temp = n.getElem().compareTo(elem);
            if (temp == 0) {
                retorno = n.getElem();
            } else {
                if (temp < 0) {
                    retorno = perteneceAux(n.getDerecho(), elem);
                } else {
                    retorno = perteneceAux(n.getIzquierdo(), elem);
                }
            }
        }
        return retorno;
    }

    public String toString() {
        String cad = "Arbol vacio";
        if (!esVacio()) {
            cad = toStringAux(raiz);
        }
        return cad;
    }

    public String toStringAux(NodoAVL n) {
        String cad = "";
        if (n != null) {
            cad += "(" + n.getElem().getNombre() + ") -> ";
            if (n.getIzquierdo() != null) {
                cad += "HI: " + n.getIzquierdo().getElem().getNombre() + "  ";
            } else {
                cad += "HI: -  ";
            }
            if (n.getDerecho() != null) {
                cad += "HD: " + n.getDerecho().getElem().getNombre() + "\n";
            } else {
                cad += "HD: - \n";
            }
            cad += toStringAux(n.getIzquierdo());
            cad += toStringAux(n.getDerecho());
        }
        return cad;
    }

}
