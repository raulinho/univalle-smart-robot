/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tablero;

import java.util.Random;
import snakesandladders.Jugador;

/**
 *
 * @author Nathaly
 */
public class Tablero {

    private Casilla[][] matrizTab;
    private boolean jugar;
    private Jugador j1;
    private Jugador j2;

    public Tablero()
    {
        matrizTab=new Casilla [8][8];
        int pos=1;
        for (int x=0; x<8; x++)
        {
            for (int y=0; y<8; y++)
            {
                Casilla cas= new Casilla(pos);
                if(pos==12)
                {
                    cas.setTipo("Escalera");
                    cas.setEscalera(28);
                }
                else if(pos==17)
                {
                    cas.setTipo("Escalera");
                    cas.setEscalera(33);
                }
                else if(pos==27)
                {
                    cas.setTipo("Serpiente");
                    cas.setSerpiente(10);
                }
                else if(pos==31)
                {
                    cas.setTipo("Serpiente");
                    cas.setSerpiente(4);
                }
                else if(pos==35)
                {
                    cas.setTipo("Escalera");
                    cas.setSerpiente(29);
                }
                else if(pos==41)
                {
                    cas.setTipo("Escalera");
                    cas.setEscalera(57);
                }
                else if(pos==55)
                {
                    cas.setTipo("Serpiente");
                    cas.setSerpiente(44);
                }
                else if(pos==61)
                {
                    cas.setTipo("Serpiente");
                    cas.setSerpiente(53);
                }
                else if(pos==63)
                {
                    cas.setTipo("Serpiente");
                    cas.setSerpiente(48);
                }
                matrizTab[x][y]=cas;
                pos++;
            }
        }
        jugar=true;
        j1=new Jugador();
        j2=new Jugador();
        j1.setNombre("jugador 1");
        j2.setNombre("maquina");
    }

    public void moverJugador(Jugador jug)
    {
        int mov=lanzarDado();
        int pos=jug.getPosicionActual();
        int posN=pos+mov;
        if(posN<64)
        {
            jug.setPosicionActual(posN);
            System.out.println("Posicion de "+jug.getNombre()+" "+posN);
        }
        else if(posN>64)
        {
            posN=pos-mov;
            jug.setPosicionActual(posN);
            System.out.println("Posicion de "+jug.getNombre()+" "+posN);
        }
        else
        {
            jug.setPosicionActual(posN);
            jugar = false;
            System.out.println("Posicion de "+jug.getNombre()+" "+posN);
            System.out.println("Fin del juego");
        }
    }

    public boolean evaluarPosicion()
    {
        if(j1.getPosicionActual()==j2.getPosicionActual())
        {
            System.out.println("juego de decision");
            return true;
        }
        return false;
    }

    public void jugar()
    {
        int cuenta=1;
        while (jugar)
        {
            System.out.println("Ronda "+cuenta);
            moverJugador(j1);
            evaluarPosicion();
            moverJugador(j2);
            evaluarPosicion();
            cuenta++;
        }
    }

    public int lanzarDado()
    {
        Random ran= new Random();
        int num;
        num=ran.nextInt(6)+1;
        return num;
    }
}
