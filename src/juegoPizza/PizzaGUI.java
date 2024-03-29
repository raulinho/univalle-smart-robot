/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PizzaGUI.java
 *
 * Created on 04-dic-2010, 16:13:43
 */

package juegoPizza;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Nathaly
 */
public class PizzaGUI extends javax.swing.JFrame {

    private PizzaEnvenenada pizza;
    private final String jugador1;
    private final String jugador2;
    private int eleccion;
    private String ganador;

    /** Creates new form PizzaGUI */
    public PizzaGUI(String jugador1, String jugador2) {
        initComponents();
        pizza= new PizzaEnvenenada();
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupP = new javax.swing.ButtonGroup();
        labelNPiezas = new javax.swing.JLabel();
        radioButton1 = new javax.swing.JRadioButton();
        radioButton2 = new javax.swing.JRadioButton();
        radioButton3 = new javax.swing.JRadioButton();
        labelImagen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pizza Envenenada");

        labelNPiezas.setText("Tomar:");

        buttonGroupP.add(radioButton1);
        radioButton1.setText("1");
        radioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton1ActionPerformed(evt);
            }
        });

        buttonGroupP.add(radioButton2);
        radioButton2.setText("2");
        radioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton2ActionPerformed(evt);
            }
        });

        buttonGroupP.add(radioButton3);
        radioButton3.setText("3");
        radioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton3ActionPerformed(evt);
            }
        });

        labelImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pizza.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(labelImagen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNPiezas)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(radioButton1)
                        .addGap(18, 18, 18)
                        .addComponent(radioButton2)
                        .addGap(18, 18, 18)
                        .addComponent(radioButton3)))
                .addGap(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(labelNPiezas)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radioButton3)
                            .addComponent(radioButton2)
                            .addComponent(radioButton1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(labelImagen)))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void radioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton1ActionPerformed
        eleccion=Integer.parseInt(evt.getActionCommand());
    }//GEN-LAST:event_radioButton1ActionPerformed

    private void radioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton2ActionPerformed
        eleccion=Integer.parseInt(evt.getActionCommand());
    }//GEN-LAST:event_radioButton2ActionPerformed

    private void radioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButton3ActionPerformed
       eleccion=Integer.parseInt(evt.getActionCommand());
    }//GEN-LAST:event_radioButton3ActionPerformed

    public void iniciarJuego()
    {
        pizza.jugar(jugador1, jugador2, this);
    }

    public int jugada()
    {
        return eleccion;
    }

    private ImageIcon crearImagen(String ruta)
    {
        java.net.URL imgURL= PizzaGUI.class.getResource(ruta);
        if(imgURL!=null)
        {
            return new ImageIcon(imgURL);
        }

        else
        {
            System.out.println("metio mal el dedo");
            return null;
        }
    }

    public void mostrarImagen(int piezas)
    {
        eleccion=0;
        int id=8-piezas;
        labelImagen.setIcon(crearImagen("/imagenes/pizza"+id+".jpg"));
    }

    public void anunciarGanador(String ganador)
    {
        this.ganador=ganador;
        JOptionPane.showMessageDialog(this, "El ganador es: "+ ganador, "Juego Terminado", WIDTH);
        this.setVisible(false);
    }

    public String getGanador() {
        return ganador;
    }

    /**
    * @param args the command line arguments
    */
    /*public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PizzaGUI().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupP;
    private javax.swing.JLabel labelImagen;
    private javax.swing.JLabel labelNPiezas;
    private javax.swing.JRadioButton radioButton1;
    private javax.swing.JRadioButton radioButton2;
    private javax.swing.JRadioButton radioButton3;
    // End of variables declaration//GEN-END:variables

}
