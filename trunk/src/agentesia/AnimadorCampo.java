/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agentesia;

/**
 *
 * @author nathamg
 */

import javax.swing.ImageIcon;

public class AnimadorCampo extends Thread {

    ImageIcon [] imagenes;

    public AnimadorCampo()
    {
        imagenes= new ImageIcon [4];
        imagenes[0]= new ImageIcon(getClass().getResource("img/campoMagnetico1.gif"));
        imagenes[1]= new ImageIcon(getClass().getResource("img/campoMagnetico2.gif"));
        imagenes[2]= new ImageIcon(getClass().getResource("img/campoMagnetico3.gif"));
        imagenes[3]= new ImageIcon(getClass().getResource("img/campoMagnetico4.gif"));
        
    }

    @Override

    public void run()
    {
        while(true)
        {
            
        }
    }

}
