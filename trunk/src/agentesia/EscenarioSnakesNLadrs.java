/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agentesia;

import java.util.ArrayList;

/**
 *
 * @author jorgeorm
 */
class EscenarioSnakesNLadrs {
    int[] atajos=null;
    int humano=1;
    int maquina=1;
    int[][]mapa=null;

    public EscenarioSnakesNLadrs(int[] atajos,int [][]mapa)
    {
        this.atajos=new int[atajos.length];
        //copio snakes
        System.arraycopy(atajos, 0, this.atajos, 0, atajos.length);
        //copio mapa
        this.mapa=new int [mapa.length][mapa[0].length];

        for(int i=0;i<mapa.length;i++)
        {
            System.arraycopy(mapa[i],0,this.mapa[i],0,mapa[i].length);
        }
    }

    public boolean desplazar(String jugador,int avance)
    {
        boolean encuentro=false;

        if (jugador.equals("humano"))
        {
            humano=humano+avance;
        }else
        {
            maquina=maquina+avance;
        }

        if(humano==maquina) encuentro=true;

        return  encuentro;
    }
    public ArrayList getPos(int pos)
    {
        ArrayList obj=new ArrayList();

        GRANDE:for(int idy=0;idy<mapa.length;idy++)
        {
            for(int idx=0;idx<mapa[idy].length;idx++)
            {
                if(mapa[idx][idy]==pos)
                {
                    obj.add(idx);
                    obj.add(idy);
                    break GRANDE;
                }
            }
        }

        return obj;
    }

    public boolean esAtajo(int casilla)
    {
        if(atajos[casilla-1]!=0) return true;
        else return false;
    }

}
