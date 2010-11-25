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

        //Ciclo que recorre el ambiente y me provee las coordenadas de los items
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
                }
            }
        }
        tipoHeu=h;
    }

   @Override
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
        //Cuento al nodo expandido
        contNodos++;

        return hijos;
    }

    public NodoEstado ejecutar()
    {
        double h;

        colaPrioridad.add(nodoRaiz);

      while(!colaPrioridad.isEmpty())
        {
            NodoEstado nodoActual, nodoTmp;
            nodoActual= colaPrioridad.poll();
            colaPrioridad.clear();
            System.out.println("Nodo Actual: "+nodoActual.getX()+","+nodoActual.getY());
            System.out.println("Heurisitica del nodo: "+nodoActual.getCosto_est());

            //Detecto el nodo como un ciclo y salto si sí es luego verifico meta
            if(esClico(nodoActual))
            {
                System.out.println("Es Ciclo:"+nodoActual.getX()+","+nodoActual.getY()+" \n"+nodoActual.getOperador());
                System.out.println("Numero items en el mapa: " + nodoActual.getN_items());
                //int [][]map = nodoActual.getMemoria().clone();
                //System.out.println("Numero en posicion del primer item: " + map[cordXItem1][cordYItem1]);
                return nodoActual;
            }
            else if (esMeta(nodoActual)) return nodoActual;
                else
                {
                    ArrayList<NodoEstado> nodosHijos= aplicarOperadores(nodoActual);

                    for(int x=0; x<nodosHijos.size(); x++)
                    {
                        nodoTmp = nodosHijos.get(x);

                        String items=nodoActual.getItemEnPadre();
                        if(items.equals("")) items=nodoActual.getN_items()+"";
                        else items=nodoActual.getItemEnPadre()+","+nodoActual.getN_items();
                        nodoTmp.setItemEnPadre(items);
                        //colaPrioridad.clear();
                        //dependiendo de la heuristica a aplicar asigno h
                        if(tipoHeu==1) h = aplicarH1(nodoTmp);
                        else h= aplicarH2(nodoTmp);
                        nodoTmp.setCosto_est(h);
                        colaPrioridad.add(nodoTmp);
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
        int numeroPunto=0;
        while(sc.hasNext())
        {
            //saco por pedazos el punto de la ruta con el scanner
            cordNodo=sc.next();
            cordNodo=cordNodo+","+sc.next();
            numeroPunto++;
            //Si la cadena armada es igual a el punto del nodo actual
            if(cordNodo.equals(cadena))
            {
                int idx=(numeroPunto-1)*2;
                char acierto=nodo.getItemEnPadre().charAt(idx);
                int nItemAcierto=Integer.parseInt(acierto+"");
                if(nItemAcierto==nodo.getN_items())return true;
            }
        }

        return false;
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
