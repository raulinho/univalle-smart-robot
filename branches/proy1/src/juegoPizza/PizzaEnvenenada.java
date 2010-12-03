/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package juegoPizza;

import java.util.ArrayList;
import snakesandladders.Minimax;

/**
 *
 * @author Nathaly
 */
public class PizzaEnvenenada {

    private int porciones;
    private Minimax minmax;
    //private Nodo raiz;

    public PizzaEnvenenada()
    {
        porciones=8;
        minmax= new Minimax();
    }

    //Metodo que utiliza el algoritmo minimax para determinar el numero de piezas que toma la maquina
    public void tomarPieza()
    {
        Nodo raiz=new Nodo(porciones);
        raiz.setTipo("max");
        crearArbol(raiz);
        int c=minmax.minimax(raiz);
        System.out.println("DECISION: tomar "+c+" piezas");
        porciones-=c;
    }

    //Metodo que toma el numero de piezas que decide el jugador
    public void tomarPieza(int piezas)
    {
        porciones-=piezas;
    }

    public void crearArbol(Nodo nod)
    {
        ArrayList hijos=crearHijos(nod);
        nod.setHijos(hijos);
        for(int x=0; x<hijos.size(); x++)
        {
            //System.out.println("Entre for CrearArbol");
            Nodo n=(Nodo)hijos.get(x);
            if(n.getNom()>0)
            {
                //System.out.println("crearArbol para nodo " + n.getNom());
                crearArbol(n);
            }
        }
    }

    private ArrayList crearHijos(Nodo n)
    {
        ArrayList hijos= new ArrayList();
        int cont=n.getNom();
        //System.out.println(n.getTipo());
        if(cont!=0)
        {
            for (int x = 1; x < 4; x++)
            {
                if(cont-x > -1)
                {
                    if(n.getTipo().equals("max"))
                    {
                       // System.out.println("Entre for CrearHijos");
                        Nodo nod= new Nodo(cont-x);
                        nod.setTipo("min");
                        nod.setPiezasTomadas(x);
                       // System.out.println("creado nodo: " +nod.getTipo()+ " "+ nod.getNom()+" tomando "+nod.getPiezasTomadas()+" piezas");
                        hijos.add(nod);
                    }

                    else if(n.getTipo().equals("min"))
                    {
                        //System.out.println("Entre for CrearHijos");
                        Nodo nod= new Nodo(cont-x);
                        nod.setTipo("max");
                        nod.setPiezasTomadas(x);
                        //System.out.println("creado nodo: " +nod.getTipo()+ " " +nod.getNom()+" tomando "+nod.getPiezasTomadas()+" piezas");
                        hijos.add(nod);
                    }
                }
            }
            return hijos;
        }
        return null;
    }

    public int getPorciones() {
        return porciones;
    }

    public void setPorciones(int porciones) {
        this.porciones = porciones;
    }
    
}
