package Tests;

import Dominio.*;
import Estructuras.TablaHash;
import Estructuras.AVL.ArbolAVL;

public class TestPartido {
    public static void main(String[] args) {
        Equipo eq1 = new Equipo("Argentina", "escalo", 'A');
        Equipo eq2 = new Equipo("Canada", "kcyo", 'A');
        Equipo eq3 = new Equipo("Colombia", "xd", 'B');
        Ciudad c = new Ciudad("NEW YORK", false);
        Partido p = new Partido(eq3, eq2, "grupo", c, "MERCEDES-BENZ", 2, 0);
        Partido x = new Partido(eq3, eq2, "semi", c, "MERCEDES-BENZ", 2, 0);
        // System.out.println(p.toString());
        ArbolAVL arbol = new ArbolAVL();
        TablaHash th = new TablaHash();
        th.insertar(p);
        th.insertar(x);
        System.out.println(th.recuperar(eq2, eq3));
        // arbol.insertar(eq3);
        // arbol.insertar(eq1);
        // arbol.insertar(eq2);
        // System.out.println(arbol.toString());
    }

}
