package Tests;

import Dominio.*;
import Estructuras.TablaHash;
import Estructuras.AVL.ArbolAVL;

public class TestPartido {
    public static void main(String[] args) {
        Equipo eq1 = new Equipo("Estados Unidos", "escalo", 'A');
        Equipo eq2 = new Equipo("Canada", "kcyo", 'A');
        Equipo eq3 = new Equipo("Colombia", "xd", 'B');
        Ciudad c = new Ciudad("NEW YORK", false);
        Partido p = new Partido(eq3, eq2, "grupo", c, "MERCEDES-BENZ", 2, 0);
        Partido x = new Partido(eq3, eq2, "semi", c, "MERCEDES-BENZ", 0, 1);
        Partido f = new Partido(eq3, eq2, "cuartos", c, "MERCEDES-BENZ", 0, 11);
        Partido n = new Partido(eq1, eq3, "grupo", c, "ESTADIO", 0, 0);
        // System.out.println(p.toString());
        ArbolAVL arbol = new ArbolAVL();
        TablaHash tabla = new TablaHash();
        System.out.println(eq1.mostrarInfo());
        System.out.println(eq2.mostrarInfo());
        System.out.println(eq3.mostrarInfo());
        // System.out.println(tabla.insertar(n));
        System.out.println(tabla.insertar(p));
        // System.out.println(tabla.insertar(x));
        System.out.println(tabla.insertar(f));
        System.out.println(tabla.recuperar1(eq3, eq2));

        System.out.println(tabla.recuperarTest("Argentina", "Canadá") + " - Argentina" + "Canadá");
        System.out.println(tabla.recuperarTest("Perú", "Chile") + " - Perú" + "Chile");
        System.out.println(tabla.recuperarTest("Ecuador", "Venezuela") + " - Ecuador" + "Venezuela");
        System.out.println(tabla.recuperarTest("México", "Jamaica") + " - México" + "Jamaica");
        System.out.println(tabla.recuperarTest("Estados Unidos", "Bolivia") + " - Estados Unidos" + "Bolivia");
        System.out.println(tabla.recuperarTest("Uruguay", "Panamá") + " - Uruguay" + "Panamá");
        System.out.println(tabla.recuperarTest("Colombia", "Paraguay") + " - Colombia" + "Paraguay");
        System.out.println(tabla.recuperarTest("Brasil", "Costa Rica") + " - Brasil" + "Costa Rica");
        System.out.println(tabla.recuperarTest("Perú", "Canadá") + " - Perú" + "Canadá");
        System.out.println(tabla.recuperarTest("Chile", "Argentina") + " - Chile" + "Argentina");
        System.out.println(tabla.recuperarTest("Ecuador", "Jamaica") + " - Ecuador" + "Jamaica");
        System.out.println(tabla.recuperarTest("Venezuela", "México") + " - Venezuela" + "México");
        System.out.println(tabla.recuperarTest("Panamá", "Estados Unidos") + " - Panamá" + "Estados Unidos");
        System.out.println(tabla.recuperarTest("Uruguay", "Bolivia") + " - Uruguay" + "Bolivia");
        System.out.println(tabla.recuperarTest("Colombia", "Costa Rica") + " - Colombia" + "Costa Rica");
        System.out.println(tabla.recuperarTest("Paraguay", "Brasil") + " - Paraguay" + "Brasil");
        System.out.println(tabla.recuperarTest("Argentina", "Perú") + " - Argentina" + "Perú");
        System.out.println(tabla.recuperarTest("Canadá", "Chile") + " - Canadá" + "Chile");
        System.out.println(tabla.recuperarTest("México", "Ecuador") + " - México" + "Ecuador");
        System.out.println(tabla.recuperarTest("Jamaica", "Venezuela") + " - Jamaica" + "Venezuela");
        System.out.println(tabla.recuperarTest("Bolivia", "Panamá") + " - Bolivia" + "Panamá");
        System.out.println(tabla.recuperarTest("Estados Unidos", "Uruguay") + " - Estados Unidos" + "Uruguay");
        System.out.println(tabla.recuperarTest("Brasil", "Colombia") + " - Brasil" + "Colombia");
        System.out.println(tabla.recuperarTest("Costa Rica", "Paraguay") + " - Costa Rica" + "Paraguay");
        System.out.println("Cuartos");
        // Cuartos
        System.out.println(tabla.recuperarTest("Argentina", "Ecuador") + " - Argentina" + "Ecuador");
        System.out.println(tabla.recuperarTest("Colombia", "Panamá") + " - Colombia" + "Panamá");
        System.out.println(tabla.recuperarTest("Venezuela", "Canadá") + " - Venezuela" + "Canadá");
        System.out.println(tabla.recuperarTest("Uruguay", "Brasil") + " - Uruguay" + "Brasil");
        System.out.println("Semis");

        // Semi
        System.out.println(tabla.recuperarTest("Argentina", "Canadá") + " - Argentina" + "Canadá");
        System.out.println(tabla.recuperarTest("Colombia", "Uruguay") + " - Colombia" + "Uruguay");
        System.out.println("Final");

        // Final
        System.out.println(tabla.recuperarTest("Argentina", "Colombia") + " - Argentina" + "Colombia");
        System.out.println("3er");

        // 3er puesto
        System.out.println(tabla.recuperarTest("Canadá", "Uruguay") + " - Canadá" + "Uruguay");

        // th.insertar(p);
        // th.insertar(x);
        // th.insertar(n);

        // arbol.insertar(eq3);
        // arbol.insertar(eq1);
        // arbol.insertar(eq2);
        // System.out.println(arbol.toString());
    }

}
