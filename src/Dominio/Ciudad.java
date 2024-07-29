package Dominio;

public class Ciudad {
    private static int max = 20;
    private String nombre;
    private int cant;
    boolean sede, alojamiento;

    public Ciudad(String nombre) {
        this.nombre = nombre;
    }

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

    @Override
    public boolean equals(Object elem) {
        Ciudad c = (Ciudad) elem;
        return nombre.equalsIgnoreCase(c.nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(boolean alojamiento) {
        this.alojamiento = alojamiento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
