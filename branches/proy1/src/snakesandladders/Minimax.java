/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snakesandladders;

import java.util.ArrayList;
import juegoPizza.Nodo;

/**
 *
 * @author Nathaly
 */
public class Minimax {

    private int decision;

    public int minimax(Nodo n)
    {
        System.out.println("Minimax nodo "+ n.getTipo()+" "+ n.getNom());
        if(n.getTipo().equals("max"))
        {
            if(n.getNom()==0)
            {
                n.setUtilidad(1);
                System.out.println("Utilidad nodo "+ n.getTipo()+" "+ n.getNom()+" es "+n.getUtilidad());
            }

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
                        System.out.println("Utilidad nodo "+ n.getTipo()+" "+ n.getNom()+" es "+n.getUtilidad());
                        decision=nod.getPiezasTomadas();
                    }
                }
            }
        }

        else if(n.getTipo().equals("min"))
        {
            if(n.getNom()==0)
            {
                n.setUtilidad(-1);
            }
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
                        System.out.println("Utilidad nodo "+ n.getTipo()+" "+ n.getNom()+" es "+n.getUtilidad());
                        decision=nod.getPiezasTomadas();
                    }
                }
            }            
        }

        return decision;
    }

}
