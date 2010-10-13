/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agentesia;

import java.util.PriorityQueue;
import java.util.ArrayList;
/**
 *
 * @author Gina
 */
public class A_estrella extends Busqueda{

    NodoEstado nodoRaiz;
    PriorityQueue<NodoEstado> colaPrioridad;
    ComparadorEstadosInformada comparador;
    int tipoHeu;

    public A_estrella(NodoEstado raiz, int cordX, int cordY, int escen[][], int h)
    {
        nodoRaiz=raiz;
        cordXSalida=cordX;
        cordYSalida=cordY;
        comparador= new ComparadorEstadosInformada();
        colaPrioridad= new PriorityQueue(1,comparador);
        mapa=escen.clone();
        for(int idx=0;idx<10;idx++)
        {
            for(int idy=0;idy<10;idy++)
            {
                if(mapa[idx][idy]==6)
                {
                    if(cordXItem1==0 && cordYItem1==0)
                    {
                        cordXItem1=idx;
                        cordYItem1=idy;
                    }else
                    {
                        cordXItem2=idx;
                        cordYItem2=idy;
                    }
                }else if(mapa[idx][idy]==4)
                {
                    cordXNave1=idx;
                    cordYNave1=idy;
                }else if(mapa[idx][idy]==5)
                {
                    cordXNave2=idx;
                    cordYNave2=idy;
                }
            }
        }
        tipoHeu=h;
    }

    public NodoEstado ejecutar()
    {
        double h;
        
        colaPrioridad.add(nodoRaiz);

        while(!colaPrioridad.isEmpty())
        {
            NodoEstado nodoActual, nodoTmp;
            nodoActual= colaPrioridad.poll();

            if (esMeta(nodoActual)) return nodoActual;
            else
            {
                ArrayList<NodoEstado> nodosHijos= aplicarOperadores(nodoActual);

                for(int x=0; x<nodosHijos.size(); x++)
                {
                    nodoTmp = nodosHijos.get(x);
                    //dependiendo de la heuristica a aplicar asigno h
                    if(tipoHeu==1) h = aplicarH1(nodoTmp);
                    else h= aplicarH2(nodoTmp);
                    nodoTmp.setCosto_est(nodoTmp.getCosto()+h);
                    colaPrioridad.add(nodosHijos.get(x));
                }
            }
        }
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
