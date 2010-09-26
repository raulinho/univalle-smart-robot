/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agentesia;

import java.util.ArrayList;

/**
 * Acá se planean poner los diferentes algoritmos para la solución del problema
 * @author jorgeorm
 */
public class AgentesIA {
    private int [][] escenario;
    private Punto pi,pf;
    private int nItems,nNaves;

    public int[][] getEscenario() {
        return escenario;
    }

    public void setEscenario(int[][] escenario) {
        this.escenario = escenario;

        for(int idy=0;idy<10;idy++)
        {
            for(int idx=0;idx<10;idx++)
            {
                int sitio=escenario[idx][idy];
                switch(sitio)
                {
                //obstaculo
                case 1:
                break;
                //Inicio
                case 2:
                    pi=new Punto(idx, idy);
                break;
                //Salida
                case 3:
                    pf=new Punto(idx, idy);
                break;
                //Nave1
                case 4:
                    nNaves++;
                break;
                //Nave2
                case 5:
                    nNaves++;
                break;
                //Item
                case 6:
                    nItems++;
                break;
                //CampoElectromagnetico
                case 7:
                break;
                }
            }
        }
        
        NodoEstado respuesta=amplitud();
        if(respuesta.equals(null)) System.out.println("No se encontró respuesta con aplitud");
        else
        {
            System.out.println("Se encontró respuesta con amplitud");
            System.out.println("Ruta: "+respuesta.getRuta()+", "+respuesta.getLugarMatriz().toString());
            System.out.println("Operadores: "+respuesta.getOperador());
            System.out.println("Costo: "+respuesta.getCosto());
        }
    }

    public NodoEstado amplitud()
    {
        ArrayList <NodoEstado> cola = new ArrayList<NodoEstado>();
        int [][] memoriaMapa =new int [10][10];
        for (int idy=0;idy<10;idy++)
        {
            for(int idx=0;idx<10;idx++)
            {
                memoriaMapa[idx][idy]=0;
            }
        }
        NodoEstado raiz= new NodoEstado("", 0, "", pi, nItems, nNaves, 0,memoriaMapa);
        cola.add(raiz);
        
        while(!(cola.isEmpty()))
        {
            NodoEstado nodoActual=cola.get(0);
            if(esMetaAmplitud(nodoActual))
            {
                return nodoActual;
            }
            else
            {
                //Expando el nodo por amplitud
                cola.remove(0);
                cola.addAll(aplicarOperadores(nodoActual));
            }
        }
        return null;
    }

    public NodoEstado crearHijo(NodoEstado padre, String operador, Punto puntoHijo)
    {
        //Ruta que guardara la ruta del padre y al padre
        String ruta;
        //Posición en x del hijo
        int x=puntoHijo.getX();
        //Posición en y del hijo
        int yN=puntoHijo.getY();
        //Costo guardará el costo de avanzar al hijo
        double costo;
        //Nodo estado que se va a retornar
        NodoEstado hijo=null;

        //Si la cadenas estan vacias no usar comas
        if(padre.getOperador().equals("")) operador=padre.getOperador()+operador;
        else operador=padre.getOperador()+", "+operador;
        if(padre.getRuta().equals("")) ruta=padre.getLugarMatriz().toString();
        else ruta=padre.getRuta()+", "+padre.getLugarMatriz().toString();

        //Memoria para saber que lugares ya visite Solo la utilizo para saber si ya recogí un item.
        int [][] memoria = padre.getMemoria();
        memoria[padre.getLugarMatriz().getX()][padre.getLugarMatriz().getY()]=1;

        //Si en el mapa es libre
        if(escenario[x][yN]==0 || escenario[x][yN]==3 || memoria[x][yN]==1)
        {
            //Si va en nave
            if(padre.getNave()>0)
            {
                costo=padre.getCosto()+0.5;
                hijo= new NodoEstado(ruta,costo,operador,puntoHijo,padre.getN_items(), padre.getN_naves(), padre.getNave()-1,memoria);
            }
            else
            {
                costo=padre.getCosto()+1;
                hijo= new NodoEstado(ruta,costo,operador,puntoHijo,padre.getN_items(), padre.getN_naves(), 0,memoria);
            }
        }
        //Si en el mapa es nave
        else if(escenario[x][yN]==4 || escenario[x][yN]==5)
        {
            //Si va en nave
            if(padre.getNave()>0)
            {
                costo=padre.getCosto()+0.5;
                //Lleno combustible
                hijo= new NodoEstado(ruta,costo,operador,puntoHijo,padre.getN_items(), padre.getN_naves()-1, padre.getNave()-1+10,memoria);
            }
            else
            {
                costo=padre.getCosto()+1;
                //Lleno combustible
                hijo= new NodoEstado(ruta,costo,operador,puntoHijo,padre.getN_items(), padre.getN_naves()-1, 10,memoria);
            }
        }
        //Si en el mapa es item
        else if(escenario[x][yN]==6)
        {
            //Si va en nave
            if(padre.getNave()>0)
            {
                costo=padre.getCosto()+0.5;
                hijo= new NodoEstado(ruta,costo,operador,puntoHijo,padre.getN_items()-1, padre.getN_naves(), padre.getNave()-1, memoria);
            }
            else
            {
                costo=padre.getCosto()+1;
                hijo= new NodoEstado(ruta,costo,operador,puntoHijo,padre.getN_items()-1, padre.getN_naves(), 0, memoria);
            }
            //escenario[x][yN]=0;
        }
        //Si en el mapa es campo
        else if(escenario[x][yN]==7)
        {
            //Si va en nave
            if(padre.getNave()>0)
            {
                costo=padre.getCosto()+0.5+6;
                hijo= new NodoEstado(ruta,costo,operador,puntoHijo,padre.getN_items(), padre.getN_naves(), padre.getNave()-1, memoria);
            }
            else
            {
                costo=padre.getCosto()+1+6;
                hijo= new NodoEstado(ruta,costo,operador,puntoHijo,padre.getN_items(), padre.getN_naves(), 0, memoria);
            }
        }

        return hijo;
    }

    public ArrayList<NodoEstado> aplicarOperadores(NodoEstado nodo)
    {
        Punto punto=nodo.getLugarMatriz();
        int x=punto.getX();
        int y=punto.getY();
        ArrayList <NodoEstado> hijos =new ArrayList<NodoEstado>();
        double costo=0;
        if(y>0)
        {
            int yN=y-1;
            String operador="↑";
            NodoEstado hijo= crearHijo(nodo, operador, new Punto(x, yN));
            if (hijo!=null)hijos.add(hijo);
        }
        //TODO comparación del contenido del escenario para determinar los hijos a insertar en la cola con los operadores faltantes.
        if(x<9)
        {
            int xN=x+1;
            String operador="→";
            NodoEstado hijo= crearHijo(nodo, operador, new Punto(xN, y));
            if (hijo!=null)hijos.add(hijo);
        }
        if(y<9)
        {
            int yN=y+1;
            String operador="↓";
            NodoEstado hijo= crearHijo(nodo, operador, new Punto(x, yN));
            if (hijo!=null)hijos.add(hijo);
        }
        if(x>0)
        {
            int xN=x-1;
            String operador="←";
            NodoEstado hijo= crearHijo(nodo, operador, new Punto(xN, y));
            if (hijo!=null)hijos.add(hijo);
        }
        
        return hijos;
    }

    public boolean esMetaAmplitud(NodoEstado nodo)
    {
        if(nodo.getLugarMatriz().getX()==pf.getX() && pf.getY()==nodo.getLugarMatriz().getY() && nodo.getN_items()<=0)
        {
            return true;
        }
        else return false;
    }

    public void imprimirEscenario()
    {
        String siso="";

        for(int idy=0;idy<10;idy++)
        {
            for(int idx=0;idx<10;idx++)
            {
                siso=escenario[idx][idy]+"";

                if(idy<9)siso=siso+",";
                System.out.print(siso);
            }
            System.out.println();
        }
    }


    public AgentesIA()
    {
        escenario=new int [10][10];
  //      vItems = new Vector<Punto>();
  //      vNaves = new Vector<Punto>();
    }

}
