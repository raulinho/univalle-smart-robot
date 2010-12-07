/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package minimax;

import java.util.AbstractList;
import java.util.ArrayList;

/**
 *
 * @author jorgeorm
 */
public class NodoEstado {
  private int posX,posY,prof,pos2X,pos2Y;
  //Tamano mapa
  private int tamanoX,tamanoY;
  private char [][]mapa;
  private double utilidad;
  private String tipo;
  private char operador,desicion;
  private int altura;

  public NodoEstado(char[][] escenario, String tipo, char op,int posX,int posY,int pos2I, int pos2J,int tamanoX,int tamanoY)
  {
    this.tamanoX=tamanoX;
    this.tamanoY=tamanoY;
    desicion=' ';
    operador=op;
    this.tipo=tipo;
    this.posX=posX;
    this.posY=posY;
    this.pos2X=pos2I;
    this.pos2Y=pos2J;
    prof=0;
    mapa=new char[tamanoX][tamanoY];
    for(int idx=0;idx<tamanoX;idx++)
    {
        for(int idy=0;idy<tamanoY;idy++)
        {
            mapa[idx][idy]=escenario[idx][idy];
        }
    }
    if(tipo.equals("Max"))utilidad=Double.NEGATIVE_INFINITY;
    else utilidad=Double.POSITIVE_INFINITY;
    altura=0;
  }

  public void setAltura(int altura)
  {
      this.altura=altura;
  }

  public boolean esMeta()
  {
    boolean respuesta=false;
    
    //InmovilizaH
      if(puedoMover('m')&&!puedoMover('h'))
      {
          utilidad=1000000;
          respuesta=true;
        
      }//InmovilizaM
      else if(puedoMover('h')&&!puedoMover('m'))
      {
          utilidad=-1000000;
          respuesta=true;
      }//ambos Inmoviles
      else if(!puedoMover('h')&&!puedoMover('m'))
      {
        int hs=contarH();
        int ms=contarM();
        if(ms>hs) utilidad=1000000;
        else if(ms<hs) utilidad=-1000000;
        else utilidad=0;
        respuesta=true;
      }

    return respuesta;
  }
  //Conteo para el empate
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
  }//Conteo para el empate
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

  //Verificar si es posible mover
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
          jughx=posX;
          jughy=posY;
        }
        else{
          jughx=this.pos2X;
          jughy=this.pos2Y;
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
        if((jughx<tamanoX && jughx>=0) && (jughy<tamanoY && jughy >=0))
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

    public char getDesicion() {
        return desicion;
    }

    public void setDesicion(char desicion) {
        this.desicion = desicion;
    }

    public char[][] getMapa() {
        return mapa;
    }

    public void setMapa(char[][] mapa) {
        this.mapa = mapa;
    }

    public char getOperador() {
        return operador;
    }

    public void setOperador(char operador) {
        this.operador = operador;
    }

    public int getProf() {
        return prof;
    }

    public double getUtilidad() {
        return utilidad;
    }


    public void setProf(int prof) {
        this.prof = prof;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

  public void setPos(int x, int y)
  {
    this.posX=x;
    this.posY=y;
  }

  public void setPos2(int x, int y)
  {
    this.pos2X=x;
    this.pos2Y=y;
  }

  public AbstractList getPos()
  {
    ArrayList punto=new ArrayList(2);
    punto.add(posX);
    punto.add(posY);
    return punto;
  }
  public AbstractList getPos2()
  {
    ArrayList punto=new ArrayList(2);
    punto.add(pos2X);
    punto.add(pos2Y);
    return punto;
  }

  public void setUtilidad(double utilidad)
  {
    this.utilidad=utilidad;
  }

  public void setHeuristica()
  {
    int jughx,jughy;
    char mov=' ';
    char [] operadores={'q','w','e','d','c','x','z','a'};
    ArrayList posiciones=(ArrayList)this.getPos();
    posiciones.addAll(this.getPos2());
    double utilidad=0;
    boolean max=false;

    while(!posiciones.isEmpty())
    {
        jughx=(Integer)posiciones.remove(0);
        jughy=(Integer)posiciones.remove(0);
        for(int i=0;i<operadores.length;i++)
        {//Si es Min o Max
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
            if((jughx<tamanoX && jughx>=0) && (jughy<tamanoY && jughy >=0))
            {
              if(mapa[jughx][jughy]==' ')
              {
                if(max)utilidad=utilidad-getUtilidadPosicion(jughx, jughy);
                else utilidad=utilidad+getUtilidadPosicion(jughx, jughy);
              }
            }

        }
        max=!max;
    }


    this.utilidad=utilidad;
    System.out.println("Utilidad: "+utilidad);

  }

  public int getUtilidadPosicion(int x, int y)
  {
      int valor=0;
      boolean esquina=(x==0&&y==0)||
                      (x==0&&y==4)||
                      (x==4&&y==0)||
                      (x==4&&y==4);
      if(esquina)
      {
        valor=1;
      }else if(x<4&&x>0&&(y==1||y==3))
      {
        valor=8;
      }else if(x<4&&x>0&&(y==2))
      {
        valor=10;
      }else{
        valor=4;
      }
      return valor;
  }

  public int manhattan()
  {
    int valor= Math.abs(posX-pos2X)+Math.abs(posY-pos2Y);
    return valor;
  }

  public NodoEstado clone()
  {
    char[][] memoria=new char[tamanoX][tamanoY];
    for(int idx=0;idx<tamanoX;idx++)
    {
        for(int idy=0;idy<tamanoY;idy++)
        {
            memoria[idx][idy]=mapa[idx][idy];
        }
    }
    NodoEstado nodo= new NodoEstado(memoria, this.tipo, this.operador ,this.posX,this.posY,this.pos2X,this.pos2Y,this.tamanoX,this.tamanoY);
    return nodo;
  }

  public int getAltura()
  {
      return this.altura;
  }

  public ArrayList<NodoEstado> expandir()
  {
      ArrayList<NodoEstado> hijos=null;

      if(!esMeta())
      {
          //Expando
          hijos=new ArrayList<NodoEstado>();
          //Variables para movimientos
          char[]operadores={'q','w','e','d','c','x','z','a'};
          char mov=' ';

          //Variables para la creación de los nodos
          char [][] memoria;
          char ophijo=' ';
          int jugmx=0;
          int jugmy=0;
          String tipoHijo;
          int jughx=0;
          int jughy=0;
          

          //DescubroOperadores
          for(int i=0;i<operadores.length;i++)
          {
            //Si es Min o Max
            if(this.tipo.equals("Max"))
            {
              jughx=posX;
              jughy=posY;
            }
            else{
              jughx=pos2X;
              jughy=pos2Y;
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
                ophijo=operadores[i];
                memoria=new char[tamanoX][tamanoY];
                for(int idx=0;idx<tamanoX;idx++)
                {
                    for(int idy=0;idy<tamanoY;idy++)
                    {
                        memoria[idx][idy]=mapa[idx][idy];
                    }
                }
                if(this.tipo.equals("Max"))
                {
                  jugmx=jughx;
                  jugmy=jughy;
                  jughx=pos2X;
                  jughy=pos2Y;
                  tipoHijo="Min";
                  memoria[jugmx][jugmy]='m';
                }//Es min
                else{
                    jugmx=posX;
                    jugmy=posY;
                    tipoHijo="Max";
                    memoria[jughx][jughy]='h';
                }
                NodoEstado hijo= new NodoEstado(memoria, tipoHijo, ophijo, jugmx, jugmy, jughx, jughy,tamanoX,tamanoY);
                hijo.setAltura(altura+1);

                hijos.add(hijo);
              }
            }

          }
      }

      return hijos;

  }

}