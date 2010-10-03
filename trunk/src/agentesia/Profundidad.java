/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agentesia;

import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author jorgeorm
 */
public class Profundidad extends Busqueda{

    NodoEstado raiz=null;

    public Profundidad(NodoEstado raiz,int cordXf,int cordYf,int [][] escen)
    {
        super();
        this.raiz=raiz;
        this.cordXSalida=cordXf;
        this.cordYSalida=cordYf;
        this.mapa=escen.clone();
    }

    @Override
    public boolean esMeta(NodoEstado nodo) {
        if(nodo.getX()==cordXSalida && nodo.getY()==cordYSalida && nodo.getN_items()==0)
        {
            return true;
        }
        else return false;
    }

    public boolean esClico(NodoEstado nodo)
    {
        String ruta=nodo.getRuta();
        Scanner sc=new Scanner(ruta);
        int x,y;
        int count=0;
        while(sc.hasNextInt())
        {
            x=sc.nextInt();
            y=sc.nextInt();
            if(x==nodo.getX()&&y==nodo.getY())
            {
                count++;
            }
        }
        if (count>1) return true;
        else return false;
    }

    @Override
    public NodoEstado ejecutar() {
        listaNodos.add(raiz);
        NodoEstado nodoActual;
        ArrayList <NodoEstado> colita;
        NODO:while(!(listaNodos.isEmpty()))
        {
            nodoActual=listaNodos.get(0);
            listaNodos.remove(0);
            if(esClico(nodoActual))
            {
                System.out.println("Es Ciclo: "+nodoActual.getOperador());
                continue NODO;
            }
            else if(esMeta(nodoActual))return nodoActual;
            colita=aplicarOperadores(nodoActual);

            colita.addAll(listaNodos);
            listaNodos=(ArrayList <NodoEstado>)colita.clone();
        }
        return null;
    }

}
