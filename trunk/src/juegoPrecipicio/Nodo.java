/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package juegoPrecipicio;

import java.util.ArrayList;

/**
 *
 * @author Nathaly
 */
public class Nodo {

    private int escalonMaquina;
    private int escalonJugador;
    private int utilidad;
    private boolean maquinaCedio;
    private boolean jugadorCedio;
    private String tipo;
    private ArrayList <Nodo> hijos;

    public ArrayList<Nodo> getHijos() {
        return hijos;
    }

    public void setHijos(ArrayList<Nodo> hijos) {
        this.hijos = hijos;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public int getEscalonJugador() {
        return escalonJugador;
    }

    public void setEscalonJugador(int escalonJugador) {
        this.escalonJugador = escalonJugador;
    }

    public int getEscalonMaquina() {
        return escalonMaquina;
    }

    public void setEscalonMaquina(int escalonMaquina) {
        this.escalonMaquina = escalonMaquina;
    }

    public boolean isJugadorCedio() {
        return jugadorCedio;
    }

    public void setJugadorCedio(boolean jugadorCedio) {
        this.jugadorCedio = jugadorCedio;
    }

    public boolean isMaquinaCedio() {
        return maquinaCedio;
    }

    public void setMaquinaCedio(boolean maquinaCedio) {
        this.maquinaCedio = maquinaCedio;
    }

    public int getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(int utilidad) {
        this.utilidad = utilidad;
    }
    
}
