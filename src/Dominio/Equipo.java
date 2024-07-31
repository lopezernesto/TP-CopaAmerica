package Dominio;

public class Equipo implements Comparable {
    private String entrenador, nombre, difGol;

    private char grupo;
    private int puntos, golesFavor, golesContra;
    private boolean cuartos, semi, laFinal;

    public Equipo(String nombreEquipo) {
        nombre = nombreEquipo;
    }

    public Equipo(String nombreEquipo, String dt, char grupo) {
        nombre = nombreEquipo;
        entrenador = dt;
        this.grupo = grupo;
        difGol = "0";
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
     * Actualizo difgol
     * Si la difgol > 0 le agrego un "+"
     */
    public void actualizarDifGol() {
        int diferencia = golesFavor - golesContra;
        difGol = ((golesFavor - golesContra) > 0 ? "+" : "") + diferencia;
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
     * tal cual como el usuario lo escribe
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

    @Override
    public String toString() {
        return nombre;
    }

    /*
     * Muestra la info con el formato de una 'tabla'
     * EJ:
     * Nombre: ------- Grupo: Puntos: GF: GC: DF:
     * Argentina ------- A ------ 0 -- 0 - 0 - 0
     */
    public String mostrarInfo() {
        String header = String.format("%-16s %-7s %-7s %-7s %-7s %-7s %-16s", "Nombre:", "Grupo:", "Puntos:", "GF:",
                "GC:",
                "DF:", "Director Tecnico:");
        String equipoInfo = String.format("%-16s %-7c %-7d %-7d %-7d %-7s %-16s",
                nombre, grupo, puntos, golesFavor, golesContra, difGol, entrenador);

        return header + "\n" + equipoInfo;
    }

    public boolean equals(Equipo e) {
        return (nombre.equalsIgnoreCase(e.nombre));
    }

    /*
     * Utilizado a la hora de recuperar una Lista segun los GF
     * El hash de esta clase solo se utiliza para eso
     */
    @Override
    public int hashCode() {
        return golesFavor;
    }

    /*
     * Metodo utilizado para, luego de recuperar la lista ordenada por goles,
     * mostrar el nombre del equipo y la cantidad de goles que tiene
     */
    public String mostrarGoles() {
        return "Nombre del equipo: " + nombre + "\t" + " Cantidad de goles: " + golesFavor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDifGol() {
        return difGol;
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
