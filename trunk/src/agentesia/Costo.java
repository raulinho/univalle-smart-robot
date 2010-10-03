/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agentesia;

import java.util.PriorityQueue;
import java.util.ArrayList;

/**
 *
 * @author Nathaly
 */
public class Costo extends Busqueda{

    NodoEstado nodoRaiz;
    PriorityQueue<NodoEstado> colaPrioridad;
    ComparadorEstados comparador;

    public Costo(NodoEstado raiz, int cordX, int cordY, int escen[][])
    {
        cordXSalida=cordX;
        cordYSalida=cordY;
        nodoRaiz=raiz;
        comparador= new ComparadorEstados();
        colaPrioridad= new PriorityQueue(1,comparador);
        mapa=escen.clone();
    }

    public NodoEstado ejecutar()
    {
        colaPrioridad.add(nodoRaiz);

        while(!colaPrioridad.isEmpty())
        {
            NodoEstado nodo;
            nodo= colaPrioridad.poll();

            if (esMeta(nodo)) return nodo;

            else
            {
                ArrayList<NodoEstado> nodosHijos= aplicarOperadores(nodo);
                
                for(int x=0; x<nodosHijos.size(); x++)
                {
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
