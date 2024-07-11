package Estructuras.Grafo;

public class NodoVert {
    private NodoAdy primerArco;
    private NodoVert siguienteVertice;
    private String nombre;

    // Creo un Vertice
    public NodoVert(String nombre) {
        this.nombre = nombre;
    }

    public boolean equals(NodoVert vertice) {
        return nombre.equals(vertice.nombre);
    }

    public boolean insertarArco(NodoVert destino, int tiempo) {
        boolean exit = false;
        if (primerArco == null) {
            primerArco = new NodoAdy(destino, tiempo);
            exit = true;
        } else {
            NodoAdy aux = primerArco;
            NodoAdy anterior = null;
            while (!exit && aux != null) {
                exit = aux.getVertice().equals(destino);
                anterior = aux;
                aux = aux.getSiguiente();
            }
            if (!exit && anterior != null)
                anterior.setSiguiente(new NodoAdy(destino, tiempo));
        }

        return exit;
    }

    public boolean eliminarArco(NodoVert destino) {
        boolean exit = false;

        NodoAdy aux = primerArco, anterior = null;
        while (aux != null) {
            if (aux.getVertice().equals(destino)) {
                exit = true;
                if (anterior == null) {
                    primerArco = primerArco.getSiguiente();
                } else {
                    anterior.setSiguiente(aux.getSiguiente());
                }
            }
            anterior = aux;
            aux = aux.getSiguiente();
        }
        return exit;
    }

    public NodoAdy getPrimerArco() {
        return primerArco;
    }

    public void setPrimerArco(NodoAdy primerArco) {
        this.primerArco = primerArco;
    }

    public NodoVert getSiguienteVertice() {
        return siguienteVertice;
    }

    public void setSiguienteVertice(NodoVert siguienteVertice) {
        this.siguienteVertice = siguienteVertice;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        String cad = "No tiene conexiones";
        if (primerArco != null) {
            cad = "";
            NodoAdy arco = primerArco;
            while (arco != null) {
                cad += "------>" + arco.getVertice().nombre + "\n";
                arco = arco.getSiguiente();
            }
        }
        return cad;
    }
}
