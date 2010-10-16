/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agentesia;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Acá se planea poner todo lo referente a la animación del robot
 * @author jorgeorm
 */
public class EscenarioGrafico extends Canvas{
    private int ancho;
    private int alto;
    private BufferedImage bi_fondo,img_nave1,robot;
    private BufferedImage [] campo;
    private boolean flagEscenario;
    private int[][]mapa;
    private MediaTracker track;
    private int posRobotX,posRobotY;
    private int gifCampo;
    private ArrayList<Campo>campos;
    private Robot robotsito;
    public EscenarioGrafico(int alto, int ancho)
    {
        robotsito=new Robot();
        campos=new ArrayList<Campo>();
        posRobotX=0;
        posRobotY=0;
        mapa=new int[10][10];
        flagEscenario=false;
        this.ancho=ancho;
        this.alto=alto;
        this.setBounds(0,0,this.alto,this.ancho);
        //cargando Imagen de fondo
        bi_fondo=cargarImagen("img/Escenario.jpg");
        img_nave1=cargarImagen("img/nave1.gif");
       // campo=cargarImagen("img/campoMagnetico1.gif");
       // robot=cargarImagen("img/robotC.png");
       // this.setIgnoreRepaint(true);
    }
    public void limpiarBuffer()
    {
        bi_fondo=cargarImagen("img/Escenario.jpg");
    }

    public void setPosRobot(int x, int y)
    {
        this.posRobotX=x;
        this.posRobotY=y;
    }

    public void pintarRobot()
    {
        //this.getGraphics().drawImage(robot, posRobotX*60, posRobotY*60, this);
        robotsito.pintar(this.getGraphics(), this, posRobotX*60, posRobotY*60);
    }

    public void pintarRobot(int xVieja,int yVieja)
    {
        int x,y,xfin,yfin;
        x=xVieja*60;
        y=yVieja*60;
        xfin=posRobotX*60;
        yfin=posRobotY*60;
        BufferedImage buffer=new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
        Graphics graph=buffer.getGraphics();
        boolean puedo=true;
        while(puedo)
        {
            if(x<xfin)x=x+10;
            else if(x>xfin)x=x-10;
            else if(y>yfin)y=y-10;
            else if(y<yfin)y=y+10;
            else if(y==yfin && x==xfin)
            {
                break;
            }
            graph.drawImage(bi_fondo, 0, 0, this);
            robotsito.animarMov();
            robotsito.pintar(graph, this, x, y);
            //graph.drawImage(robot, x, y, this);
            Campo campito;
            for(int idx=0;idx<campos.size();idx++)
            {
                campito=campos.get(idx);
                campito.animarCampo();
                campito.pintarCampo(graph, this);
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
        if(flagEscenario)
        {
            paintEscenario(this.mapa);
            pintarRobot();
        }
    }

    @Override
    public void repaint() {
        this.paint(this.getGraphics());
    }

    public void paintEscenario(int[][]mapa)
    {
        Graphics graphcs=bi_fondo.getGraphics();
        //graphcs.drawImage(bi_fondo, 0, 0,alto,ancho,this);
        int cordx=0;
        int cordy=0;
        int factorx=alto/10;
        int factory=ancho/10;
        for(int idx=0;idx<10;idx++)
        {
            cordx=idx*factorx;

            for(int idy=0;idy<10;idy++)
            {
                cordy=idy*factory;
                int valMap=mapa[idx][idy];
                graphcs.drawRect(cordx, cordy, 60, 60);
                this.mapa[idx][idy]=valMap;
                switch(valMap)
                {
                //obstaculo
                case 1:
                    graphcs.setColor(Color.DARK_GRAY);
                    graphcs.fillRect(cordx, cordy, 60, 60);
                    graphcs.setColor(null);
                break;
                //Inicio
                case 2:
                    graphcs.setColor(Color.YELLOW);
                    graphcs.drawString("S", cordx+20, cordy+20);
                   // graphcs.drawImage(robot, cordx, cordy, this);
                break;
                //Salida
                case 3:
                    graphcs.setColor(Color.YELLOW);
                    graphcs.drawString("(*)", cordx+20, cordy+20);
                break;
                //Nave1
                case 4:
                    graphcs.setColor(Color.YELLOW);
                    //graphcs.drawString("N1", cordx+20, cordy+20);
                    graphcs.drawImage(img_nave1, cordx+10, cordy+10, this);
                break;
                //Nave2
                case 5:
                    graphcs.setColor(Color.YELLOW);
                    graphcs.drawString("N2", cordx+20, cordy+20);
                break;
                //Item
                case 6:
                    graphcs.setColor(Color.YELLOW);
                    graphcs.drawString("(I)", cordx+20, cordy+20);
                break;
                //CampoElectromagnetico
                case 7:
                    graphcs.setColor(Color.YELLOW);
                    Campo campito=new Campo(cordx,cordy);
                    campito.animarCampo();
                    campito.pintarCampo(graphcs, this);
                    campos.add(campito);
                    //graphcs.drawString("(¬¬)", cordx+20, cordy+20);
                   // graphcs.drawImage(campo, cordx, cordy, this);
                break;
                }
                graphcs.setColor(Color.DARK_GRAY);

            }
        }
        this.getGraphics().drawImage(bi_fondo, 0, 0, this);
        flagEscenario=true;
    }

   public void mostrarRuta(NodoEstado resp, int cordx, int cordy)
    {
       //Scanner para recorrer la ruta y sacar uno a uno los operadores
        Scanner ruta= new Scanner(resp.getOperador());
        ruta.useDelimiter(",");
        char op;
        while(ruta.hasNext())
        {
            //Graphics graph=bi_fondo.getGraphics();
            
            this.getGraphics().drawImage(bi_fondo, 0, 0, this);
            op=ruta.next().charAt(0);
            //Dependiendo del operador incremento o decremento la coordenada correspondiente para determinar el lugar del mapa al que me debo dirigir
            moverRobot(op);
        }
    }

   //Con un operador determina la nueva posición del robot
   public void moverRobot( char op)
   {
       int xVieja=posRobotX;
       int yVieja=posRobotY;
        switch (op)
        {
            case '↑':
            {
                posRobotY--;
                break;
            }
            case '→':
            {
                posRobotX++;
                break;
            }
            case '↓':
            {
                posRobotY++;
                break;
            }
            case '←':
            {
                posRobotX--;
                break;
            }
        }

       pintarRobot(xVieja, yVieja);
   }

   //Espera entre el pintado de cada movimiento del robot
   public void espera()
   {
       try
       {
           Thread.sleep(150);
       }
       catch(InterruptedException e)
       {
           
       }
   }
}
