/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package juegoPrecipicio;

import java.util.ArrayList;

/**
 *
 * @author Nathaly
 */

public class Precipicio {

    public int cont=1;

    public Precipicio()
    {
       
    }

    public void crearNodos(Nodo nod)
    {
        ArrayList <Nodo> hijos= crearHijos(nod);
        if(hijos!=null)
        {
            for(int x=0; x<hijos.size(); x++)
            {
                Nodo tmp= hijos.get(x);
                cont++;
                crearNodos(tmp);
            }
        }
    }

    public ArrayList crearHijos(Nodo nodoIn)
    {
        if(!(nodoIn.isJugadorCedio()&&nodoIn.isMaquinaCedio()))
        {
            ArrayList <Nodo> tmpHijos= new ArrayList();
            //Lanzamiento dado jugador
            if(nodoIn.getTipo().equals("aleMin"))
            {
                int escalon=nodoIn.getEscalonJugador();
                for(int x=1; x<4; x++)
                {
                    if(escalon+x<10)
                    {
                        Nodo n = new Nodo();
                        n.setTipo("max");
                        n.setEscalonJugador(nodoIn.getEscalonJugador()+x);
                        n.setEscalonMaquina(nodoIn.getEscalonMaquina());
                        n.setJugadorCedio(nodoIn.isJugadorCedio());
                        n.setMaquinaCedio(nodoIn.isMaquinaCedio());
                        tmpHijos.add(n);
                        //System.out.println("Creado nodo: "+n.getTipo()+" escalon maquina"+n.getEscalonMaquina()+" escalon jugador"+n.getEscalonJugador());
                    }
                }
            }

            else if(nodoIn.getTipo().equals("aleMax"))
            {
                int escalon=nodoIn.getEscalonMaquina();
                for(int x=1; x<4; x++)
                {
                    if(escalon+x<11)
                    {
                        Nodo n = new Nodo();
                        n.setTipo("min");
                        n.setEscalonJugador(nodoIn.getEscalonJugador());
                        n.setEscalonMaquina(nodoIn.getEscalonMaquina()+x);
                        n.setJugadorCedio(nodoIn.isJugadorCedio());
                        n.setMaquinaCedio(nodoIn.isMaquinaCedio());
                        tmpHijos.add(n);
                        //System.out.println("Creado nodo: "+n.getTipo()+" escalon maquina"+n.getEscalonMaquina()+" escalon jugador"+n.getEscalonJugador());
                    }
                }
            }

            else if(nodoIn.getTipo().equals("max"))
            {
                if(nodoIn.getEscalonMaquina()<8)
                {
                    Nodo aleat = new Nodo();
                    aleat.setTipo("aleMax");
                    aleat.setEscalonJugador(nodoIn.getEscalonJugador());
                    aleat.setEscalonMaquina(nodoIn.getEscalonMaquina());
                    aleat.setJugadorCedio(nodoIn.isJugadorCedio());
                    aleat.setMaquinaCedio(nodoIn.isMaquinaCedio());
                    tmpHijos.add(aleat);
                    //System.out.println("Creado nodo aleMax: "+aleat.getTipo()+" escalon maquina"+aleat.getEscalonMaquina()+" escalon jugador"+aleat.getEscalonJugador());
                
                
                    if(!nodoIn.isMaquinaCedio())
                    {
                        Nodo ceder= new Nodo();
                        ceder.setTipo("min");
                        ceder.setEscalonJugador(nodoIn.getEscalonJugador());
                        ceder.setEscalonMaquina(nodoIn.getEscalonMaquina());
                        ceder.setJugadorCedio(nodoIn.isJugadorCedio());
                        ceder.setMaquinaCedio(true);
                        tmpHijos.add(ceder);
                        //System.out.println("Creado nodo ceder: "+ceder.getTipo()+" escalon maquina"+ceder.getEscalonMaquina()+" escalon jugador"+ceder.getEscalonJugador());
                    }
                }
                
            }

            else if(nodoIn.getTipo().equals("min"))
            {
                if(nodoIn.getEscalonJugador()<8)
                {
                    Nodo aleat = new Nodo();
                    aleat.setTipo("aleMin");
                    aleat.setEscalonJugador(nodoIn.getEscalonJugador());
                    aleat.setEscalonMaquina(nodoIn.getEscalonMaquina());
                    aleat.setJugadorCedio(nodoIn.isJugadorCedio());
                    aleat.setMaquinaCedio(nodoIn.isMaquinaCedio());
                    tmpHijos.add(aleat);
                    //System.out.println("Creado nodo: "+aleat.getTipo()+" escalon maquina"+aleat.getEscalonMaquina()+" escalon jugador"+aleat.getEscalonJugador());
                
                
                    if(!nodoIn.isJugadorCedio())
                    {
                        Nodo ceder= new Nodo();
                        ceder.setTipo("max");
                        ceder.setEscalonJugador(nodoIn.getEscalonJugador());
                        ceder.setEscalonMaquina(nodoIn.getEscalonMaquina());
                        ceder.setJugadorCedio(true);
                        ceder.setMaquinaCedio(nodoIn.isMaquinaCedio());
                        tmpHijos.add(ceder);
                       //System.out.println("Creado nodo: "+ceder.getTipo()+" escalon maquina"+ceder.getEscalonMaquina()+" escalon jugador"+ceder.getEscalonJugador());

                    }
                }
                
            }

            return tmpHijos;
        }
        return null;
    }

    public void minimax(Nodo nod)
    {
        
    }
}
