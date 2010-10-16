/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agentesia;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author jorgeorm
 */
public class Robot {
    int x,y;
    int gifCampo,gifDer;
    BufferedImage [] camiarDerecha,caminarIzquierda;

    public Robot() {
        camiarDerecha= new BufferedImage[10];
        gifDer=0;
        gifCampo=0;
        for(int i=1;i<10;i++)
        {
            System.out.println("img/caminarDer/" + i + ".gif");
            camiarDerecha[i] = cargarImagen("img/caminarDer/" + i + ".gif");

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

    public void animarMov()
    {
        gifDer++;
        if(gifDer>8)gifDer=0;
    }

    public void pintar(Graphics graph,ImageObserver imobs,int x,int y)
    {
        graph.drawImage(camiarDerecha[gifDer], x, y, imobs);
    }

}
