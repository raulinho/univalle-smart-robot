/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package juegoPizza;

import java.util.ArrayList;

/**
 *
 * @author Nathaly
 */
public class PizzaEnvenenada {

    private int porciones;
    private Minimax minmax;
    private boolean terminar;
    private String ganador;

    public PizzaEnvenenada()
    {
        porciones=8;
        minmax= new Minimax();
        terminar=false;
        ganador=null;
    }

    //Metodo que utiliza el algoritmo minimax para determinar el numero de piezas que toma la maquina
    public void tomarPieza()
    {
        if(porciones>0)
        {
            Nodo raiz=new Nodo(porciones);
            raiz.setTipo("max");
            crearArbol(raiz);
            int decision=minmax.minimax(raiz);
            System.out.println("DECISION: tomar "+decision+" piezas");
            porciones-=decision;
            if(porciones==0)
            {
                terminar=true;
                ganador="humano";
            }
            System.out.println("El robot toma " + decision + " piezas, quedan por tomar "+porciones);
        }
    }

    //Metodo que toma el numero de piezas que decide el jugador
    public void tomarPieza(int piezas)
    {
        if(porciones>0)
        {
            porciones -= piezas;
            if(porciones==0)
            {
                terminar=true;
                ganador="robot";
            }
            System.out.println("El humano toma " + piezas + " piezas, quedan por tomar "+porciones);
        }
    }

    public void crearArbol(Nodo nod)
    {
        ArrayList hijos=crearHijos(nod);
        nod.setHijos(hijos);
        for(int x=0; x<hijos.size(); x++)
        {
            Nodo n=(Nodo)hijos.get(x);
            if(n.getNom()>0)
            {
                crearArbol(n);
            }
        }
    }

    private ArrayList crearHijos(Nodo n)
    {
        ArrayList hijos= new ArrayList();
        int cont=n.getNom();
        if(cont!=0)
        {
            for (int x = 1; x < 4; x++)
            {
                if(cont-x > -1)
                {
                    if(n.getTipo().equals("max"))
                    {
                        Nodo nod= new Nodo(cont-x);
                        nod.setTipo("min");
                        nod.setPiezasTomadas(x);                   
                        hijos.add(nod);
                    }

                    else if(n.getTipo().equals("min"))
                    {
                        Nodo nod= new Nodo(cont-x);
                        nod.setTipo("max");
                        nod.setPiezasTomadas(x);                      
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

    public void jugar(String jugador1,String jugador2, PizzaGUI interfaz)
    {
       if(jugador1.equals("robot"))
       {
            tomarPieza();
            interfaz.mostrarImagen(porciones);
       }
       while(!terminar)
       {
           int piezas = interfaz.jugada();
           if(piezas!=0&&porciones!=0)
           {
                tomarPieza(piezas);
                interfaz.mostrarImagen(porciones);
                esperar();
                tomarPieza();
                interfaz.mostrarImagen(porciones);
           }
        }

        System.out.println("El ganador es: "+ganador);
        interfaz.anunciarGanador(ganador);
        interfaz.dispose();
    }

    public void esperar()
    {
        try
        {
            Thread.sleep(1000);
        }
        catch(InterruptedException exp)
        {
            
        }
    }

}
