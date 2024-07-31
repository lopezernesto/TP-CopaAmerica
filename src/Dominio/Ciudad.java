package Dominio;

public class Ciudad {
    private int max = 20;
    private String nombre;
    boolean sede, alojamiento;

    public Ciudad(String nombre) {
        this.nombre = nombre;
    }

    public Ciudad(String nombre, boolean alojamiento, boolean sede) {
        this.nombre = nombre;
        this.alojamiento = alojamiento;
        this.sede = sede;
    }

    public boolean isSede() {
        return sede;
    }

    public void setSede(boolean sede) {
        this.sede = sede;
    }

    public boolean reservar(int cantidad) {
        boolean exit = false;
        if (alojamiento && max - cantidad >= 0) {
            exit = true;
            max -= cantidad;
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
