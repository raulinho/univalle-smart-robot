/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agentesia;

/**
 *
 * @author jorgeorm
 */
public class FactoriaBusqueda {

    private NodoEstado raiz;
    private int cordXf,cordYf;
    private int [] [] mapa;

    public FactoriaBusqueda(NodoEstado nodo,int xf,int yf,int [][]escen) {
        raiz=nodo;
        cordXf=xf;
        cordYf=yf;
        mapa=new int  [10][10];
        if(escen!=null)
        {
            for(int idy=0;idy<10;idy++)
            {
                for(int idx=0;idx<10;idx++)
                {
                    this.mapa[idx][idy]=escen[idx][idy];
                }
            }
        }else System.err.println("El escenario debe ser diferente de null");
        
    }
    public FactoriaBusqueda(NodoEstado nodo,int xf,int yf) {
        raiz=nodo;
        cordXf=xf;
        cordYf=yf;
    }

    public Busqueda crearNoInformada (String tipo)
    {
        Busqueda busqueda=null;
        if(tipo.equals("Amplitud"))busqueda=new Amplitud(raiz, cordXf, cordYf, mapa);
        else if(tipo.equals("Costo"))busqueda=new Costo(raiz, cordXf, cordYf, mapa);
        else if(tipo.equals("Profundidad"))busqueda=new Profundidad(raiz,cordXf,cordYf,mapa);
        return busqueda;
    }

    public Busqueda crearInformada (String tipo)
    {
        Busqueda busqueda=null;
        if(tipo.equals("Avara"))busqueda=new Avara(raiz, cordXf, cordYf, mapa);
        else if(tipo.equals("A*"))busqueda=new A_estrella(raiz, cordXf, cordYf, mapa);
        return busqueda;
    }
}
