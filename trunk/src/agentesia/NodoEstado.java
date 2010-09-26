/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agentesia;

/**
 *
 * @author jorgeorm
 */
public class NodoEstado {
    String ruta ="";
    double costo;
    String operador;
    private Punto lugarMatriz;
    private int n_items,n_naves;
    private int nave;
    private int [][]memoria;

    public NodoEstado(String ruta, double costo, String operador, Punto lugarMatriz, int n_items, int n_naves, int nave, int[][]memoria) {
        this.ruta =ruta;
        this.costo = costo;
        this.operador = operador;
        this.lugarMatriz = lugarMatriz;
        this.n_items = n_items;
        this.n_naves = n_naves;
        this.nave = nave;
        this.memoria=memoria;
    }

    public int[][] getMemoria() {
        return memoria;
    }

    public double getCosto() {
        return costo;
    }

    public Punto getLugarMatriz() {
        return lugarMatriz;
    }

    public int getN_items() {
        return n_items;
    }

    public int getN_naves() {
        return n_naves;
    }

    public String getOperador() {
        return operador;
    }

    public String getRuta() {
        return ruta;
    }
    public int getNave()
    {
        return nave;
    }
}
