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
public class Campo {
    int x,y;
    int gifCampo;
    BufferedImage [] imagenesCampo;

    public Campo(int idx,int idy) {
        imagenesCampo= new BufferedImage[4];
        imagenesCampo[0]= cargarImagen("img/campoMagnetico1.gif");
        imagenesCampo[1]= cargarImagen("img/campoMagnetico2.gif");
        imagenesCampo[2]= cargarImagen("img/campoMagnetico3.gif");
        imagenesCampo[3]= cargarImagen("img/campoMagnetico4.gif");
        x=idx;
        y=idy;
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

    public void animarCampo()
    {
        gifCampo++;
        if(gifCampo>3)gifCampo=0;
    }

    public void pintarCampo(Graphics graph,ImageObserver imobs)
    {
        graph.drawImage(imagenesCampo[gifCampo], x, y, imobs);
    }
}
