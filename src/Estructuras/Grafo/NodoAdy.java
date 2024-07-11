package Estructuras.Grafo;

public class NodoAdy {
    private NodoVert destino;
    private NodoAdy sigAdyacente;
    private int tiempo; // etiqueta

    public NodoAdy(NodoVert vertice, int valor) {
        destino = vertice;
        tiempo = valor;
    }

    public NodoVert getVertice() {
        return destino;
    }

    public NodoAdy getSiguiente() {
        return sigAdyacente;
    }

    public int getEtiqueta() {
        return tiempo;
    }

    public void setSiguiente(NodoAdy arco) {
        sigAdyacente = arco;
    }

    public void setVertice(NodoVert vertice) {
        destino = vertice;
    }

    public void setEtiqueta(int valor) {
        tiempo = valor;
    }

}
