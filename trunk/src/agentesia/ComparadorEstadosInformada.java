/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agentesia;
import java.util.Comparator;
/**
 *
 * @author Administrator
 */
public class ComparadorEstadosInformada implements Comparator<NodoEstado> {

     public int compare(NodoEstado o1, NodoEstado o2) {
        //throw new UnsupportedOperationException("Not supported yet.");

        if (o1.getCosto_est()==o2.getCosto_est()) return 0;
        else if(o1.getCosto_est() > o2.getCosto_est()) return 1;
        else if(o1.getCosto_est() < o2.getCosto_est()) return -1;

        return 3;
    }
}
