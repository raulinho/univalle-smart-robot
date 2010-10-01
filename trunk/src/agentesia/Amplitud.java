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
//TODO Clase amplitud incompleta tiene error de memoria.
public class Amplitud extends Busqueda {
    private NodoEstado nodoraiz;
    private int [][] mapa;

    public Amplitud(NodoEstado raiz, int cordXf,int cordYf , int[][] mapa)
    {
        super();
        this.mapa=new int [10][10];
        cordXSalida=cordXf;
        cordYSalida=cordYf;
        nodoraiz=raiz;
        for(int idy=0;idy<10;idy++)
        {
            for(int idx=0;idx<10;idx++)
            {
                this.mapa[idx][idy]=mapa[idx][idy];
            }
        }
    }

     public NodoEstado ejecutar()
    {
        listaNodos.add(nodoraiz);

        while(!(listaNodos.isEmpty()))
        {
            NodoEstado nodoActual=listaNodos.get(0);
            if(esMeta(nodoActual))
            {
                return nodoActual;
            }
            else
            {
                //Expando el nodo por amplitud
                listaNodos.remove(0);
                listaNodos.addAll(aplicarOperadores(nodoActual));
            }
        }
        return null;
    }

    //Determina que operadores se pueden aplicar y evita devolverse a menos que haya un item en el mapa o una nave
    @Override
    public ArrayList<NodoEstado> aplicarOperadores(NodoEstado nodo)
    {
        int x=nodo.getX();
        int y=nodo.getY();


        boolean lugarDevuelta=mapa[x][y]==6||mapa[x][y]==5||mapa[x][y]==4;

        String lastOperador=nodo.getOperador();
        System.out.println(lastOperador);
        if(lastOperador.length()>0)lastOperador=lastOperador.charAt((lastOperador.length()-1))+"";

        ArrayList <NodoEstado> hijos =new ArrayList<NodoEstado>();
        if(y>0)
        {
            int yN=y-1;
            String operador="↑";
            NodoEstado hijo= null;
            if(lastOperador!="↓"||lugarDevuelta)hijo= crearHijo(nodo, operador, x, yN);
            if (hijo!=null)hijos.add(hijo);
        }
        if(x<9)
        {
            int xN=x+1;
            String operador="→";
            NodoEstado hijo= null;
            if(lastOperador!="←"||lugarDevuelta)hijo= crearHijo(nodo, operador, xN,y);
            if (hijo!=null)hijos.add(hijo);
        }
        if(y<9)
        {
            int yN=y+1;
            String operador="↓";
            NodoEstado hijo= null;
            if(lastOperador!="↑"||lugarDevuelta)hijo= crearHijo(nodo, operador, x, yN);
            if (hijo!=null)hijos.add(hijo);
        }
        if(x>0)
        {
            int xN=x-1;
            String operador="←";
            NodoEstado hijo= null;
            if(lastOperador!="→"||lugarDevuelta)hijo= crearHijo(nodo, operador, xN,y);
            if (hijo!=null)hijos.add(hijo);
        }

        return hijos;
    }

    public boolean esMeta(NodoEstado nodo)
    {
        if(nodo.getX()==cordXSalida && nodo.getY()==cordYSalida && nodo.getN_items()<=0)
        {
            return true;
        }
        else return false;
    }
}
