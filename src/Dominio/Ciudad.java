package Dominio;

public class Ciudad {
    private static int max = 20;
    private String nombre;
    private int cant;
    boolean sede, alojamiento;

    public Ciudad(String nombre, boolean sede) {
        this.nombre = nombre;
        alojamiento = true;
        this.sede = sede;
    }

    public boolean reservar(int cantidad) {
        boolean exit = false;
        if (max - cantidad >= 0) {
            cant = cantidad;
            exit = true;
            max -= cant;
            if (max == 0)
                alojamiento = false;
        }
        return exit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
