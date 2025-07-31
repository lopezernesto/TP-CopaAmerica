package Tests.SegundaOpcionListar;

import Estructuras.AVL.NodoAVL;

/* 
 * Debido a las pruebas realizadas se llego a la conclucion que
 * No es factible utilizar este NodoAVL debido a funciones como Eliminar que quedarian raras
 * 
 * Se guardara para futuras pruebas
 */
public class NodoAVLEspecifico extends NodoAVL {
    private NodoAVLEspecifico siguiente;
    /*
     * Este Nodo es un NodoAVL especifico pero con 'Hermanos Derecho'
     */

    public NodoAVLEspecifico(Comparable elem, NodoAVL hijoIzquierdo, NodoAVL hijoDerecho) {
        super(elem, hijoIzquierdo, hijoDerecho);
    }

    public NodoAVLEspecifico(Comparable elem) {
        super(elem);
    }

    public NodoAVLEspecifico getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoAVLEspecifico siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public NodoAVLEspecifico getDerecho() {
        NodoAVLEspecifico x = (NodoAVLEspecifico) super.getDerecho();
        return x;
    }

    public NodoAVLEspecifico getIzquierdo() {
        NodoAVLEspecifico x = (NodoAVLEspecifico) super.getIzquierdo();
        return x;
    }
}
