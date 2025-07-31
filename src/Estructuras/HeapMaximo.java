
package Estructuras;

public class HeapMaximo {
    private Comparable[] heap;
    private int ultimo = 0;

    public HeapMaximo() {
        this.heap = new Comparable[30];
    }

    public boolean insertar(Comparable elemento) {
        boolean retorno = false;
        // insertamos en el lugar mas a la izquierda libre
        if (this.heap.length > this.ultimo + 1) {
            this.heap[this.ultimo + 1] = elemento;
            this.ultimo++;
            ordenarHaciaArriba();
            retorno = true;
        }
        return retorno;
    }

    private void ordenarHaciaArriba() {
        // tomamos el ultimo nodo insertado y lo comparamos con su padre

        int i = this.ultimo;
        int padre = this.ultimo / 2;
        boolean control = true;
        while (padre >= 1 && control) {
            // compramos el nodo final con su padre u guradamos su pocicion
            int resp = this.heap[i].compareTo(this.heap[padre]);
            if (resp > 0) {
                // los intercambiamos
                Comparable tmp1 = this.heap[i];
                this.heap[i] = this.heap[padre];
                this.heap[padre] = tmp1;
                // obtenemos los nuevos padre del elemento
                i = padre;
                padre = i / 2;
            } else {
                control = false;
            }
        }
    }

    public boolean eliminar() {
        boolean retorno = false;

        if (this.ultimo > 0) {
            // intermabiamos la cima por la ultima hoja
            // eliminamos la cima y
            Comparable tmp = this.heap[this.ultimo];
            this.heap[1] = tmp;
            this.heap[this.ultimo] = null;
            this.ultimo--;
            // ordenamos el arbol
            ordenarHaciaAbajo(1);
            retorno = true;
        }
        return retorno;
    }

    private void ordenarHaciaAbajo(int posicionPadre) {
        // vamos a evaluar que tengamos hijos
        Comparable compara = this.heap[posicionPadre];
        boolean salir = false;
        int posicionHI;
        while (!salir) {
            posicionHI = posicionPadre * 2;
            // evaluamos que la pocicion del HI este dentro del arreglo
            if (posicionHI <= this.ultimo) {
                // evaluamos si tenemos HD
                if (posicionHI < this.ultimo) {
                    // elegimos el menor de los dos hermanos
                    if (this.heap[posicionHI + 1].compareTo(this.heap[posicionHI]) > 0) {
                        // el HD es menor al HI y nos movemos al menor
                        posicionHI++;
                    }
                }

                // vamos a compara el Hijo mas pequeño con el padre
                if (this.heap[posicionHI].compareTo(compara) > 0) {
                    // en caso de que el padre sea mayor que el menor de los hijos lo intercambiamos
                    this.heap[posicionPadre] = this.heap[posicionHI];
                    this.heap[posicionHI] = compara;
                    posicionPadre = posicionHI;
                } else {
                    // en caso de que el padre sea menor que los hijos esta bien posicionado
                    // cortamos la iteracion
                    salir = true;
                }
            } else {
                salir = true;
            }
        }

    }

    public Comparable obtenerCima() {
        Comparable cima = null;

        if (this.ultimo > 0) {
            cima = this.heap[1];
        }

        return cima;
    }

    public String toString() {
        String retorno = "[";

        if (this.heap.length > this.ultimo) {
            int i = 1;
            while (i <= this.ultimo) {
                retorno += this.heap[i];
                if (i != this.ultimo) {
                    retorno += ",";
                }
                i++;
            }
        }

        retorno += "]";
        return retorno;
    }
}