/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package juegoPizza;

import java.util.ArrayList;


/**
 *
 * @author Nathaly
 */
public class Nodo {

    private int utilidad;
    private ArrayList<Nodo> hijos;
    private int nom;
    private String tipo;
    private int piezasTomadas;

    public Nodo()
    {}

    public Nodo(int inicio)
    {
       System.out.println("Creando nodo "+ inicio);
       this.nom=inicio;
       utilidad=0;
    }

    public ArrayList<Nodo> getHijos() {
        return hijos;
    }

    public void setHijos(ArrayList<Nodo> hijos) {
        this.hijos = hijos;
    }

    public int getNom() {
        return nom;
    }

    public void setNom(int nom) {
        this.nom = nom;
    }

    public int getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(int utilidad) {
        this.utilidad = utilidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPiezasTomadas() {
        return piezasTomadas;
    }

    public void setPiezasTomadas(int piezasTomadas) {
        this.piezasTomadas = piezasTomadas;
    }
    
}
