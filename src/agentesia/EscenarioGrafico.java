/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agentesia;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.util.*;

/**
 * Acá se planea poner todo lo referente a la animación del robot
 * @author jorgeorm
 */
public class EscenarioGrafico extends Canvas{
    private int ancho;
    private int alto;
    private BufferedImage bi_fondo;
    private int[][]mapa;
    private int[]snakesNladders;
    private int posRobotX,posRobotY;
    private int posHumanX,posHumanY;
    private Robot robotsito;
    private Human humano;

    public EscenarioGrafico(int alto, int ancho)
    {
        robotsito=new Robot();
        humano=new Human();
        this.ancho=ancho;
        this.alto=alto;
        this.setBounds(0,0,this.alto,this.ancho);
        //cargando Imagen de fondo
        limpiarBuffer();
        // campo=cargarImagen("img/campoMagnetico1.gif");
       // robot=cargarImagen("img/robotC.png");
       // this.setIgnoreRepaint(true);
        snakesNladders=new int[64];

        //Ladders
        snakesNladders[11]=28;
        snakesNladders[16]=33;
        snakesNladders[40]=57;
        //Snakes
        snakesNladders[30]=4;
        snakesNladders[26]=10;
        snakesNladders[34]=29;
        snakesNladders[54]=44;
        snakesNladders[62]=48;
        snakesNladders[60]=53;
        
        posRobotX=0;
        posRobotY=7;
        posHumanX=0;
        posHumanY=7;
     }

    public void limpiarBuffer()
    {
        bi_fondo=cargarImagen("img/cavescene.jpg");
    }

    public void setEscenario(int [][] tablero)
    {

        /* Del primer proy
        obj_escenario.limpiarBuffer();
        obj_escenario.paintEscenario(escen);
        obj_escenario.setPosRobot(cordxI, cordyI);
        obj_escenario.pintarJugador();
        */
        mapa=tablero.clone();
        limpiarBuffer();
        paintEscenario();
    }

    public void pintarJugador()
    {
        robotsito.pintar(true, this.getGraphics(), this, posRobotX*75, posRobotY*75);
        humano.pintar(true, this.getGraphics(), this, posHumanX*75, posHumanY*75);
    }

    public void pintarJugador(int xVieja,int yVieja, String juga)
    {
        int x,y,xfin,yfin;
        String jugador=juga;
        x=xVieja*75;
        y=yVieja*75;
        if(jugador.equals("robot"))
        {
            xfin=posRobotX*75;
            yfin=posRobotY*75;
        }
        else
        {
            xfin=posHumanX*75;
            yfin=posHumanY*75;
        }
        
        BufferedImage buffer=new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
        Graphics graph=buffer.getGraphics();
        boolean puedo=true;

        while(puedo)
        {
            if(x<xfin)x=x+6;
            else if(y<yfin)y=y+6;
            else if(y>=yfin && x>=xfin) break;        
                        
            /*
            if(x<xfin)x=x+6;
            else if(y<yfin)y=y+6;
            else if(((x>=xfin)&& (y>=yfin)) || ((xfin==0)&&(yfin==0))) break;*/

            graph.drawImage(bi_fondo, 0, 0, this);
            boolean der=xVieja*75<x;

            if(jugador.equals("robot"))
            {
                humano.pintar(der, graph, this, posHumanX*75, posHumanY*75);
                robotsito.animarMov(der);
                robotsito.pintar(der,graph, this, x, y);
            }
            else
            {
                robotsito.pintar(der, graph, this, posRobotX*75, posRobotY*75);
                humano.animarMov(der);
                humano.pintar(der,graph, this, x, y);
            }

            this.getGraphics().drawImage(buffer, 0, 0, this);
            espera();
        }

    }

    public final BufferedImage cargarImagen(String nombre)
    {
        URL url=null;
        try {
            url=getClass().getClassLoader().getResource(nombre);
            return ImageIO.read(url);
        } catch (IOException ex) {
            System.out.println("No se pudo cargar la imagen " + nombre +" de "+url);
            System.out.println("El error fue : ");
            Logger.getLogger(EscenarioGrafico.class.getName()).log(Level.SEVERE, null, ex);

            return null;
        }
    }

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        //Pintando imagen cargada
        limpiarBuffer();
        grphcs.drawImage(bi_fondo, 0, 0,alto,ancho,this);

        paintEscenario();
        pintarJugador();
        
    }

    @Override
    public void repaint() {
        this.paint(this.getGraphics());
    }

    public void paintEscenario()
    {
        int columns=mapa.length;
        int fils=mapa[0].length;

        BufferedImage buffer=new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
        Graphics graph=buffer.getGraphics();
        boolean puedo=true;

        int factorX, factorY;

        factorY=alto/fils;
        factorX=ancho/columns;

        graph.drawImage(bi_fondo, 0, 0, this);

        int posX,posY;

        for(int idy=0;idy<columns;idy++)
        {
            for(int idx=0;idx<fils;idx++)
            {
                posX=idx*factorX;
                posY=idy*factorY;
                //System.out.println("estoy en "+posX+" "+posY);
                Graphics2D g2D=(Graphics2D)graph;
                g2D.setColor(Color.black);
                g2D.setStroke (new BasicStroke(1.2f));
                g2D.drawRect(posX, posY, factorX, factorY);
                g2D.drawString((mapa[idx][idy])+"", posX+factorX/2, posY+factorY/2);

                for(int casilla=0; casilla<64; casilla++)
                {
                    if((snakesNladders[casilla]!= 0) && (casilla+1==mapa[idx][idy]))
                    {
                        Vector p1= buscarPuntos(casilla+1);
                        Vector p2= buscarPuntos(snakesNladders[casilla]);
                        if(casilla<snakesNladders[casilla])
                        {    //Se pintan escaleras
                            int iniSx1=(((Integer)p1.get(0)*factorX)+factorX/3);
                            int iniSy=(((Integer)p1.get(1)*factorX)+factorX/2);
                            int iniSx2=(((Integer)p1.get(0)*factorX)+2*factorX/3);
                            int finSx1=(((Integer)p2.get(0)*factorY)+factorY/3);
                            int finSx2=(((Integer)p2.get(0)*factorY)+2*factorY/3);
                            int finSy=(((Integer)p2.get(1)*factorY)+factorY/2);
                            g2D.setColor (Color.red);
                            g2D.setStroke (new BasicStroke(5.2f));
                            g2D.drawLine(iniSx1, iniSy, finSx1, finSy);
                            g2D.drawLine(iniSx2, iniSy, finSx2, finSy);
                        }
                        else
                        {
                            //Se pintan serpientes
                            int iniSx=(((Integer)p1.get(0)*factorX)+factorX/2);
                            int iniSy=(((Integer)p1.get(1)*factorX)+factorX/2);
                            int finSx=(((Integer)p2.get(0)*factorY)+factorY/2);
                            int finSy=(((Integer)p2.get(1)*factorY)+factorY/2);
                            g2D.setColor (Color.green);
                            g2D.setStroke (new BasicStroke(5.2f));
                            g2D.drawLine(iniSx, iniSy, finSx, finSy);
                        }
                    }
                }

            }
        }
        bi_fondo.getGraphics().drawImage(buffer, 0, 0, this);
        this.getGraphics().drawImage(buffer, 0, 0, this);
    }

    public Vector buscarPuntos(int num)
    {
        Vector vecPunto = new Vector();

        for(int y=0; y<8; y++)
        {
           for(int x=0; x<8; x++)
           {
                if(num==mapa[x][y])
                {
                    vecPunto.add(x);
                    vecPunto.add(y);
                }
           }
        }
        return vecPunto;
    }
    
   //Con una cantidad de casillas y la especificacion de a quien se mueve,se desplaza un jugador
   public void moverJugador(int m, String j)
   {
       String jugador=j;
       int xVieja, yVieja, posActual;
       if(jugador.equals("robot"))
       {
            xVieja=posRobotX;
            yVieja=posRobotY;
            posActual=mapa[posRobotX][posRobotY];
       }
       else
       {
           xVieja=posHumanX;
           yVieja=posHumanY;
           posActual=mapa[posHumanX][posHumanY];
       }

        for(int idy=0; idy<8; idy++)
        {
            for(int idx=0; idx<8; idx++)
            {
                if(mapa[idx][idy]==posActual+m)
                {
                    if(jugador.equals("robot"))
                    {
                        posRobotX=idx;
                        posRobotY=idy;
                    }
                    else
                    {
                        posHumanX=idx;
                        posHumanY=idy;
                    }
                }
            }
        }
        pintarJugador(xVieja, yVieja, jugador);
       

        /* BufferedImage buffer=new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
        Graphics graph=buffer.getGraphics();
        graph.drawImage(bi_fondo, 0, 0, this);

        /*int cont=0;
        while(cont<11){
            robotsito.animarMov(true);
            this.getGraphics().drawImage(buffer, 0, 0, this);
            //cont++;
            espera();
       //}*/
   }

   public int getPosHumanX() {
        return posHumanX;
    }

    public int getPosHumanY() {
        return posHumanY;
    }

    public int getPosRobotX() {
        return posRobotX;
    }

    public int getPosRobotY() {
        return posRobotY;
    }

    public int[] getSnakesNladders() {
        return snakesNladders;
    }
    
   //Espera entre el pintado de cada movimiento del robot
   public void espera()
   {
       try
       {
           Thread.sleep(100);
       }
       catch(InterruptedException e)
       {
           
       }
   }
}
