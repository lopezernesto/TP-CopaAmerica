package Dominio;

public class Equipo implements Comparable {
    private String entrenador, nombre, difGol;

    private char grupo;
    private int puntos, golesFavor, golesContra;
    private boolean cuartos, semi, laFinal;

    public Equipo(String nombreEquipo, String dt, char grupo) {
        nombre = nombreEquipo;
        entrenador = dt;
        this.grupo = grupo;
        puntos = 0;
        golesContra = 0;
        golesFavor = 0;
        cuartos = false;
        semi = false;
        laFinal = false;
    }

    /*
     * Los siguientes tres metodos son invocados desde Partido
     * Cuando se establece un partido, en base a su resultado
     * modifica los equipo
     */
    public void sumarGolesFavor(int goles) {
        golesFavor += goles;
    }

    public void sumarGolesContra(int goles) {
        golesContra += goles;
    }

    public void sumarPuntos(int puntos) {
        this.puntos += puntos;
    }

    /*
     * Metodo que verifica si el equipo ya jugo esa ronda
     * En caso de ya haberla jugado, no podra crearse el partido
     */
    public boolean jugoRonda(String ronda) {
        boolean ret = false;
        switch (ronda) {
            case "cuartos":
                ret = cuartos;
                break;
            case "semis":
                ret = semi;
                break;
            case "final":
                ret = laFinal;
                break;
            default:
                break;
        }
        return ret;
    }

    /*
     * Metodo que modifica la ronda, segun cual hayan jugado
     * Esto lo hace una vez que ya se creo el partido
     */
    public void modificarRonda(String ronda) {
        switch (ronda) {
            case "cuartos":
                cuartos = true;
                break;
            case "semis":
                semi = true;
                break;
            case "final":
                laFinal = true;
                break;
            default:
                break;
        }
    }

    /*
     * Si los nombres son iguales pero estan escrito de diferente manera
     * (o sea, alternando mayusculas y minusculas) devuelve 0 igualmente
     * 
     * Las comparaciones son en minusculas pero el nombre lo inserta
     * tal cual como el usuario lo pide
     */
    @Override
    public int compareTo(Object otro) {
        Equipo c = (Equipo) otro;
        // Lo que ingresa por parametro lo pongo en minusculas para comparar
        String segundo = c.nombre.toLowerCase();
        // Creo la variable aux para guardar el nombre original en minuscula
        String aux = nombre.toLowerCase();
        // Retorno la comparacion de ambos nombres en minuscula
        return aux.compareTo(segundo);
    }

    public String toString() {
        return "Nombre:   Grupo: Puntos: GF: GC: DF:" + "\n" + nombre + "    " + grupo + "      " + puntos + "    "
                + golesFavor + "  "
                + golesContra
                + "  " + difGol;
    }

    public boolean equals(Equipo e) {
        return (nombre.equalsIgnoreCase(e.nombre));
    }

    public String getNombre() {
        return nombre;
    }

    public String getDifGol() {
        return difGol;
    }

    public void setDifGol(String difGol) {
        this.difGol = difGol;
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
