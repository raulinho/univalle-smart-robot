/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agentesia;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Gina
 */
public class Avara extends Busqueda {

    NodoEstado nodoRaiz;
    PriorityQueue<NodoEstado> colaPrioridad;
    ComparadorEstadosInformada comparador;
    int tipoHeu;

    public Avara(NodoEstado raiz, int cordX, int cordY, int escen[][], int h)
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
                if(mapa[idx][idy]==6||mapa[idx][idy]==5)
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
                }
            }
        }
        tipoHeu=h;
    }

    public NodoEstado ejecutar()
    {
        double h;

        colaPrioridad.add(nodoRaiz);

       NODO:while(!colaPrioridad.isEmpty())
        {
            NodoEstado nodoActual, nodoTmp;
            nodoActual= colaPrioridad.poll();

            //Detecto el nodo como un ciclo y salto si sí es luego verifico meta
            if(esClico(nodoActual))
            {
                System.out.println("Es Ciclo: "+nodoActual.getOperador());
                continue NODO;
            }
            else if (esMeta(nodoActual)) return nodoActual;
                else
                {
                    ArrayList<NodoEstado> nodosHijos= aplicarOperadores(nodoActual);

                    for(int x=0; x<nodosHijos.size(); x++)
                    {
                        nodoTmp = nodosHijos.get(x);
                        //dependiendo de la heuristica a aplicar asigno h
                        if(tipoHeu==1) h = aplicarH1(nodoTmp);
                        else h= aplicarH2(nodoTmp);
                        nodoTmp.setCosto_est(h);
                        colaPrioridad.add(nodosHijos.get(x));
                    }
                }
        }
        return null;
    }

    public boolean esClico(NodoEstado nodo)
    {
        //ruta
        String ruta=nodo.getRuta();
        //scanner que mirara la ruta en busca de puntos
        Scanner sc=new Scanner(ruta);
        //Delimitador para reconocer puntos
        sc.useDelimiter(",");
        //string donde se concatenara temporalmente nodo extraido de la ruta
        String cordNodo;
        //punto con la coordenada del nodo actual
        String cadena= "("+ nodo.getX() + "," +nodo.getY() + ")";
        //contador de apariciones
        int count=0;
        while(sc.hasNext())
        {
           //saco por pedazos el punto de la ruta con el scanner
            cordNodo=sc.next();
            cordNodo=cordNodo+","+sc.next();
            //Si la cadena armada es igual a el punto del nodo actual
            if(cordNodo.equals(cadena))
            {
                count++;
            }
        }
        if (count>1) return true;
        else return false;
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
