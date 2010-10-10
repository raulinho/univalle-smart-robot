/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agentesia;

import java.util.PriorityQueue;
import java.util.ArrayList;
/**
 *
 * @author Administrator
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
        tipoHeu=h;
    }

    public NodoEstado ejecutar()
    {
        double h;
        if(tipoHeu==1)
        {
            colaPrioridad.add(nodoRaiz);

            while(!colaPrioridad.isEmpty())
            {
                NodoEstado nodo, n1;
                nodo= colaPrioridad.poll();

                if (esMeta(nodo)) return nodo;
                else
                {
                    ArrayList<NodoEstado> nodosHijos= aplicarOperadores(nodo);

                    for(int x=0; x<nodosHijos.size(); x++)
                    {
                        n1 = nodosHijos.get(x);
                        h = aplicarH1(n1);
                        n1.setCosto_est(n1.getCosto()+h);
                        colaPrioridad.add(nodosHijos.get(x));
                    }
                }
            }
            return null;
        }
        else if (tipoHeu==2)
        {
            colaPrioridad.add(nodoRaiz);

            while(!colaPrioridad.isEmpty())
            {
                NodoEstado nodo, n1;
                nodo= colaPrioridad.poll();

                if (esMeta(nodo)) return nodo;
                else
                {
                    ArrayList<NodoEstado> nodosHijos= aplicarOperadores(nodo);

                    for(int x=0; x<nodosHijos.size(); x++)
                    {
                        n1 = nodosHijos.get(x);
                        h = aplicarH2(n1);
                        n1.setCosto_est(n1.getCosto()+h);
                        colaPrioridad.add(nodosHijos.get(x));
                    }
                }
            }
            return null;
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
