package Tests.SegundaOpcionListar;

/* 
 * Debido a las pruebas realizadas se llego a la conclucion que
 * No es factible utilizar el AVLEspecial y por lo tanto no se utilizara esta clase.
 * 
 * Se guardara para futuras pruebas
 */
public class EquipoGoles implements Comparable {
    private String nombre;
    private int golesFavor;

    public EquipoGoles(String nombre, int golesFavor) {
        this.nombre = nombre;
        this.golesFavor = golesFavor;
    }

    @Override
    public int compareTo(Object otro) {
        EquipoGoles e = (EquipoGoles) otro;
        int res = Integer.compare(golesFavor, (e.golesFavor));
        return res;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Nombre del equipo: " + nombre + "\t" + " Cantidad de goles: " + golesFavor + "\n";
    }
}
