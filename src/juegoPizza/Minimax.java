/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package juegoPizza;

import java.util.ArrayList;
import juegoPizza.Nodo;

/**
 *
 * @author Nathaly
 */
public class Minimax {

    //Almacena el numero de piezas que se deben tomar segun la decision minimax
    private int decision;

    public int minimax(Nodo n)
    {
        //Toma de decision para nodos max (jugadas de la maquina)
        if(n.getTipo().equals("max"))
        {
            //Nodo terminal, el jugador tomó la ultima pieza. La utilidad del nodo es 1
            if(n.getNom()==0)
            {
                n.setUtilidad(1);
            }

            //Si el nodo no es terminal la utilidad es la mayor entre los hijos
            else
            {
                ArrayList hijos=n.getHijos();
                int desT=-2;
                for(int x=0; x<hijos.size(); x++)
                {
                    Nodo nod= (Nodo)hijos.get(x);
                    minimax(nod);
                    if(nod.getUtilidad()>desT)
                    {
                        desT=nod.getUtilidad();
                        n.setUtilidad(desT);
                        decision=nod.getPiezasTomadas();
                    }
                }
            }
        }

        //Toma de decision para nodos min (movimientos del jugador)
        else if(n.getTipo().equals("min"))
        {
            //Nodo terminal, la maquina tomo la ultima pieza. La utilidad es -1
            if(n.getNom()==0)
            {
                n.setUtilidad(-1);
            }

            //Para nodos no terminales la utilidad es la menor entre sus hijos
            else
            {
                ArrayList hijos=n.getHijos();
                int desT=2;
                for(int x=0; x<hijos.size(); x++)
                {
                    Nodo nod= (Nodo)hijos.get(x);
                    minimax(nod);
                    if(nod.getUtilidad()<desT)
                    {
                        desT=nod.getUtilidad();
                        n.setUtilidad(desT);
                        decision=nod.getPiezasTomadas();
                    }
                }
            }            
        }
        return decision;
    }

}
