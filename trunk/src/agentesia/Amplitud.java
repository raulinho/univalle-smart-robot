/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agentesia;

/**
 *
 * @author jorgeorm
 */
public class Amplitud extends Busqueda {
    NodoEstado nodoraiz;

    public Amplitud(NodoEstado raiz, int cordXf,int cordYf )
    {
        super();
        cordXSalida=cordXf;
        cordYSalida=cordYf;
        nodoraiz=raiz;
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

    public boolean esMeta(NodoEstado nodo)
    {
        if(nodo.getX()==cordXSalida && nodo.getY()==cordYSalida && nodo.getN_items()<=0)
        {
            return true;
        }
        else return false;
    }
}
