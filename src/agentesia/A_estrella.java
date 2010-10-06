/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agentesia;

/**
 *
 * @author Administrator
 */
public class A_estrella extends Busqueda{

    NodoEstado nodoRaiz;

    public A_estrella(NodoEstado raiz, int cordX, int cordY, int escen[][])
    {
        nodoRaiz=raiz;
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
