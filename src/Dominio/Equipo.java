package Dominio;

public class Equipo implements Comparable {
    private String entrenador, nombre;
    private char grupo;
    private int puntos, golesFavor, golesContra;

    public Equipo(String nombreEquipo, String dt, char grupo, int puntos, int gf, int gc) {
        nombre = nombreEquipo;
        entrenador = dt;
        this.grupo = grupo;
        this.puntos = puntos;
        golesContra = gc;
        golesFavor = gf;
    }

    @Override
    public int compareTo(Object otro) {
        Equipo c = (Equipo) otro;
        return nombre.compareTo(c.getNombre());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(String entrenador) {
        this.entrenador = entrenador;
    }

    public char getGrupo() {
        return grupo;
    }

    public void setGrupo(char grupo) {
        this.grupo = grupo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getGolesFavor() {
        return golesFavor;
    }

    public void setGolesFavor(int golesFavor) {
        this.golesFavor = golesFavor;
    }

    public int getGolesContra() {
        return golesContra;
    }

    public void setGolesContra(int golesContra) {
        this.golesContra = golesContra;
    }

}
