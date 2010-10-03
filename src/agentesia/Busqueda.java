/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agentesia;

import java.util.ArrayList;

/**
 *
 * @author jorgeorm
 */
public abstract class Busqueda {
    protected ArrayList<NodoEstado> listaNodos;
    protected int cordXSalida,cordYSalida;
    
    public Busqueda()
    {
        listaNodos=new ArrayList<NodoEstado>();
    }

 
    //A partir de un operador y la dirección del nuevo nodo en el mapa crea un nodo.
    public NodoEstado crearHijo(NodoEstado padre, String operador, int cordX_hijo, int cordY_hijo)
    {
        //Ruta que guardara la ruta del padre y al padre
        String ruta;
        //Costo guardará el costo de avanzar al hijo
        double costo;
        //Nodo estado que se va a retornar
        NodoEstado hijo=null;

        //Si la cadenas estan vacias no usar comas
        if(padre.getOperador().equals("")) operador=padre.getOperador()+operador;
        else operador=padre.getOperador()+","+operador;
        if(padre.getRuta().equals("")) ruta="("+padre.getX()+","+padre.getY()+")";
        else ruta=padre.getRuta()+",("+padre.getX()+","+padre.getY()+")";

        //Memoria para saber que lugares ya visite Solo la utilizo para saber si ya recogí un item.
        int [][] memoriaPadre=padre.getMemoria();
        int [][] memoria = new int [10][10];

        for (int idy=0;idy<10;idy++)
        {
            for (int idx=0;idx<10;idx++)
            {
                memoria[idx][idy]=memoriaPadre[idx][idy];
            }
        }

        //Si en el mapa es libre
        if(memoria[cordX_hijo][cordY_hijo]==0 || memoria[cordX_hijo][cordY_hijo]==3)
        {
            //Si va en nave
            if(padre.getNave()>0)
            {
                costo=padre.getCosto()+0.5;
                hijo= new NodoEstado(ruta,costo,operador,cordX_hijo,cordY_hijo,padre.getN_items(), padre.getN_naves(), padre.getNave()-1,memoria);
            }
            else
            {
                costo=padre.getCosto()+1;
                hijo= new NodoEstado(ruta,costo,operador,cordX_hijo,cordY_hijo,padre.getN_items(), padre.getN_naves(), 0,memoria);
            }
        }
        //Si en el mapa es nave
        else if(memoria[cordX_hijo][cordY_hijo]==4 || memoria[cordX_hijo][cordY_hijo]==5)
        {
            //Si va en nave
            if(padre.getNave()>0)
            {
                costo=padre.getCosto()+0.5;
                //Paso como si nada
                hijo= new NodoEstado(ruta,costo,operador,cordX_hijo,cordY_hijo,padre.getN_items(), padre.getN_naves(), padre.getNave()-1,memoria);
            }
            else
            {
                costo=padre.getCosto()+1;
                memoria[cordX_hijo][cordY_hijo]=0;
                //Lleno combustible
                hijo= new NodoEstado(ruta,costo,operador,cordX_hijo,cordY_hijo,padre.getN_items(), padre.getN_naves()-1, 10,memoria);
            }
        }
        //Si en el mapa es item
        else if(memoria[cordX_hijo][cordY_hijo]==6)
        {
            memoria[cordX_hijo][cordY_hijo]=0;
            //Si va en nave
            if(padre.getNave()>0)
            {
                costo=padre.getCosto()+0.5;
                hijo= new NodoEstado(ruta,costo,operador,cordX_hijo,cordY_hijo,padre.getN_items()-1, padre.getN_naves(), padre.getNave()-1, memoria);
            }
            else
            {
                costo=padre.getCosto()+1;
                hijo= new NodoEstado(ruta,costo,operador,cordX_hijo,cordY_hijo,padre.getN_items()-1, padre.getN_naves(), 0, memoria);
            }
            //escenario[x][yN]=0;
        }
        //Si en el mapa es campo
        else if(memoria[cordX_hijo][cordY_hijo]==7)
        {
            //Si va en nave
            if(padre.getNave()>0)
            {
                costo=padre.getCosto()+0.5+6;
                hijo= new NodoEstado(ruta,costo,operador,cordX_hijo,cordY_hijo,padre.getN_items(), padre.getN_naves(), padre.getNave()-1, memoria);
            }
            else
            {
                costo=padre.getCosto()+1+6;
                hijo= new NodoEstado(ruta,costo,operador,cordX_hijo,cordY_hijo,padre.getN_items(), padre.getN_naves(), 0, memoria);
            }
        }

        return hijo;
    }

    //Determina que operadores se pueden aplicar
    public ArrayList<NodoEstado> aplicarOperadores(NodoEstado nodo)
    {
        int x=nodo.getX();
        int y=nodo.getY();
        ArrayList <NodoEstado> hijos =new ArrayList<NodoEstado>();
        if(y>0)
        {
            int yN=y-1;
            String operador="↑";
            NodoEstado hijo= crearHijo(nodo, operador, x, yN);
            if (hijo!=null)hijos.add(hijo);
        }
        if(x<9)
        {
            int xN=x+1;
            String operador="→";
            NodoEstado hijo= crearHijo(nodo, operador, xN,y);
            if (hijo!=null)hijos.add(hijo);
        }
        if(y<9)
        {
            int yN=y+1;
            String operador="↓";
            NodoEstado hijo= crearHijo(nodo, operador, x, yN);
            if (hijo!=null)hijos.add(hijo);
        }
        if(x>0)
        {
            int xN=x-1;
            String operador="←";
            NodoEstado hijo= crearHijo(nodo, operador, xN, y);
            if (hijo!=null)hijos.add(hijo);
        }

        return hijos;
    }

    public abstract boolean esMeta(NodoEstado nodo);

    public abstract NodoEstado ejecutar ();
}
