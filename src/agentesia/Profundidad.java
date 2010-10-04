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
           /* x=sc.nextInt();
            y=sc.nextInt();
            if(x==nodo.getX()&&y==nodo.getY())
            {
                System.out.println("encontre un ciclo, las coordenadas son iguales");
                count++;
            }*/
            //saco por pedazos el punto de la ruta con el scanner
            cordNodo=sc.next();
            cordNodo=cordNodo+","+sc.next();
            //Si la cadena armada es igual a el punto del nodo actual
            if(cordNodo.equals(cadena))
            {
                count++;
            }
        }
        //TODO Discutir cual será la tolerancia a ciclos a utilizar.
        //tolerancia de aparición de un mismo nodo 1 por ahora
        if (count>1) return true;
        else return false;
    }

    @Override
    public NodoEstado ejecutar() {
        //cargo nodo raiz en cola
        listaNodos.add(raiz);
        //nodo almacenado temporalmente
        NodoEstado nodoActual;
        //cola de hijos producto de aplicar ops
        ArrayList <NodoEstado> colita;
        //PROFUNDIDAD
        NODO:while(!(listaNodos.isEmpty()))
        {
            nodoActual=listaNodos.get(0);
            listaNodos.remove(0);
            //Detecto el nodo como un ciclo y salto si sí es luego verifico meta
            if(esClico(nodoActual))
            {
                System.out.println("Es Ciclo: "+nodoActual.getOperador());
                continue NODO;
            }
            else if(esMeta(nodoActual))return nodoActual;
            //Expando Nodo
            colita=aplicarOperadores(nodoActual);
            //Armo cola general
            colita.addAll(listaNodos);
            listaNodos=(ArrayList <NodoEstado>)colita.clone();
        }
        return null;
    }

}
