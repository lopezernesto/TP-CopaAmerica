package Estructuras.Grafo;

public class NodoAdy {
    private NodoVert destino;
    private NodoAdy sigAdyacente;
    private double tiempo; // etiqueta

    public NodoAdy(NodoVert vertice, double valor) {
        destino = vertice;
        tiempo = valor;
    }

    public NodoVert getVertice() {
        return destino;
    }

    public NodoAdy getSiguiente() {
        return sigAdyacente;
    }

    public double getEtiqueta() {
        return tiempo;
    }

    public void setSiguiente(NodoAdy arco) {
        sigAdyacente = arco;
    }

    public void setVertice(NodoVert vertice) {
        destino = vertice;
    }

    public void setEtiqueta(double valor) {
        tiempo = valor;
    }

}
