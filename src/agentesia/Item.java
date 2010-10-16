/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agentesia;

import java.awt.Graphics;
import java.awt.Graphics2D;
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
public class Item {

    int x,y;
    boolean esta;
    BufferedImage imagen;

    public Item(int idx,int idy) {
        esta=true;
        imagen= cargarImagen("img/item.gif");
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

    public void animarItem()
    {
        esta=false;
    }

    public void pintarItem(Graphics graph,ImageObserver imobs)
    {
        if(esta)graph.drawImage(imagen, x, y, imobs);
    }

}
