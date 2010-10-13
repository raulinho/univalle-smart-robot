/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agentesia;

import java.util.Comparator;

/**
 *
 * @author Nathaly
 */

//La clase es utilizada por la cola de prioridad implementada en el algoritmo de costo
public class ComparadorEstados implements Comparator<NodoEstado>{

    //Con la implementación de este metodo se decide el orden de la cola de prioridad
    public int compare(NodoEstado o1, NodoEstado o2) {

        if (o1.getCosto()==o2.getCosto()) return 0;
        else if(o1.getCosto() > o2.getCosto()) return 1;
        else if(o1.getCosto() < o2.getCosto()) return -1;

        return 3;
    }

    public int compare(NodoEstado o1, NodoEstado o2, int c)
    {
        return 0;
    }

}
