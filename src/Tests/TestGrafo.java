package Tests;

import Dominio.Ciudad;
import Estructuras.Grafo.Grafo;
import Estructuras.Lineales.Lista;

public class TestGrafo {
        public static void main(String[] args) {
                Grafo grafo = new Grafo();
                Ciudad c = new Ciudad("Cipolletti");
                Ciudad cipo = new Ciudad("Cipolletti", false);
                Ciudad nqn = new Ciudad("Neuquen", true);
                Ciudad seni = new Ciudad("Senillosa", false);
                Ciudad plot = new Ciudad("Plottier", true);
                Ciudad roca = new Ciudad("General Roca", true);
                Ciudad allen = new Ciudad("Allen", false);
                Ciudad regina = new Ciudad("Villa Regina", true);
                Ciudad cs = new Ciudad("Cinco Saltos", false);
                Ciudad cutral = new Ciudad("Cutral Co", true);
                Ciudad cente = new Ciudad("Centenario", false);
                Ciudad sm = new Ciudad("San Martin de los Andes", true);
                Ciudad zap = new Ciudad("Zapala", false);
                Ciudad chosma = new Ciudad("Chos Malal", true);
                Ciudad rincon = new Ciudad("Rincon de los Sauces", false);
                Ciudad cholar = new Ciudad("El Cholar", true);
                Ciudad huecu = new Ciudad("El Huecu", false);
                Ciudad picun = new Ciudad("Picun Leufu", true);
                Ciudad cav = new Ciudad("Caviahue", false);
                // System.out.println(grafo.insertarVertice(c));
                System.out.println(grafo.insertarVertice(cipo));
                System.out.println(grafo.insertarVertice(nqn));
                System.out.println(grafo.insertarVertice(plot));
                System.out.println(grafo.insertarVertice(seni));
                System.out.println(grafo.insertarVertice(roca));
                System.out.println(grafo.insertarVertice(allen));
                System.out.println(grafo.insertarVertice(regina));
                System.out.println(grafo.insertarVertice(cs));
                System.out.println(grafo.insertarVertice(cutral));
                System.out.println(grafo.insertarVertice(cente));
                System.out.println(grafo.insertarVertice(sm));
                System.out.println(grafo.insertarVertice(zap));
                System.out.println(grafo.insertarVertice(chosma));
                System.out.println(grafo.insertarVertice(rincon));
                System.out.println(grafo.insertarVertice(cholar));
                System.out.println(grafo.insertarVertice(huecu));
                System.out.println(grafo.insertarVertice(picun));
                System.out.println(grafo.insertarVertice(cav));

                // Inserción de arcos y verificación
                System.out.println("Insertar arco Cipolletti - Neuquen: " + grafo.insertarArco(cipo, nqn, 10));
                System.out.println("Insertar arco Cipolletti - Plottier: " + grafo.insertarArco(cipo, plot, 20));
                System.out.println("Insertar arco Cipolletti - Cinco Saltos: " + grafo.insertarArco(cipo, cs, 8));
                System.out.println("Insertar arco Cipolletti - Zapala: " + grafo.insertarArco(cipo, zap, 300));
                System.out.println("Insertar arco Cipolletti - Allen: " + grafo.insertarArco(cipo, allen, 65));
                System.out.println("Insertar arco Cipolletti - Allen: " + grafo.insertarArco(cipo, allen, 65));

                System.out.println("Insertar arco General Roca - Allen: " + grafo.insertarArco(roca, allen, 25));
                // cambiar plot por una ciudad sin arcos
                System.out.println("Insertar arco General Roca - Plottier: " + grafo.insertarArco(roca, plot, 60));
                System.out.println("Insertar arco General Roca - Centenario: " + grafo.insertarArco(roca, cente, 25));

                System.out.println("Insertar arco Allen - Senillosa: " + grafo.insertarArco(allen, seni, 45));
                System.out.println("Insertar arco Allen - Centenario: " + grafo.insertarArco(allen, cente, 12));

                System.out.println("Insertar arco Neuquen - Senillosa: " + grafo.insertarArco(nqn, seni, 20));
                System.out.println("Insertar arco Neuquen - Cutral Co: " + grafo.insertarArco(nqn, plot, 90));
                System.out.println("Insertar arco Neuquen - Zapala: " + grafo.insertarArco(nqn, zap, 45));

                System.out.println("Insertar arco Zapala - Plottier: " + grafo.insertarArco(zap, plot, 130));

                System.out.println("Insertar arco Cutral Co - Plottier: " + grafo.insertarArco(cutral, plot, 100));
                System.out.println("Insertar arco Cutral Co - Senillosa: " + grafo.insertarArco(cutral, seni, 65));

                System.out.println("Insertar arco Cinco Saltos - Centenario: " + grafo.insertarArco(cs, cente, 30));

                System.out.println(grafo.toString());
                // Lista l0 = grafo.caminoMasCorto(cipo, cente);
                // System.out.println(l0.toString());
                // System.out.println("----------------------");
                // Lista l = grafo.caminoMasCortoSin(cipo, cente, cs);
                // System.out.println(l.toString());
                // Lista l1 = grafo.listarCaminos(cipo, cente);
                // System.out.println("--------acaaa---------");
                // for (int i = 1; i <= l1.longitud(); i++) {
                // Lista x = (Lista) l1.recuperar(i);
                // System.out.println(x.toString());
                // }
        }
}
