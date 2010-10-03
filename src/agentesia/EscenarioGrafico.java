/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agentesia;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
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
    private BufferedImage bi_fondo;
    private boolean flagEscenario;
    private int[][]mapa;

    public EscenarioGrafico(int alto, int ancho)
    {
        mapa=new int[10][10];
        flagEscenario=false;
        this.ancho=ancho;
        this.alto=alto;
        this.setBounds(0,0,this.alto,this.ancho);
        //cargando Imagen de fondo
        bi_fondo=cargarImagen("img/Escenario.jpg");
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
    
    public void paintEscenario(int[][]mapa)
    {
        Graphics graphcs=this.getGraphics();
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
                break;
                //Salida
                case 3:
                    graphcs.setColor(Color.YELLOW);
                    graphcs.drawString("(*)", cordx+20, cordy+20);
                break;
                //Nave1
                case 4:
                    graphcs.setColor(Color.YELLOW);
                    graphcs.drawString("N1", cordx+20, cordy+20);
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
                    graphcs.drawString("(¬¬)", cordx+20, cordy+20);
                break;
                }
                graphcs.setColor(Color.DARK_GRAY);

            }
        }
        flagEscenario=true;
    }

}
