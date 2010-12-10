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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author jorgeorm
 */
public abstract class Jugador {
    protected int idx,x,y;

    protected int idy;
    protected int gifDer,gifIzq;
    protected BufferedImage [] camiarDerecha,caminarIzquierda;
    protected boolean derecha;


    public Jugador() {
        x=0;
        y=0;
        idx=0;
        idy=0;
        gifDer=0;
        gifIzq=0;
        derecha=true;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public void setIdy(int idy) {
        this.idy = idy;
    }

    public void setPos(int idx,int idy)
    {
        this.idx=idx;
        this.idy=idy;
    }

    public int getIdx() {
        return idx;
    }

    public int getIdy() {
        return idy;
    }

    public void setDerecha(boolean derecha) {
        this.derecha = derecha;
    }

    public boolean getDecha()
    {
        return this.derecha;
    }

    public ArrayList getPos()
    {
        ArrayList pos= new ArrayList();
        pos.add(idx);
        pos.add(idy);
        return pos;
    }
    public ArrayList getPosMa()
    {
        ArrayList pos= new ArrayList();
        pos.add(x);
        pos.add(y);
        return pos;
    }
    public void setPosMa(int idx, int idy)
    {
        this.x=idx;
        this.y=idy;
    }
    public int getX()
    {
        return this.x;
    }
    public int getY()
    {
        return this.y;
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
            Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);

            return null;
        }
    }
    
    public void animarMov()
    {
        if(derecha)
        {
            gifDer++;
            if(gifDer==camiarDerecha.length)gifDer=0;
        }else{
            gifIzq++;
            if(gifIzq==caminarIzquierda.length)gifIzq=0;
        }

    }

    public abstract void pintar(Graphics graph,ImageObserver imobs);
}
