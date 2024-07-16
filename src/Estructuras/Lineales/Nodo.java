package Estructuras.Lineales;

import Dominio.Partido;

public class Nodo {
    private Partido elem;
    private Nodo enlace;

    public Nodo(Partido elem, Nodo enlace) {
        this.elem = elem;
        this.enlace = enlace;
    }

    public Partido getElem() {
        return this.elem;
    }

    public Nodo getEnlace() {
        return this.enlace;
    }

    public void setElem(Partido elem) {
        this.elem = elem;
    }

    public void setEnlace(Nodo enlace) {
        this.enlace = enlace;
    }

}
