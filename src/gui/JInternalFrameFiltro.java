/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import espacial.Histograma;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 *
 * @author Manuel
 */
public class JInternalFrameFiltro extends javax.swing.JInternalFrame {

    /**
     * Creates new form JInternalFrameFiltro
     */
    private JInternalFrameImagen internal;
    private Image origigi;

    public JInternalFrameFiltro(JInternalFrameImagen internal) {
        this.internal = internal;
        initComponents();

        this.origigi = herramientas.HerramientasImagen.copiarImagen(internal.getImagenOriginal());

        this.jButtonGrises.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                BufferedImage bi = herramientas.HerramientasImagen.toBufferedImage(internal.getImagenOriginal());
                for (int x = 0; x < bi.getWidth(); x++) {
                    for (int y = 0; y < bi.getHeight(); y++) {
                        Color c1 = new Color(bi.getRGB(x, y));
                        int med = (c1.getRed() + c1.getGreen() + c1.getBlue()) / 3;
                        bi.setRGB(x, y, new Color(med, med, med).getRGB());
                    }
                }
                Image nueva = herramientas.HerramientasImagen.toImage(bi);
                internal.setImagen(nueva);
            }
        });

        this.jButtonBN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedImage bi = herramientas.HerramientasImagen.toBufferedImage(origigi);

                for (int x = 0; x < bi.getWidth(); x++) {
                    for (int y = 0; y < bi.getHeight(); y++) {
                        Color c1 = new Color(bi.getRGB(x, y));
                        int med = (c1.getRed() + c1.getGreen() + c1.getBlue()) / 3;
                        if (med <= jSlider1.getValue()) {
                            bi.setRGB(x, y, 0);
                        } else {
                            bi.setRGB(x, y, Color.WHITE.getRGB());

                        }

                    }
                }
                Image nueva = herramientas.HerramientasImagen.toImage(bi);
                internal.setImagen(nueva);
            }
        });

        this.jButtonNeg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedImage bi = herramientas.HerramientasImagen.toBufferedImage(internal.getImagenOriginal());
                for (int x = 0; x < bi.getWidth(); x++) {
                    for (int y = 0; y < bi.getHeight(); y++) {
                        Color c1 = new Color(bi.getRGB(x, y));
                        int r = c1.getRed();
                        int g = c1.getGreen();
                        int b = c1.getBlue();

                        bi.setRGB(x, y, new Color(255 - r, 255 - g, 255 - b).getRGB());
                    }
                }
                Image nueva2 = herramientas.HerramientasImagen.toImage(bi);
                internal.setImagen(nueva2);
            }
        });
        this.jButtonBN1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedImage bi = herramientas.HerramientasImagen.toBufferedImage(origigi);
                System.out.println("U1: " + jSlider2.getValue() + "U2: " + jSlider3.getValue());
                for (int x = 0; x < bi.getWidth(); x++) {
                    for (int y = 0; y < bi.getHeight(); y++) {
                        Color c1 = new Color(bi.getRGB(x, y));
                        int med = (c1.getRed() + c1.getGreen() + c1.getBlue()) / 3;
                        if (jSlider2.getValue() <= jSlider3.getValue() && med >= jSlider2.getValue() && med <= jSlider3.getValue()) {

                            bi.setRGB(x, y, 0);
                        } else {
                            bi.setRGB(x, y, Color.WHITE.getRGB());

                        }

                    }
                }
                Image nueva = herramientas.HerramientasImagen.toImage(bi);
                internal.setImagen(nueva);
            }
        });
        this.jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                BufferedImage bi = herramientas.HerramientasImagen.toBufferedImage(origigi);
                double histograma[] = new double[256];

                Color color;
                for (int x = 0; x < bi.getWidth(); x++) {
                    for (int y = 0; y < bi.getHeight(); y++) {
                        // extraer el color
                        color = new Color(bi.getRGB(x, y));
                        int gris = (color.getRed() + color.getBlue() + color.getGreen()) / 3; // (0-255)
                        histograma[gris]++;

                    }
                }

                int ui = calcularUmbralInicial(histograma);
                int uNuevo = 0;
                System.out.println(ui);
                do {
                    uNuevo = ui;
                    ui = reajustarUmbral(ui, histograma);
                    System.out.println(ui);
                } while (uNuevo != ui);

                BufferedImage bi2 = herramientas.HerramientasImagen.toBufferedImage(origigi);
                for (int x = 0; x < bi2.getWidth(); x++) {
                    for (int y = 0; y < bi2.getHeight(); y++) {
                        Color c1 = new Color(bi2.getRGB(x, y));
                        int med = (c1.getRed() + c1.getGreen() + c1.getBlue()) / 3;
                        if (med <= ui) {

                            bi2.setRGB(x, y, 0);
                        } else {
                            bi2.setRGB(x, y, Color.WHITE.getRGB());

                        }

                    }
                }
                Image nueva2 = herramientas.HerramientasImagen.toImage(bi2);
                internal.setImagen(nueva2);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonGrises = new javax.swing.JButton();
        jButtonNeg = new javax.swing.JButton();
        jButtonBN = new javax.swing.JButton();
        jSlider1 = new javax.swing.JSlider();
        jSlider2 = new javax.swing.JSlider();
        jSlider3 = new javax.swing.JSlider();
        jButtonBN1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setClosable(true);

        jButtonGrises.setText("Tono de grises");

        jButtonNeg.setText("Negativo");

        jButtonBN.setText("Blanco y negro");

        jSlider1.setMajorTickSpacing(10);
        jSlider1.setMaximum(255);
        jSlider1.setMinorTickSpacing(1);
        jSlider1.setPaintLabels(true);
        jSlider1.setPaintTicks(true);
        jSlider1.setSnapToTicks(true);
        jSlider1.setToolTipText("");
        jSlider1.setValue(15);

        jSlider2.setMajorTickSpacing(10);
        jSlider2.setMaximum(255);
        jSlider2.setMinorTickSpacing(1);
        jSlider2.setPaintLabels(true);
        jSlider2.setPaintTicks(true);
        jSlider2.setSnapToTicks(true);
        jSlider2.setToolTipText("");
        jSlider2.setValue(15);

        jSlider3.setMajorTickSpacing(10);
        jSlider3.setMaximum(255);
        jSlider3.setMinorTickSpacing(1);
        jSlider3.setPaintLabels(true);
        jSlider3.setPaintTicks(true);
        jSlider3.setSnapToTicks(true);
        jSlider3.setToolTipText("");
        jSlider3.setValue(15);

        jButtonBN1.setText("Blanco y negro (Doble U)");

        jButton1.setText("Binarización Automática");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonGrises, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                    .addComponent(jButtonNeg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonBN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSlider2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSlider3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonBN1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonGrises)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonNeg)
                .addGap(23, 23, 23)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSlider3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonBN1)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
    private static int calcularUmbralInicial(double[] histograma) {
        int numPixels = 0;
        int suma = 0;
        for (int x = 0; x < histograma.length; x++) {
            numPixels += histograma[x];
            suma += histograma[x] * x;
        }
        return (int) (suma / numPixels);
    }

    private static int reajustarUmbral(int ui, double[] histograma) {
        int u1, u2;
        int a1 = 0, a2 = 0, n1 = 0, n2 = 0;
        a1 += histograma[0];
        for (int x = 1; x < ui; x++) {
            a1 += histograma[x] * x;
            n1 += histograma[x];
        }

        for (int y = ui; y <= 255; y++) {
            a2 += histograma[y] * y;
            n2 += histograma[y];
        }
        if (n1 == 0 || n2 == 0) {
            return 0;
        }
        u1 = a1 / n1;
        u2 = a2 / n2;
        return (int) ((u1 + u2) / 2);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonBN;
    private javax.swing.JButton jButtonBN1;
    private javax.swing.JButton jButtonGrises;
    private javax.swing.JButton jButtonNeg;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSlider jSlider2;
    private javax.swing.JSlider jSlider3;
    // End of variables declaration//GEN-END:variables
}
