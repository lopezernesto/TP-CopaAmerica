package Dominio;

public class Partido {
    private Equipo eq1, eq2;
    private String ronda, resultado, estadio;
    private Ciudad ciudad;

    /*
     * Este constructor es para "crear" un partido para poder comparar sus claves
     * Ya estan ordenados lexicograficamente
     */
    public Partido(Equipo uno, Equipo dos) {
        eq1 = uno;
        eq2 = dos;
    }

    public Partido(Equipo uno, Equipo dos, String fase, Ciudad ciudad, String estadio, int resEq1, int resEq2) {
        this.ronda = fase;
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

    public boolean equals(Partido p) {
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
        return eq1.getNombre() + "; " + eq2.getNombre() + "; " + ronda + "; " + ciudad.getNombre() + "; " + estadio
                + "; " + resultado;
    }
}
