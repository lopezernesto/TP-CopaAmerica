package Dominio;

/*
 * EquipoGoles es una clase que se utilizara para no sobrecargar a Equipo
 * Se usara para pasar los Equipos del AVL a un Heap y asi poder comparar los equipos por sus GF
 * ya que el compareTo de Equipo es con su nombre
 * 
 * Tambien usa dentro del AVLEspecifico y se crea con el Equipo ** Solo para pruebas **
 */
public class EquipoGoles implements Comparable {
    private String nombre;
    private int golesFavor;

    public EquipoGoles(Comparable equipo) {
        nombre = ((Equipo) equipo).getNombre();
        golesFavor = ((Equipo) equipo).getGolesFavor();
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
