package Estructuras.AVL;

public class NodoAVL {
    private NodoAVL HI, HD;
    private Comparable elem;
    private int altura;

    public NodoAVL(Comparable elem, NodoAVL hijoIzquierdo, NodoAVL hijoDerecho) {
        this.elem = elem;
        HI = hijoIzquierdo;
        HD = hijoDerecho;
        altura = 0;
    }

    public NodoAVL(Comparable elem) {
        this.elem = elem;
        HD = HI = null;
        altura = 0;
    }

    public int getAltura() {
        return altura;
    }

    public void recalcularAltura() {
        int altD = -1, altI = -1;
        if (HD != null) {
            altD = HD.getAltura();
        }
        if (HI != null) {
            altI = HI.getAltura();
        }
        altura = Math.max(altI, altD) + 1;
    }

    public NodoAVL getIzquierdo() {
        return HI;
    }

    public void setIzquierdo(NodoAVL hijoIzquierdo) {
        HI = hijoIzquierdo;
    }

    public NodoAVL getDerecho() {
        return HD;
    }

    public void setDerecho(NodoAVL hijoDerecho) {
        HD = hijoDerecho;
    }

    public Comparable getElem() {
        return elem;
    }

    public void setElem(Comparable elem) {
        this.elem = elem;
    }
}
