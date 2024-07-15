package Dominio;

public class Equipo implements Comparable {
    private String entrenador, nombre;
    private char grupo;
    private int puntos, golesFavor, golesContra;

    /*
     * Este constructor es para "crear" un equipo para poder compararlo en mi AVL
     * Asi de esta manera lo puedo eliminar
     */
    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    public Equipo(String nombreEquipo, String dt, char grupo) {
        nombre = nombreEquipo;
        entrenador = dt;
        this.grupo = grupo;
        puntos = 0;
        golesContra = 0;
        golesFavor = 0;
    }

    @Override
    public int compareTo(Object otro) {
        Equipo c = (Equipo) otro;
        return nombre.compareTo(c.nombre);
    }

    public boolean equals(Equipo e) {
        return (nombre.equals(e.nombre));
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
