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
public class Robot extends Jugador{


    public Robot(int idx,int idy)
    {
        super();
        camiarDerecha= new BufferedImage[9];
        caminarIzquierda=new BufferedImage[9];
        this.x=idx/75;
        this.y=idy/75;

        this.idx=idx;
        this.idy=idy;
        
        for(int i=0;i<9;i++)
        {
            camiarDerecha[i] = cargarImagen("img/robot/caminarDer/" +(i+1)+ ".gif");
            caminarIzquierda[i] = cargarImagen("img/robot/caminarIzq/" +(i+1)+ ".gif");
        }
    }


    public void pintar(Graphics graph,ImageObserver imobs)
    {
        if(derecha)graph.drawImage(camiarDerecha[gifDer], idx, idy, imobs);
        else graph.drawImage(caminarIzquierda[gifIzq], idx, idy, imobs);
    }

}