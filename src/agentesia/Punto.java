/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agentesia;

/**
 *
 * @author jorgeorm
 */
public class Punto {
    private int x=0;
    private int y=0;

    public Punto(int cordX, int cordY)
    {
        this.x=cordX;
        this.y=cordY;
    }

    public int calcularManhatan()
    {
        return 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        String punto="";
        punto= "("+x+","+y+")";
        return punto;
    }



}
