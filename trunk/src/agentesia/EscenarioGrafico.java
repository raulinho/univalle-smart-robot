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
    private BufferedImage bi_fondo,img_nave1,robot,campo;
    private boolean flagEscenario;
    private int[][]mapa;
    private MediaTracker track;

    public EscenarioGrafico(int alto, int ancho)
    {
        mapa=new int[10][10];
        flagEscenario=false;
        this.ancho=ancho;
        this.alto=alto;
        this.setBounds(0,0,this.alto,this.ancho);
        //cargando Imagen de fondo
        bi_fondo=cargarImagen("img/Escenario.jpg");
        img_nave1=cargarImagen("img/nave1.gif");
        campo=cargarImagen("img/campoMagnetico1.gif");
        robot=cargarImagen("img/robotC.png");
       // this.setIgnoreRepaint(true);
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
        grphcs.drawImage(bi_fondo, 0, 0,alto,ancho,this);
        if(flagEscenario)paintEscenario(this.mapa);
    }

    @Override
    public void repaint() {
        super.repaint();
        Graphics graph=getGraphics();
        graph.drawImage(bi_fondo, 0, 0,alto,ancho,this);
        if(flagEscenario)paintEscenario(this.mapa);
        //paint(this.getGraphics());
    }

    /*@Override
    public void update(Graphics grphcs)
    {
        paint(grphcs);
    }*/

    public void paintEscenario(int[][]mapa)
    {
        Graphics graphcs=this.getGraphics();
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
                    //graphcs.drawString("(¬¬)", cordx+20, cordy+20);
                    graphcs.drawImage(campo, cordx, cordy, this);
                    campo.flush();
                break;
                }
                graphcs.setColor(Color.DARK_GRAY);

            }
        }
        flagEscenario=true;
    }

   public void mostrarRuta(NodoEstado resp, int cordx, int cordy)
    {
       //Scanner para recorrer la ruta y sacar uno a uno los operadores
        Scanner ruta= new Scanner(resp.getOperador());
        ruta.useDelimiter(",");
        char op;
        //Almacena el valor de la coordenada en el mapa para restauracion
        int ant=0;
        while(ruta.hasNext())
        {
            //restauro valor anterior antes de determinar el sgte punto
            this.mapa[cordx][cordy]=ant;
            op=ruta.next().charAt(0);
            System.out.println("entre while "+op);
            //Dependiendo del operador incremento o decremento la coordenada correspondiente para determinar el lugar del mapa al que me debo dirigir
                    switch (op)
                    {
                        case '↑':
                        {
                            cordy--;
                            espera();
                            break;
                        }
                        case '→':
                        {
                            cordx++;
                            espera();
                            break;
                        }
                        case '↓':
                        {
                            cordy++;
                            espera();
                            break;
                        }
                        case '←':
                        {
                            cordx--;
                            espera();
                            break;
                        }
                    }
                    //guardo el valor que esta en la coordenada antes de colocar el robot
                    ant=this.mapa[cordx][cordy];
                    //se coloca el dos para que el repaint pinte el robot en la coordenada
                    this.mapa[cordx][cordy]=2;
                    repaint();
        }
    }

   //Espera entre el pintado de cada movimiento del robot
   public void espera()
   {
       try
       {
           Thread.sleep(500);
       }
       catch(InterruptedException e)
       {
           
       }
   }

   public void pintarRobot(int cordx, int cordy, Graphics graph)
   {
       graph.drawImage(robot, cordx*60, cordy*60, this);
   }

}
