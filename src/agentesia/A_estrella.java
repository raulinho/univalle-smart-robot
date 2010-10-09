/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agentesia;

import java.math.*;

/**
 *
 * @author Administrator
 */
public class A_estrella extends Busqueda{

    NodoEstado nodoRaiz;

    public A_estrella(NodoEstado raiz, int cordX, int cordY, int escen[][])
    {
        nodoRaiz=raiz;
        cordXSalida=cordX;
        cordYSalida=cordY;
        //comparador= new ComparadorEstados();
        //colaPrioridad= new PriorityQueue(1,comparador);
        mapa=escen.clone();
    }

    //copy paste... como carajos aplico heuristica al nodo hijo si apenas lo estoy creando
    @Override
    public NodoEstado crearHijo(NodoEstado padre, String operador, int cordX_hijo, int cordY_hijo)
    {
        String ruta; //Ruta que guardara la ruta del padre y al padre
        double costo; //Costo guardará el costo de avanzar al hijo
        double costo_e;
        NodoEstado hijo=null; //Nodo estado que se va a retornar

        try{

        //Si la cadenas estan vacias no usar comas
        if(padre.getOperador().equals("")) operador=padre.getOperador()+operador;
        else operador=padre.getOperador()+","+operador;
        if(padre.getRuta().equals("")) ruta="("+padre.getX()+","+padre.getY()+")";
        else ruta=padre.getRuta()+",("+padre.getX()+","+padre.getY()+")";
        }catch(OutOfMemoryError exc)
        {
            System.err.println("LLego al limite de memoria de java este nodo no se expande");
            System.err.println("Nodo: "+padre.getX()+","+padre.getY());
            System.err.println("Profundidad: "+padre.getProfundidadPorOps());
            System.out.println("Operadores: "+padre.getOperador());
            return null;
        }
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

    public NodoEstado ejecutar()
    {
        return null;
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
