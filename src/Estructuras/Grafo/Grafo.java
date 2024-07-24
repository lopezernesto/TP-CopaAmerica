package Estructuras.Grafo;

//Este digrafo etiquetado es utilizado para hacer un mapa de ciudades
public class Grafo {
    private NodoVert inicio;

    public Grafo() {
    }

    /*
     * Dado una ciudad crea un Vertice y lo inserta en la 'lista'
     * de Vertices que tiene mi grafo
     * Retorna false unicamente si ya existe un Vertice con ese nombre
     */
    public boolean insertarVertice(Object elem) {
        boolean exit = false, encontrado = false;
        if (inicio == null) {
            // Si no tiene vertices, inserta
            inicio = new NodoVert(elem);
            exit = true;
        } else {
            NodoVert aux = inicio;
            // Sino, verifica que no exista una ciudad con ese nombre
            while (!encontrado && aux.getSiguienteVertice() != null) {
                if (aux.getElem().equals(elem))
                    encontrado = true;
                aux = aux.getSiguienteVertice();
            }
            // Compara el ultimo elem. ya que no entra al ultimo en el while
            if (!encontrado && aux != null && !aux.getElem().equals(elem)) {
                // Si no encuentra una ciudad con ese nombre la inserta como enlace al ultimo
                aux.setSiguienteVertice(new NodoVert(elem));
                exit = true;
            }
        }
        return exit;
    }

    /*
     * Dado el nombre de una ciudad, borra el Vertice de esa misma
     * Retorna false unicamente si no encuentra la ciudad
     * 
     * En caso de encontrar. Utiliza otro metodo para borrar los arcos desde
     * los Vertices destino.
     * Ejemplo: Si desde un vertice A hay un arco hacia el vertice B
     * sabemos que desde B tambien hay un arco hacia el A
     */
    public boolean eliminarVertice(Object elem) {
        boolean exit = false;
        if (inicio != null) {
            NodoVert aux = inicio;
            NodoVert anterior = null;
            // Primero busco el vertice a eliminar
            while (aux != null && !exit) {
                if (aux.getElem().equals(elem)) {
                    exit = true;
                    // Si lo encontro borra todos sus arcos desde los vertices destino
                    borrarAdyacencias(aux);
                    // Si encuentra una ciudad en el primer vertice lo elimina
                    if (anterior == null)
                        inicio = inicio.getSiguienteVertice();
                    else {
                        // Sino enlaza al anterior con el siguiente de ese vertice
                        anterior.setSiguienteVertice(aux.getSiguienteVertice());
                    }
                }
                anterior = aux;
                aux = aux.getSiguienteVertice();

            }
        }
        return exit;
    }

    /*
     * Volvemos al ejemplo anterior. Tenemos:
     * Vertice -arco- Vertice
     * A ------------ B
     * A ------------ C
     * B ------------ A
     * C ------------ A
     * Entonces para cada arco de mi vertice A voy a ver con que vertice esta
     * enlazado, recupero el vertice 'destino' y le borro el arco que este enlazado
     * con A
     */
    private void borrarAdyacencias(NodoVert vertice) {
        NodoAdy primer = vertice.getPrimerArco();
        // Para cada vertice que tenga un arco con el que borraremos
        while (primer != null) {
            NodoVert aux = primer.getVertice();
            // Le borramos el arco desde ese vertice
            aux.eliminarArco(vertice);
            primer = primer.getSiguiente();
        }
    }

    /*
     * Busca si existe una ciudad con ese nombre
     */
    public boolean existeVertice(Object elem) {
        boolean exit = false;
        if (inicio != null) {
            NodoVert aux = inicio;
            while (!exit && aux != null) {
                if (aux.getElem().equals(elem))
                    exit = true;
                aux = aux.getSiguienteVertice();
            }
        }
        return exit;
    }

    /*
     * Si existe una ciudad
     */
    public Object recuperarVertice(Object elem) {
        boolean exit = false;
        Object ret = null;
        if (inicio != null) {
            NodoVert aux = inicio;
            while (!exit && aux != null) {
                if (aux.getElem().equals(elem)) {
                    exit = true;
                    ret = aux.getElem();
                }
                aux = aux.getSiguienteVertice();
            }
        }
        return ret;
    }

    /*
     * Tanto elemOrigen como elemDestino son ciudades auxiliares creadas solo con el
     * nombre. Para ir comparandolas con los Vertices hasta encontrar
     * Cuando encuentra, guarda ese Vertice que utilizo para compara con 'elem'
     * 
     * El metodo retorna false en los siguientes casos:
     * -Grafo vacio
     * -Ciudad origen y/o ciudad destino no existan
     * -Ya exista un arco entre ambas ciudades
     */
    public boolean insertarArco(Object elemOrigen, Object elemDestino, int etiqueta) {
        boolean exit = false, encontrado = false;
        if (inicio != null) {
            NodoVert aux = inicio, origen = null, destino = null;
            while (aux != null && !encontrado) {
                // Si aun no se encontro la ciudad de destino y el nombre coincide, lo guardo
                if (destino == null && aux.getElem().equals(elemDestino))
                    destino = aux;
                // Si aun no se encontro la ciudad de origen y el nombre coincide, lo guardo
                if (origen == null && aux.getElem().equals(elemOrigen))
                    origen = aux;
                // En caso de haber encontrado ambas, encontrado=true para cortar el while
                if (origen != null && destino != null) {
                    encontrado = true;
                    // exit verifica si se pudo o no agregar el arco
                    exit = origen.insertarArco(destino, etiqueta);
                    destino.insertarArco(origen, etiqueta);
                }
                aux = aux.getSiguienteVertice();
            }
        }
        return exit;
    }

    /*
     * Dado dos nombres de ciudades, si existen ambas, elimina el arco
     * (si es que existe) entre la ciudad de origen y la ciudad de destino
     */
    public boolean eliminarArco(Object ciudadOrigen, Object ciudadDestino) {
        boolean exit = false, encontrado = false;
        if (inicio != null) {
            NodoVert aux = inicio, origen = null, destino = null;
            while (aux != null && !encontrado) {
                if (destino == null && aux.getElem().equals(ciudadDestino))
                    destino = aux;
                if (origen == null && aux.getElem().equals(ciudadOrigen))
                    origen = aux;
                if (origen != null && destino != null) {
                    encontrado = true;
                    exit = origen.eliminarArco(destino);
                    destino.eliminarArco(origen);
                }
                aux = aux.getSiguienteVertice();
            }
        }
        return exit;
    }

    @Override
    public String toString() {
        String cad = "Grafo vacio";
        if (inicio != null) {
            cad = "";
            NodoVert aux = inicio;
            while (aux != null) {
                cad += "------------------------------Ciudad-------------------------" + "\n";
                cad += aux.getElem() + ": ";
                cad += "\n" + "----------------------------Conexiones-----------------------"
                        + "\n" + aux.toString() + "\n";
                aux = aux.getSiguienteVertice();
            }
        }
        return cad;
    }
}
