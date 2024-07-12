package Dominio;

public class Partido {
    private Equipo eq1, eq2;
    private String fase, resultado, estadio;
    private Ciudad ciudad;

    public Partido(Equipo uno, Equipo dos, String fase, Ciudad ciudad, String estadio, int resEq1, int resEq2) {
        this.fase = fase;
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

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    /*
     * Partido: nombre eq1, nombre eq2, instancia, ciudad, estadio, goles eq1, goles
     * eq2
     * P: ARGENTINA; CANADA; GRUPO; ATLANTA; MERCEDES-BENZ; 2; 0
     * P: ARGENTINA; CHILE; GRUPO; NEW YORK; METLIFE; 1; 0
     * 
     */
    public String toString() {
        return eq1.getNombre() + "; " + eq2.getNombre() + "; " + fase + "; " + ciudad.getNombre() + "; " + estadio
                + "; " + resultado;
    }
}
