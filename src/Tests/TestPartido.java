package Tests;

import Dominio.*;
import Estructuras.AVL.ArbolAVL;

public class TestPartido {
    public static void main(String[] args) {
        Equipo eq1 = new Equipo("Argentina", "escalo", 'A', 4, 10, 2);
        Equipo eq2 = new Equipo("Canada", "kcyo", 'A', 5, 10, 2);
        Equipo eq3 = new Equipo("Colombia", "xd", 'B', 0, 0, 0);
        Ciudad c = new Ciudad("NEW YORK", false);
        Partido p = new Partido(eq1, eq2, "grupo", c, "MERCEDES-BENZ", 02, 0);
        System.out.println(p.toString());
        ArbolAVL arbol = new ArbolAVL();

        arbol.insertar(eq3);
        arbol.insertar(eq1);
        arbol.insertar(eq2);
        System.out.println(arbol.toString());

    }

}
