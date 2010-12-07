/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snakesandladders;

/*import juegoPizza.Nodo;*/
import juegoPizza.PizzaEnvenenada;
import juegoPizza.PizzaGUI;
import juegoPrecipicio.Nodo;
import juegoPrecipicio.Precipicio;

/**
 *
 * @author Nathaly
 */

//Para pruebas.
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /*Tablero tab= new Tablero();
        tab.jugar();*/
        PizzaGUI pizzaEnv= new PizzaGUI("jugador1","maquina");
        pizzaEnv.iniciarJuego();
       /* Precipicio prueba= new Precipicio();
        Nodo nod= new Nodo();
        nod.setTipo("max");
        nod.setEscalonJugador(5);
        nod.setEscalonMaquina(3);
        prueba.crearNodos(nod);
        System.out.println("Numero de nodos: "+prueba.cont);*/
    }

}
