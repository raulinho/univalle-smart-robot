/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package minimax;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author jorgeorm
 */
public class Territorio {
  private int tamanoX,tamanoY, jughx,jughy,jugmx,jugmy;
  private char [][]mapa;
  private char starter;
  private String ganador="";
  String scenario="";

  public Territorio(char jugador)
  {
    this.tamanoX=5;
    this.tamanoY=5;
    initJuego(jugador);
  }
  public Territorio(int tamanoX, int tamanoY, char jugador)
  {
    this.tamanoX=tamanoX;
    this.tamanoY=tamanoY;
    initJuego(jugador);
  }
  //INicializa el juego
  public void initJuego(char jugador)
  {
    //seteo variables
    starter=jugador;
    char tmpjugador=jugador;
    this.mapa=new char [this.tamanoX][this.tamanoY];

    for(int idx=0;idx<tamanoX;idx++)
    {
        for(int idy=0;idy<tamanoY;idy++)
        {
            this.mapa[idx][idy]=' ';
        }
    }
    //acomodo los jugadores
    if(starter=='h')
    {
      jughx=0;
      jughy=0;
      jugmx=tamanoX-1;
      jugmy=tamanoY-1;
      mapa[jughx][jughy]='h';
      mapa[jugmx][jugmy]='m';
    }else{
      jugmx=0;
      jugmy=0;
      jughx=tamanoX-1;
      jughy=tamanoY-1;
      mapa[jughx][jughy]='h';
      mapa[jugmx][jugmy]='m';
    }

    if(starter=='m')jugarMaquina();

    imprimirEscenario();

    //Empiezo a jugar
    /*while(ganador.equals(""))
    {
      if(tmpjugador=='h') jugarHumano();
      else jugarMaquina();

      imprimirEscenario();
      if(tmpjugador=='h') tmpjugador='m';
      else tmpjugador='h';
      determinarGanador();
    }*/
  }
  public boolean  determinarGanador()
  {
      boolean gano=false;
      //InmovilizaH
      if(puedoMover('m')&&!puedoMover('h'))
      {
        ganador="robot";
        gano= true;
      }//InmovilizaM
      else if(puedoMover('h')&&!puedoMover('m'))
      {
        ganador="humano";
        gano= true;
      }//ambos Inmoviles
      else if(!puedoMover('h')&&!puedoMover('m'))
      {
        int hs=contarH();
        int ms=contarM();
        if(hs<ms) ganador="robot";
        else if(ms<hs) ganador="humano";
        else ganador="empate";
        gano= true;
      }
      return gano;
  }

  public int contarH()
  {
      int res=0;
      for(int idy=0;idy<tamanoY;idy++)
      {
        for(int idx=0;idy<tamanoY;idy++)
        {
            if(mapa[idx][idy]=='h')res++;
        }
      }
      return res;
  }
  public int contarM()
  {
      int res=0;
      for(int idy=0;idy<tamanoY;idy++)
      {
        for(int idx=0;idy<tamanoY;idy++)
        {
            if(mapa[idx][idy]=='m')res++;
        }
      }
      return res;
  }
  public String getScenario()
    {
    return this.scenario;
  }

  public void imprimirEscenario()
  {
    scenario="";
    for(int idy=0;idy<tamanoY;idy++)
    {
      for(int idx=0;idx<tamanoX;idx++)
      {
        String tmp=mapa[idx][idy]+" | ";
	System.out.print(tmp);
        if(tmp.equals(' '+" | "))tmp=' '+"   | ";
        scenario=scenario+tmp;
      }
      scenario=scenario+"\n";
      System.out.println(" ");
    }
  }

  public void imprimirMovidas()
  {
    System.out.println("Ingrese movimiento");
    System.out.println("DiagSupIzq - q");
    System.out.println("Arriba - w");
    System.out.println("DiagSupDer - e");
    System.out.println("Derecha - d");
    System.out.println("DiagInfDer - c");
    System.out.println("Aabajo -x");
    System.out.println("DiagInfIzq - z");
    System.out.println("Izquierda - a");
  }

  public boolean puedoMover(char jugador)
  {
    int jughx,jughy;
    char mov=' ';
    boolean puedo=false;
    char [] operadores={'q','w','e','d','c','x','z','a'};
    for(int i=0;i<operadores.length;i++)
    {//Si es Min o Max
        if(jugador=='m')
        {
          jughx=jugmx;
          jughy=jugmy;
        }
        else{
          jughx=this.jughx;
          jughy=this.jughy;
        }

        mov=operadores[i];
        switch (mov)
        {
          case 'q':
            jughx--;
            jughy--;
          break;
          case 'w':
            jughy--;
          break;
          case 'e':
            jughx++;
            jughy--;
          break;
          case 'd':
            jughx++;
          break;
          case 'c':
            jughx++;
            jughy++;
          break;
          case 'x':
            jughy++;
          break;
          case 'z':
            jughx--;
            jughy++;
          break;
          case 'a':
            jughx--;
          break;
        }
        //Jugador hubicado en coordenadas validas
        if(jughx<tamanoX && jughx>=0 && jughy<tamanoY && jughy >=0)
        {
          if(mapa[jughx][jughy]==' ')
          {
            puedo=true;
            return puedo;
          }
        }

    }
    
    return puedo;
  }

  public void jugarHumano()
  {
      imprimirMovidas();

      boolean stop=false;
      int jughx=0;
      int jughy=0;

      Scanner sc=new Scanner(System.in);

      while(!stop)
      {
	char mov=sc.nextLine().charAt(0);
	jughx=this.jughx;
	jughy=this.jughy;


	switch (mov)
	{
	  case 'q':
	    jughx--;
	    jughy--;
	  break;
	  case 'w':
	    jughy--;
	  break;
	  case 'e':
	    jughx++;
	    jughy--;
	  break;
	  case 'd':
	    jughx++;
	  break;
	  case 'c':
	    jughx++;
	    jughy++;
	  break;
	  case 'x':
	    jughy++;
	  break;
	  case 'z':
	    jughx--;
	    jughy++;
	  break;
	  case 'a':
	    jughx--;
	  break;
	}
	//Jugador hubicado en coordenadas validas
	if(jughx<tamanoX && jughx>=0 && jughy<tamanoY && jughy >=0)
	{
          if(mapa[jughx][jughy]==' '){
              stop=true;
          }
	}
      }
      this.jughx=jughx;
      this.jughy=jughy;
      mapa[jughx][jughy]='h';
        

  }
  public String jugarHumano(char mov)
  {
      if(!ganador.equals(""))
      {
          switch (mov)
      {
	  case 'q':
	    jughx--;
	    jughy--;
	  break;
	  case 'w':
	    jughy--;
	  break;
	  case 'e':
	    jughx++;
	    jughy--;
	  break;
	  case 'd':
	    jughx++;
	  break;
	  case 'c':
	    jughx++;
	    jughy++;
	  break;
	  case 'x':
	    jughy++;
	  break;
	  case 'z':
	    jughx--;
	    jughy++;
	  break;
	  case 'a':
	    jughx--;
	  break;
      }
	//Jugador hubicado en coordenadas validas
	if(jughx<tamanoX && jughx>=0 && jughy<tamanoY && jughy >=0)
	{
          if(mapa[jughx][jughy]==' '){
              this.jughx=jughx;
              this.jughy=jughy;
              mapa[jughx][jughy]='h';
              imprimirEscenario();
              if(determinarGanador())return this.ganador;
              jugarMaquina();
              imprimirEscenario();
              if(determinarGanador())return this.ganador;
              
          }
	}

      }else return this.ganador;
      return "";
  }
  public void reajustarJugador()
  {
    if(jughx<0){
	jughx++;
      }else if(jughx>=tamanoX)
      {
	jughx--;
      }
      if(jughy<0){
	jughy++;
      }else if(jughy>=tamanoY)
      {
	jughy--;
      }
  }
  public void jugarMaquina()
  {
      NodoEstado desicion=decidirMaquina();
      char mov= desicion.getDesicion();
      int jughx=this.jugmx;
      int jughy=this.jugmy;
      switch (mov)
	{
	  case 'q':
	    jughx--;
	    jughy--;
	  break;
	  case 'w':
	    jughy--;
	  break;
	  case 'e':
	    jughx++;
	    jughy--;
	  break;
	  case 'd':
	    jughx++;
	  break;
	  case 'c':
	    jughx++;
	    jughy++;
	  break;
	  case 'x':
	    jughy++;
	  break;
	  case 'z':
	    jughx--;
	    jughy++;
	  break;
	  case 'a':
	    jughx--;
	  break;
	}
      this.jugmx=jughx;
      this.jugmy=jughy;
      mapa[jugmx][jugmy]='m';
  }
  //Sube la utilidad al nodo padre y elimina el nodo hijo del arrayList
  public void subirUtilidad(NodoEstado padre,NodoEstado hijo)
  {

    double utilPa,utilHj;
    utilPa=padre.getUtilidad();
    utilHj=hijo.getUtilidad();
    System.out.println("Padre: "+padre.getTipo()+"="+padre.getUtilidad()+", Hijo: "+hijo.getTipo()+"="+hijo.getUtilidad());

    //Subo utilidad deacuerdo al nodo
    if(padre.getTipo().equals("Max"))
    {
        if(utilHj>utilPa){
            padre.setUtilidad(utilHj);
            padre.setDesicion(hijo.getOperador());
        }
    }else
    {
        if(utilPa>utilHj){
            padre.setUtilidad(utilHj);
            padre.setDesicion(hijo.getOperador());
        }
    }
    System.out.println("Padre: "+padre.getTipo()+"="+padre.getUtilidad());

   
  }
  public NodoEstado decidirMaquina()
  {
    NodoEstado raiz=new NodoEstado(mapa.clone(), "Max", ' ',jugmx,jugmy,jughx,jughy,tamanoX,tamanoY);
    ArrayList<NodoEstado> cola=new ArrayList<NodoEstado>();
    cola.add(raiz);
    ArrayList<NodoEstado> padres=new ArrayList<NodoEstado>();
    //System.out.print("/");

    while(!cola.isEmpty())
    {
        NodoEstado nodo=cola.remove(0);
        //System.out.print(nodo.getOperador()+" - Nodo: "+nodo.getTipo()+", ");
        if(nodo.getAltura()<4)
        {
            //System.out.println("Nodo de altura < 2");
            ArrayList <NodoEstado> hijos=nodo.expandir();
            if(!padres.isEmpty())
            {
             //   System.out.println("\t Padres tiene algo");
                //si el nodo que voy a meter a padres tiene la misma altura que el nodo actual en la cabeza
                //de la pila de padres
                if(padres.get(0).getAltura()==nodo.getAltura())
                {
                    System.out.println("Es de la misma altura");
               //     System.out.println("\t\t nodo tienen la misma altura que el padre");
                    //saco la cabeza de la pila de padres y obtengo a el padre de el nodo que saqué
                    //lugo subo utilidad de la cabeza al padre
                    NodoEstado tmp= padres.remove(0);
                    NodoEstado abuelo=padres.get(0);
                    System.out.println("Abuelo");
                    subirUtilidad(abuelo,tmp);
                    padres.set(0, abuelo);
                    //si no doy hijos no me meto a la cola sino que a mí padre le doy mi utilidad
                    if(hijos==null)
                    {
                 //       System.out.println("\t\t\t no da hijos");
                        NodoEstado padre=padres.get(0);
                        subirUtilidad(padre, nodo);
                        padres.set(0, padre);
                    }else 
                    {
                  //     System.out.println("\t\t\t si da hijos");
                        padres.add(0,nodo);
                    }
                }else if(padres.get(0).getAltura()<nodo.getAltura())
                {
                    //System.out.println("\t\t nodo de mayor altura que padre");
                    if(hijos==null)
                    {
                        //System.out.println("\t\t\t no da hijos");
                        NodoEstado padre=padres.get(0);
                        subirUtilidad(padre, nodo);
                        padres.set(0, padre);
                    }else
                    {
                        //System.out.println("\t\t\t si da hijos");
                        
                        padres.add(0,nodo);
                    }
                }
            } else
            {
                //System.out.println("\tPadres vacio");
                padres.add(nodo);
                //Sino hay hijos no los añado a la pila
            }

            if(hijos!=null)
            {
                hijos.addAll(cola);
                cola=(ArrayList<NodoEstado>)hijos.clone();
            }


        }else
        {

            NodoEstado padre=padres.get(0);
            if(!nodo.esMeta())nodo.setHeuristica();
            subirUtilidad(padre, nodo);
            padres.set(0,padre);
        }
        
    }
    //System.out.println("");
    //System.out.println(padres.size());
    //subo las utilidades de los hijos que quedaron pendientes son los ultimos :D
    System.out.println ("Imprimo las utiliades de los padres raiz - hijos-----------------------");
    int x=padres.size()-1;
    for(int i=0;i<padres.size();i++)
    {
        System.out.print(padres.get(x-i).getTipo()+"="+padres.get(x-i).getUtilidad()+", ");
    }
    while(padres.size()>1)
    {
        
        System.out.println();
        NodoEstado hijo=padres.remove(0);
        NodoEstado padre=padres.get(0);
        subirUtilidad(padre, hijo);
        padres.set(0,padre);

    }
    /*for(int i=0;i<padres.size();i++)
    {
        System.out.print(padres.get(i).getTipo()+" "+padres.get(i).getOperador()+" ");
        System.out.print("Altura: "+padres.get(i).getAltura()+",");
    }*/
    System.out.println();
    return padres.get(0);
  }

}