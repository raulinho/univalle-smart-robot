/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tablero;

/**
 *
 * @author Nathaly
 */
public class Casilla {

    private int num_pos;
    private boolean ocupada;
    private String tipo;
    private int escalera;
    private int serpiente;

    public Casilla(int id)
    {
        num_pos=id;
    }

    public int getEscalera() {
        return escalera;
    }

    public void setEscalera(int escalera) {
        this.escalera = escalera;
    }

    public int getNum_pos() {
        return num_pos;
    }

    public void setNum_pos(int num_pos) {
        this.num_pos = num_pos;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public int getSerpiente() {
        return serpiente;
    }

    public void setSerpiente(int serpiente) {
        this.serpiente = serpiente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
