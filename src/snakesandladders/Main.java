/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snakesandladders;

import juegoPizza.Nodo;
import juegoPizza.PizzaEnvenenada;
import tablero.Tablero;

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

        Tablero tab= new Tablero();
        tab.jugar();
        PizzaEnvenenada piz= new PizzaEnvenenada();
        piz.tomarPieza();

    }

}
