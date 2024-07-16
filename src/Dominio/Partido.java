package Dominio;

public class Partido {
    private Equipo eq1, eq2;
    private String ronda, resultado, estadio;
    private Ciudad ciudad;

    /*
     * Este constructor es para "crear" un partido para poder comparar sus claves
     */
    public Partido(Equipo uno, Equipo dos, String ronda) {
        this.ronda = ronda;
        if (uno.compareTo(dos) <= 0) {
            eq1 = uno;
            eq2 = dos;
        } else {
            eq1 = dos;
            eq2 = uno;
        }
    }

    public Partido(Equipo uno, Equipo dos, String ronda, Ciudad ciudad, String estadio, int resEq1, int resEq2) {
        this.ronda = ronda;
        this.ciudad = ciudad;
        this.estadio = estadio;
        if (uno.compareTo(dos) <= 0) {
            eq1 = uno;
            resultado = resEq1 + ":" + resEq2;
            eq2 = dos;
        } else {
            eq1 = dos;
            resultado = resEq2 + ":" + resEq1;
            eq2 = uno;
        }
        modificarEquipos(eq1, eq2, ronda, resEq1, resEq2);
    }

    /*
     * Segun el resultado del partido modifica los equipos
     * Aclaracion, los Setters no modifican la variable
     * simplemente le suman a lo que ya tienen
     */
    private void modificarEquipos(Equipo uno, Equipo dos, String ronda, int resEq1, int resEq2) {
        // Si la ronda no es por grupos, la modifico
        if (!ronda.equals("grupo")) {
            uno.modificarRonda(ronda);
            dos.modificarRonda(ronda);
        }
        // Si el equipo 1 marco algun gol
        if (resEq1 != 0) {
            uno.sumarGolesFavor(resEq1);
            dos.sumarGolesContra(resEq1);
        }
        // Si el equipo 2 marco algun gol
        if (resEq2 != 0) {
            uno.sumarGolesContra(resEq2);
            dos.sumarGolesFavor(resEq2);
        }
        // Si empataron
        if (resEq1 == resEq2) {
            uno.sumarPuntos(1);
        } else {
            // Si gano el equipo2
            if (resEq1 < resEq2) {
                dos.sumarPuntos(3);
            } else {
                // Si gano el equipo1
                uno.sumarPuntos(3);
            }
        }
    }

    public int hashCode() {
        String n1 = eq1.getNombre(), n2 = eq2.getNombre();
        int i = 0, hash = 5381;
        while (i <= n1.length() - 1) {
            hash = (hash * 33) + n1.charAt(i++);
        }
        i = 0;
        while (i <= n2.length() - 1) {
            hash = (hash * 33) + n2.charAt(i++);
        }
        return Math.abs(hash);
    }

    @Override
    public boolean equals(Object elem) {
        Partido p = (Partido) elem;
        return (eq1.equals(p.eq1) && eq2.equals(p.eq2) && ronda.equals(p.ronda));
    }

    public String getEquipo1() {
        return eq1.getNombre();
    }

    public void setEquipo1(Equipo eq1) {
        this.eq1 = eq1;
    }

    public String getEquipo2() {
        return eq2.getNombre();
    }

    public void setEquipo2(Equipo eq2) {
        this.eq2 = eq2;
    }

    public String getRonda() {
        return ronda;
    }

    public void setRonda(String fase) {
        this.ronda = fase;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String toString() {
        return eq1.getNombre() + "; " + eq2.getNombre() + "; " + ronda + "; "/*
                                                                              * + ciudad.getNombre() + "; " + estadio
                                                                              * + "; "
                                                                              */ + resultado;
    }
}
