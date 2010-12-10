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
        robotsito=new Robot((posRobotX*75),(posRobotY*75));
        humano=new Human(posHumanX*75,posHumanY*75);
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
        mapa=new int[8][8];
        for(int idy=0;idy<8;idy++)
        {
            for(int idx=0;idx<8;idx++)
            {
                mapa[idx][idy]=tablero[idx][idy];
            }
        }
        posRobotX=0;
        posRobotY=7;
        posHumanX=0;
        posHumanY=7;
        
        robotsito.setPos((posRobotX*75),(posRobotY*75));
        humano.setPos((posHumanX*75),(posHumanY*75));

        limpiarBuffer();
        paintEscenario();
    }

    public void pintarJugador()
    {
        BufferedImage buffer=new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
        Graphics graph=buffer.getGraphics();
        graph.drawImage(bi_fondo, 0, 0, this);
        robotsito.pintar(graph, this);
        humano.pintar(graph, this);
        this.getGraphics().drawImage(buffer, 0, 0, this);
    }

    public void desplazarJugador(ArrayList posicion,String jugador,boolean atajo)
    {

        int xorig,yorig,xfin,yfin;
        int xo,xf,yo,yf;
        Jugador obj;
        xf=(Integer)posicion.get(0);
        yf=(Integer)posicion.get(1);

        xfin=(Integer)posicion.remove(0)*75;
        yfin=(Integer)posicion.remove(0)*75;

        if(jugador.equals("humano"))
        {
            obj=humano;
            xorig=(Integer)humano.getPos().get(0);
            yorig=(Integer)humano.getPos().get(1);
        }
        else
        {
            obj=robotsito;
            xorig=(Integer)robotsito.getPos().get(0);
            yorig=(Integer)robotsito.getPos().get(1);
        }

        xo=(Integer)obj.getPosMa().get(0);
        yo=(Integer)obj.getPosMa().get(1);

        /*if(xfin-xorig==0)
        {
            pintarVertical(yfin,yorig,jugador);
        }else if(yfin-yorig==0)
        {
            pintarHorizontal(xfin,xorig,jugador);
        }*/
        if(!atajo){
            if(yf==yo)
            {
                System.out.println("yfin "+yf+"yorigin "+yo);
                pintarHorizontal(xfin, xorig, jugador);
            }else if(xo==xf){
                System.out.println("xfin "+xf+"xorigin "+xo);
                pintarVertical(yfin, yorig, jugador);
            }else if(yf!=yo && xo!=xf)
            {
                System.out.println("xfin "+xf+"xorigin "+xo);
                System.out.println("yfin "+yf+"yorigin "+yo);
                if (yf % 2 == 0)
                {
                    pintarHorizontal(7*75, xorig, jugador);
                    pintarVertical(yfin, yorig, jugador);
                    pintarHorizontal(xfin, 7*75, jugador);
                }else
                {
                    pintarHorizontal(0, xorig, jugador);
                    pintarVertical(yfin, yorig, jugador);
                    pintarHorizontal(xfin, 0, jugador);
                }
            }
        }
        else
        {
            if(yf==yo)
            {
                System.out.println("yfin "+yf+"yorigin "+yo);
                pintarHorizontal(xfin, xorig, jugador);
            }else if(xo==xf){
                System.out.println("xfin "+xf+"xorigin "+xo);
                pintarVertical(yfin, yorig, jugador);
            }else if(yf!=yo && xo!=xf)
            {
                boolean signoY,signoX;
                signoY=yfin>yorig;
                signoX=xfin>xorig;
                if(signoX&&signoY)
                {
                    pintarENE(xfin,yfin,xorig,yorig,jugador);
                }else if(signoY&&!signoX)
                {
                    pintarONO(xfin,yfin,xorig,yorig,jugador);
                }else if(!signoY&&!signoX)
                {
                    pintarOSO(xfin,yfin,xorig,yorig,jugador);
                }else {
                    pintarESE(xfin,yfin,xorig,yorig,jugador);
                }

                    obj.setPosMa(xfin/75, yfin/75);
            }
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

        int factorX, factorY;

        factorY=alto/columns;
        factorX=ancho/fils;

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

    private void pintarENE(int xfin, int yfin, int xorig, int yorig, String jugador) {
        int a=xfin-xorig;
        int b=yfin-yorig;

        if(jugador.equals("humano"))
        {
            humano.setDerecha(xfin>xorig);
        }
        else
        {
            robotsito.setDerecha(xfin>xorig);
        }


        double d=a-(b/2);
        double ini,fin;

        while(xorig<xfin)
        {
           if(d<0)
           {
               xorig=xorig+7;
               d=d+a;
           }
           else{
               xorig=xorig+7;
               yorig=yorig+7;
               d=d+a-b;
           }

            BufferedImage buffer=new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
            Graphics graph=buffer.getGraphics();
            graph.drawImage(bi_fondo, 0, 0, this);

           if(jugador.equals("humano"))
            {
                //POR AHORA, HAY QUE QUITARLO
                humano.setPos(xorig, yorig);

                humano.animarMov();

                robotsito.pintar(graph, this);
                humano.pintar(graph, this);
            }
            else
            {
                //POR AHORA, HAY QUE QUITARLO
                robotsito.setPos(xorig, yorig);
                robotsito.animarMov();

                humano.pintar(graph, this);
                robotsito.pintar(graph, this);
            }
            this.getGraphics().drawImage(buffer, 0, 0, this);
            espera();
        }

    }

    private void pintarONO(int xfin, int yfin, int xorig, int yorig, String jugador) {
        int a=xfin-xorig;
        int b=yfin-yorig;

        if(jugador.equals("humano"))
        {
            humano.setDerecha(xfin>xorig);
        }
        else
        {
            robotsito.setDerecha(xfin>xorig);
        }


        double d=-(a+(b/2));
        double ini,fin;

        while(xorig>xfin)
        {
           if(d<0)
           {
               xorig=xorig-7;
               d=d-a;
           }
           else{
               xorig=xorig-7;
               yorig=yorig+7;
               d=d-(a+b);
           }

            BufferedImage buffer=new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
            Graphics graph=buffer.getGraphics();
            graph.drawImage(bi_fondo, 0, 0, this);

           if(jugador.equals("humano"))
            {
                //POR AHORA, HAY QUE QUITARLO
                humano.setPos(xorig, yorig);

                humano.animarMov();

                robotsito.pintar(graph, this);
                humano.pintar(graph, this);
            }
            else
            {
                //POR AHORA, HAY QUE QUITARLO
                robotsito.setPos(xorig, yorig);
                robotsito.animarMov();

                humano.pintar(graph, this);
                robotsito.pintar(graph, this);
            }
            this.getGraphics().drawImage(buffer, 0, 0, this);
            espera();
        }
    }

    private void pintarOSO(int xfin, int yfin, int xorig, int yorig, String jugador) {
        int a=xfin-xorig;
        int b=yfin-yorig;

        if(jugador.equals("humano"))
        {
            humano.setDerecha(xfin>xorig);
        }
        else
        {
            robotsito.setDerecha(xfin>xorig);
        }


        double d=-(a-(b/2));
        double ini,fin;

        while(xorig>xfin)
        {
           if(d<0)
           {
               xorig=xorig-7;
               d=d-a;
           }
           else{
               xorig=xorig-7;
               yorig=yorig-7;
               d=d-(a-b);
           }

            BufferedImage buffer=new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
            Graphics graph=buffer.getGraphics();
            graph.drawImage(bi_fondo, 0, 0, this);

           if(jugador.equals("humano"))
            {
                //POR AHORA, HAY QUE QUITARLO
                humano.setPos(xorig, yorig);

                humano.animarMov();

                robotsito.pintar(graph, this);
                humano.pintar(graph, this);
            }
            else
            {
                //POR AHORA, HAY QUE QUITARLO
                robotsito.setPos(xorig, yorig);
                robotsito.animarMov();

                humano.pintar(graph, this);
                robotsito.pintar(graph, this);
            }
            this.getGraphics().drawImage(buffer, 0, 0, this);
            espera();
        }
    }

    private void pintarESE(int xfin, int yfin, int xorig, int yorig, String jugador) {
        int a=xfin-xorig;
        int b=yfin-yorig;

        if(jugador.equals("humano"))
        {
            humano.setDerecha(xfin>xorig);
        }
        else
        {
            robotsito.setDerecha(xfin>xorig);
        }


        double d=a+(b/2);
        double ini,fin;

        while(xorig<xfin)
        {
           if(d<0)
           {
               xorig=xorig+7;
               d=d+a;
           }
           else{
               xorig=xorig+7;
               yorig=yorig-7;
               d=d+a+b;
           }

            BufferedImage buffer=new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
            Graphics graph=buffer.getGraphics();
            graph.drawImage(bi_fondo, 0, 0, this);

           if(jugador.equals("humano"))
            {
                //POR AHORA, HAY QUE QUITARLO
                humano.setPos(xorig, yorig);

                humano.animarMov();

                robotsito.pintar(graph, this);
                humano.pintar(graph, this);
            }
            else
            {
                //POR AHORA, HAY QUE QUITARLO
                robotsito.setPos(xorig, yorig);
                robotsito.animarMov();

                humano.pintar(graph, this);
                robotsito.pintar(graph, this);
            }
            this.getGraphics().drawImage(buffer, 0, 0, this);
            espera();
        }
    }

    private void pintarVertical(int yfin, int yorig, String jugador) {
        Jugador obj;
        if(jugador.equals("humano"))
        {
            obj=humano;
            humano.setDerecha(yfin>yorig);
        }else
        {
            obj=robotsito;
            robotsito.setDerecha(yfin>yorig);
        }

        obj.setPosMa(obj.getX(), yfin/75);

        if(yfin>yorig)
        {
            while(yorig<yfin)
            {
                yorig=yorig+7;

                BufferedImage buffer=new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
                Graphics graph=buffer.getGraphics();
                graph.drawImage(bi_fondo, 0, 0, this);

                if(jugador.equals("humano"))
                {
                //POR AHORA, HAY QUE QUITARLO
                humano.setPos(humano.idx, yorig);

                humano.animarMov();

                robotsito.pintar(graph, this);
                humano.pintar(graph, this);
                }
                else
                {
                //POR AHORA, HAY QUE QUITARLO
                robotsito.setPos(robotsito.idx, yorig);
                robotsito.animarMov();

                humano.pintar(graph, this);
                robotsito.pintar(graph, this);
                }
                this.getGraphics().drawImage(buffer, 0, 0, this);
                espera();
            }
        }
        else{
            
            while(yfin<yorig)
            {
                yorig=yorig-8;
                
                BufferedImage buffer=new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
                Graphics graph=buffer.getGraphics();
                graph.drawImage(bi_fondo, 0, 0, this);

                if(jugador.equals("humano"))
                {
                //POR AHORA, HAY QUE QUITARLO
                humano.setPos(humano.idx, yorig);

                humano.animarMov();

                robotsito.pintar(graph, this);
                humano.pintar(graph, this);
                }
                else
                {
                //POR AHORA, HAY QUE QUITARLO
                robotsito.setPos(robotsito.idx, yorig);
                robotsito.animarMov();

                humano.pintar(graph, this);
                robotsito.pintar(graph, this);
                }
                this.getGraphics().drawImage(buffer, 0, 0, this);
                espera();
            }

        }
    }

    private void pintarHorizontal(int xfin, int xorig, String jugador) {
        Jugador obj;
        if(jugador.equals("humano"))
        {
            obj=humano;
            humano.setDerecha(xfin>xorig);
        }else
        {
            obj=robotsito;
            robotsito.setDerecha(xfin>xorig);
        }

        int x=xfin/75;
        obj.setPosMa(x, obj.getY());
        if(xfin>xorig)
        {
            while(xorig<xfin)
            {
                xorig=xorig+8;

                BufferedImage buffer=new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
                Graphics graph=buffer.getGraphics();
                graph.drawImage(bi_fondo, 0, 0, this);

                if(jugador.equals("humano"))
                {
                //POR AHORA, HAY QUE QUITARLO
                humano.setPos(xorig, humano.idy);

                humano.animarMov();

                robotsito.pintar(graph, this);
                humano.pintar(graph, this);
                }
                else
                {
                //POR AHORA, HAY QUE QUITARLO
                robotsito.setPos(xorig, robotsito.idy);
                robotsito.animarMov();

                humano.pintar(graph, this);
                robotsito.pintar(graph, this);
                }
                this.getGraphics().drawImage(buffer, 0, 0, this);
                espera();
            }
        }
        else{

            while(xfin<xorig)
            {
                xorig=xorig-7;

                BufferedImage buffer=new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
                Graphics graph=buffer.getGraphics();
                graph.drawImage(bi_fondo, 0, 0, this);

                if(jugador.equals("humano"))
                {
                //POR AHORA, HAY QUE QUITARLO
                humano.setPos(xorig, humano.idy);

                humano.animarMov();

                robotsito.pintar(graph, this);
                humano.pintar(graph, this);
                }
                else
                {
                //POR AHORA, HAY QUE QUITARLO
                robotsito.setPos(xorig, robotsito.idy);
                robotsito.animarMov();

                humano.pintar(graph, this);
                robotsito.pintar(graph, this);
                }
                this.getGraphics().drawImage(buffer, 0, 0, this);
                espera();
            }

        }
    }
}
