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
 * @author mopsystems
 */
public class Human extends Jugador{
    public Human(int idx, int idy)
    {
        super();
        camiarDerecha= new BufferedImage[6];
        caminarIzquierda=new BufferedImage[6];
        this.x=idx/75;
        this.y=idy/75;

        this.idx=idx;
        this.idy=idy;

        for(int i=0;i<6;i++)
        {
            camiarDerecha[i] = cargarImagen("img/human/caminarDer/" +(i+1)+ ".gif");
            caminarIzquierda[i] = cargarImagen("img/human/caminarIzq/" +(i+1)+ ".gif");
        }
    }


    public void pintar(Graphics graph,ImageObserver imobs)
    {
        if(derecha)graph.drawImage(camiarDerecha[gifDer], idx, idy, imobs);
        else graph.drawImage(caminarIzquierda[gifIzq], idx, idy, imobs);
    }

}


