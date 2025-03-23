package Estructuras.Grafo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import Estructuras.Lineales.Lista;

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

    /*
     * Dada una ciudad, obtiene el Nodo que la contiene
     */
    private NodoVert obtenerVertice(Object elem) {
        NodoVert aux = inicio;
        NodoVert retorno = null;
        while (aux != null) {
            if (aux.getElem().equals(elem)) {
                retorno = aux;
            }
            aux = aux.getSiguienteVertice();
        }
        return retorno;
    }

    /*
     * Dadas dos ciudades de origen-destino devuelve el camino entre ellos con el
     * menor tiempo de vuelo (si es que existen esas ciudades)
     */
    public Lista caminoMasCorto(Object origen, Object destino) {
        NodoVert verticeOrigen = obtenerVertice(origen);
        NodoVert verticeDestino = obtenerVertice(destino);

        Lista mejorCamino = new Lista();
        Lista[] mejorCaminoRef = { mejorCamino };
        if (verticeOrigen != null && verticeDestino != null) {
            Lista caminoActual = new Lista();
            double[] mejorTiempo = { Double.MAX_VALUE };
            caminoMasCortoAux(false, verticeOrigen, verticeDestino, null, caminoActual, mejorCaminoRef, 0, mejorTiempo);
            mejorCaminoRef[0].insertar(mejorTiempo[0], 1);
        }

        return mejorCaminoRef[0];
    }

    /*
     * Hace lo mismo que el anterior pero evita usar caminos que pasen por X ciudad
     */
    public Lista caminoMasCortoSin(Object origen, Object destino, Object excluido) {
        NodoVert verticeOrigen = obtenerVertice(origen);
        NodoVert verticeDestino = obtenerVertice(destino);
        NodoVert exclu = obtenerVertice(excluido);
        Lista mejorCamino = new Lista();
        Lista[] mejorCaminoRef = { mejorCamino };
        if (verticeOrigen != null && verticeDestino != null) {
            Lista caminoActual = new Lista();
            double[] mejorTiempo = { Double.MAX_VALUE };
            caminoMasCortoAux(true, verticeOrigen, verticeDestino, exclu, caminoActual, mejorCaminoRef, 0, mejorTiempo);
            mejorCamino.insertar(mejorTiempo[0], 1);
        }
        return mejorCaminoRef[0];
    }

    /*
     * Sirve tanto para camino mas corto entre dos ciudades
     * como para camino mas corto que no pase por la ciudad X
     * 
     * Eso indica la variable "excluirNodo".
     * Si la variable excluirNodo es true, significa que el metodo que la invoco es
     * el de encontrar el camino mas corto de A hasta B sin pasar por C. entonces
     * utilizo 'excluido'. Si es false, se ignora eso porque nodo excluido = null
     * 
     * Explicacion de 'continuar':
     * 1) excluirNodo false (lo transforma a true) y la comparacion es true
     * 2) excluirNodo true (lo transforma a false) y depende de la comparacion
     * --Si la comparacion es false(no encontro al nodo que deberia excluir)
     * continuar es: true || false, por ende queda como true
     * --Si la comparacion es true(encontro al nodo que deberia excluir)
     * continuar es: false || false. Por ende no entra en el if.
     * 
     */
    private void caminoMasCortoAux(boolean excluirNodo, NodoVert actual, NodoVert destino, NodoVert excluido,
            Lista caminoActual, Lista[] mejorCamino, double tiempoActual, double[] mejorTiempo) {
        // Agrega la ciudad del nodo al camino
        caminoActual.insertar(actual.getElem(), caminoActual.longitud() + 1);
        // Si llegue a la ciudad Destino
        if (actual.equals(destino)) {
            // Comparo el tiempo
            if (tiempoActual < mejorTiempo[0]) {
                // Si el tiempoActual es mejor:
                // Reemplazo y vacio mi mejorCamino
                mejorTiempo[0] = tiempoActual;
                mejorCamino[0].vaciar();
                // Ahora mi camino actual es el mejor camino
                mejorCamino[0] = caminoActual.clone();
            }
        } else {
            NodoAdy arco = actual.getPrimerArco();
            // Para cada camino de la ciudad actual:
            while (arco != null) {
                // Se toma la ciudad conectada con ese arco
                NodoVert vecino = arco.getVertice();
                // Si la lista NO tiene a esa ciudad (significa que no paso por ahi)
                if (caminoActual.localizar(vecino.getElem()) == -1) {
                    // Explicacion en comentario inicial
                    boolean continuar = !excluirNodo || !vecino.equals(excluido);
                    if (continuar) {
                        // Sumo el tiempo y lo mando por parametro con la nueva ciudad
                        tiempoActual += arco.getEtiqueta();
                        caminoMasCortoAux(excluirNodo, vecino, destino, excluido, caminoActual, mejorCamino,
                                tiempoActual, mejorTiempo);
                        // Resto el tiempo a la vuelta
                        tiempoActual -= arco.getEtiqueta();
                    }
                }
                arco = arco.getSiguiente();
            }
        }
        caminoActual.eliminar(caminoActual.longitud());
    }

    /*
     * Lista el camino entre ciudad origen-destino que pase por la minima cantidad
     * de ciudades sin importar el tiempo de vuelo
     */
    public Lista listarCaminoMinCiudades(Object origen, Object destino) {
        Lista mejorCamino = new Lista();
        Lista[] mejorCaminoRef = { mejorCamino };
        NodoVert verticeOrigen = obtenerVertice(origen);
        NodoVert verticeDestino = obtenerVertice(destino);
        if (verticeOrigen != null && verticeDestino != null) {
            Lista caminoActual = new Lista();
            // Establezco un numero alto para evaluar un camino mas corto
            double[] minCiudadesVisitadas = { 10000 };
            caminoMinimo(verticeOrigen, verticeDestino, caminoActual, mejorCaminoRef, 0, minCiudadesVisitadas);
            mejorCaminoRef[0].insertar(minCiudadesVisitadas[0], 1);
        }
        return mejorCaminoRef[0];
    }

    private void caminoMinimo(NodoVert actual, NodoVert destino, Lista caminoActual, Lista[] mejorCamino,
            double ciudadesVisitadas, double[] minCiudadesVisitadas) {
        // Agrega la ciudad del nodo al camino
        caminoActual.insertar(actual.getElem(), caminoActual.longitud() + 1);
        // Si llegue a la ciudad Destino
        if (actual.equals(destino)) {
            // Comparo los contadores
            if (ciudadesVisitadas < minCiudadesVisitadas[0]) {
                // Si el contador de Ciudades es menor:
                // Reemplazo y vacio mi mejorCamino
                minCiudadesVisitadas[0] = ciudadesVisitadas;
                mejorCamino[0].vaciar();
                // ahora camino actual es mi mejor camino
                mejorCamino[0] = caminoActual.clone();
            }
        } else {
            NodoAdy arco = actual.getPrimerArco();
            // Para cada camino de la ciudad actual:
            while (arco != null) {
                // Se toma la ciudad conectada con ese arco
                NodoVert vecino = arco.getVertice();
                // Si la lista NO tiene a esa ciudad (significa que no paso por ahi)
                if (caminoActual.localizar(vecino.getElem()) == -1) {
                    // Sumo el contador de ciudades
                    ciudadesVisitadas++;
                    caminoMinimo(vecino, destino, caminoActual, mejorCamino, ciudadesVisitadas, minCiudadesVisitadas);
                    ciudadesVisitadas--;

                }
                arco = arco.getSiguiente();
            }
        }
        caminoActual.eliminar(caminoActual.longitud());
    }

    /*
     * Devuelve una Lista de Listas de caminos entre origen-destino
     * para despues ser filtradas
     */
    public Lista listarCaminos(Object origen, Object destino) {
        Lista caminos = new Lista();
        NodoVert verticeOrigen = obtenerVertice(origen);
        NodoVert verticeDestino = obtenerVertice(destino);
        if (verticeOrigen != null && verticeDestino != null) {
            Lista caminoParcial = new Lista();
            listarCaminosAux(verticeOrigen, verticeDestino, caminoParcial, caminos);
        }

        return caminos;
    }

    private void listarCaminosAux(NodoVert actual, NodoVert destino, Lista caminoParcial, Lista caminos) {
        // Agrega la ciudad del nodo al camino
        caminoParcial.insertar(actual.getElem(), caminoParcial.longitud() + 1);
        // Si llegue a la ciudad Destino
        if (actual.equals(destino)) {
            // Inserto el camino encontrado en mi Lista de caminos
            if (caminos.localizar(caminoParcial) == -1) {
                Lista copiaCamino = caminoParcial.clone();
                caminos.insertar(copiaCamino, caminos.longitud() + 1);
            }
        } else {
            NodoAdy arco = actual.getPrimerArco();
            // Para cada camino de la ciudad actual:
            while (arco != null) {
                // Se toma la ciudad conectada con ese arco
                NodoVert vecino = arco.getVertice();
                // Si la lista NO tiene a esa ciudad (significa que no paso por ahi)
                if (caminoParcial.localizar(vecino.getElem()) == -1) {
                    listarCaminosAux(vecino, destino, caminoParcial, caminos);

                }
                arco = arco.getSiguiente();
            }
        }
        caminoParcial.eliminar(caminoParcial.longitud());
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

    /*
     * De aca en adelante los metodos de de A*
     * Solo se utiliza para hacer pruebas
     * 
     */
    public List<Object> caminoAStar(Object origen, Object destino) {
        NodoVert nodoOrigen = obtenerVertice(origen);
        NodoVert nodoDestino = obtenerVertice(destino);

        if (nodoOrigen == null || nodoDestino == null) {
            return null; // Si el origen o destino no existen, retorna null
        }

        PriorityQueue<NodoAStar> frontera = new PriorityQueue<>(Comparator.comparingDouble(n -> n.costoEstimadoTotal));
        Map<NodoVert, NodoAStar> nodosAStar = new HashMap<>();
        Set<NodoVert> visitados = new HashSet<>();

        NodoAStar nodoInicial = new NodoAStar(nodoOrigen, null, 0, heuristica(nodoOrigen, nodoDestino));
        frontera.add(nodoInicial);
        nodosAStar.put(nodoOrigen, nodoInicial);

        while (!frontera.isEmpty()) {
            NodoAStar actual = frontera.poll();

            if (actual.vertice.equals(nodoDestino)) {
                return reconstruirCamino(actual);
            }

            visitados.add(actual.vertice);

            NodoAdy arco = actual.vertice.getPrimerArco();
            while (arco != null) {
                NodoVert vecino = arco.getVertice();
                double costo = actual.costoDesdeInicio + arco.getEtiqueta();

                if (visitados.contains(vecino)) {
                    arco = arco.getSiguiente();
                    continue;
                }

                NodoAStar nodoVecino = nodosAStar.getOrDefault(vecino, new NodoAStar(vecino));
                if (costo < nodoVecino.costoDesdeInicio) {
                    nodoVecino.costoDesdeInicio = costo;
                    nodoVecino.costoEstimadoTotal = costo + heuristica(vecino, nodoDestino);
                    nodoVecino.padre = actual;
                    nodosAStar.put(vecino, nodoVecino);

                    if (!frontera.contains(nodoVecino)) {
                        frontera.add(nodoVecino);
                    }
                }

                arco = arco.getSiguiente();
            }
        }

        return null; // No se encontró camino
    }

    private List<Object> reconstruirCamino(NodoAStar nodo) {
        List<Object> camino = new ArrayList<>();
        while (nodo != null) {
            camino.add(0, nodo.vertice.getElem());
            nodo = nodo.padre;
        }
        return camino;
    }

    private static class NodoAStar {
        NodoVert vertice;
        NodoAStar padre;
        double costoDesdeInicio;
        double costoEstimadoTotal;

        NodoAStar(NodoVert vertice) {
            this(vertice, null, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        }

        NodoAStar(NodoVert vertice, NodoAStar padre, double costoDesdeInicio, double costoEstimadoTotal) {
            this.vertice = vertice;
            this.padre = padre;
            this.costoDesdeInicio = costoDesdeInicio;
            this.costoEstimadoTotal = costoEstimadoTotal;
        }
    }

    private double heuristica(NodoVert a, NodoVert b) {
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }

}
