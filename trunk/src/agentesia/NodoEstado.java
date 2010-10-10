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
    private String ruta ="";
    private double costo; // Costo para implementar en las busquedas NO informadas
    private double costo_estimado; // Costo para implementar en las busquedas Informadas
    private String operador;
    private int n_items,n_naves;
    private int nave;
    private int [][]memoria;
    private int x,y;

    public NodoEstado(String ruta, double costo, String operador, int x, int y, int n_items, int n_naves, int nave, int [][]memoria) {
        this.ruta =ruta;
        this.costo = costo;
        this.costo_estimado = 0.0;
        this.operador = operador;
        this.n_items = n_items;
        this.n_naves = n_naves;
        this.nave = nave;
        this.memoria=memoria;
        this.x=x;
        this.y=y;
    }

    public int getProfundidadPorOps()
    {
        int tamanoOps=operador.length();
        int comas=(tamanoOps-1)/2;
        tamanoOps=tamanoOps-comas;
        return tamanoOps;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public int [][] getMemoria() {
        return memoria;
    }

    public double getCosto() {
        return costo;
    }

    public double getCosto_est() {
        return costo_estimado;
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

    public void setCosto_est(double c) {
        costo_estimado = c;
    }

    public char ultimoOperador()
    {
        if(operador.length()>0)return operador.charAt(operador.length()-1);
        else return ' ';
    }
}
