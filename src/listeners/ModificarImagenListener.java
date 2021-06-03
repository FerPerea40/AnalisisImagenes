/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import espacial.Histograma;
import gui.JFramePrincipal;
import gui.JInternalFrameConv;
import gui.JInternalFrameConv5;
import gui.JInternalFrameExpa;
import gui.JInternalFrameFiltro;
import gui.JInternalFrameIlumi;
import gui.JInternalFrameImagen;
import gui.JInternalFrameKirsch;
import gui.JInternalFrameModificar;
import gui.JInternalFrameSal;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JMenuItem;

/**
 *
 * @author working
 */
public class ModificarImagenListener implements ActionListener{
    
     
    private  JFramePrincipal framePrincipal;

    public ModificarImagenListener(JFramePrincipal framePrincipal) {
        this.framePrincipal = framePrincipal;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem item = (JMenuItem)e.getSource();
        if (item.getText().equals("Modificar Pixeles")){
            JInternalFrameImagen internal = (JInternalFrameImagen) this.framePrincipal.getjDesktopPanePrincipal().getSelectedFrame();
            JInternalFrameModificar internalNuevo = new JInternalFrameModificar(internal);
            internalNuevo.setVisible(true);
            this.framePrincipal.getjDesktopPanePrincipal().add(internalNuevo);
        }
          if(item.getText().equals("Filtros")){
            JInternalFrameImagen internal2 = (JInternalFrameImagen) this.framePrincipal.getjDesktopPanePrincipal().getSelectedFrame();
            JInternalFrameFiltro internalNuevo2 = new JInternalFrameFiltro(internal2);
            internalNuevo2.setVisible(true);
            this.framePrincipal.getjDesktopPanePrincipal().add(internalNuevo2);
        }
            if(item.getText().equals("Histograma")){
            JInternalFrameImagen internal2 = (JInternalFrameImagen) this.framePrincipal.getjDesktopPanePrincipal().getSelectedFrame();
           
            BufferedImage bi = herramientas.HerramientasImagen.toBufferedImage(internal2.getImagenOriginal());
               Histograma h = new Histograma(bi);
             h.calcularHistogramas();
              h.graficar();
             System.out.println();
//            JInternalFrameFiltro internalNuevo2 = new JInternalFrameFiltro(internal2);
//            internalNuevo2.setVisible(true);
//            this.framePrincipal.getjDesktopPanePrincipal().add(internalNuevo2);
        }
            if (item.getText().equals("Iluminación")){
           JInternalFrameImagen internal2 = (JInternalFrameImagen) this.framePrincipal.getjDesktopPanePrincipal().getSelectedFrame();
            JInternalFrameIlumi internalNuevo2 = new JInternalFrameIlumi(internal2 , this.framePrincipal);
            internalNuevo2.setVisible(true);
            this.framePrincipal.getjDesktopPanePrincipal().add(internalNuevo2);
                
        }
            if (item.getText().equals("Expansiones")){
           JInternalFrameImagen internal2 = (JInternalFrameImagen) this.framePrincipal.getjDesktopPanePrincipal().getSelectedFrame();
            JInternalFrameExpa internalNuevo2 = new JInternalFrameExpa(internal2 , this.framePrincipal);
            internalNuevo2.setVisible(true);
            this.framePrincipal.getjDesktopPanePrincipal().add(internalNuevo2);
            
        }            
             if (item.getText().equals("Convolución 3x3")){
           JInternalFrameImagen internal2 = (JInternalFrameImagen) this.framePrincipal.getjDesktopPanePrincipal().getSelectedFrame();
            Image imagen = internal2.getImagenOriginal();
            JInternalFrameConv internalNuevo2 = new JInternalFrameConv(internal2 ,imagen, this.framePrincipal);
            internalNuevo2.setVisible(true);
            this.framePrincipal.getjDesktopPanePrincipal().add(internalNuevo2);
            
        }
             if (item.getText().equals("Convolución 5x5")){
           JInternalFrameImagen internal2 = (JInternalFrameImagen) this.framePrincipal.getjDesktopPanePrincipal().getSelectedFrame();
            Image imagen = internal2.getImagenOriginal();
            JInternalFrameConv5 internalNuevo2 = new JInternalFrameConv5(internal2 ,imagen, this.framePrincipal);
            internalNuevo2.setVisible(true);
            this.framePrincipal.getjDesktopPanePrincipal().add(internalNuevo2);
            
        }
                if (item.getText().equals("Kirsch")){
           JInternalFrameImagen internal2 = (JInternalFrameImagen) this.framePrincipal.getjDesktopPanePrincipal().getSelectedFrame();
            Image imagen = internal2.getImagenOriginal();
            JInternalFrameKirsch internalNuevo2 = new JInternalFrameKirsch(internal2 ,imagen, this.framePrincipal);
            internalNuevo2.setVisible(true);
            this.framePrincipal.getjDesktopPanePrincipal().add(internalNuevo2);
            
        }
                 if (item.getText().equals("Ruido Pimienta/Sal")){
           JInternalFrameImagen internal2 = (JInternalFrameImagen) this.framePrincipal.getjDesktopPanePrincipal().getSelectedFrame();
            Image imagen = internal2.getImagenOriginal();
            JInternalFrameSal internalNuevo2 = new JInternalFrameSal(internal2 ,imagen, this.framePrincipal);
            internalNuevo2.setVisible(true);
            this.framePrincipal.getjDesktopPanePrincipal().add(internalNuevo2);
            
        }
                  if (item.getText().equals("Eliminar Ruido")){
           JInternalFrameImagen internal2 = (JInternalFrameImagen) this.framePrincipal.getjDesktopPanePrincipal().getSelectedFrame();
            Image imagen = internal2.getImagenOriginal();
            
          Image nueva = herramientas.HerramientasImagen.MaxMin(imagen);

            JInternalFrameImagen internalNuevo2 = new JInternalFrameImagen(nueva);
            internalNuevo2.setVisible(true);
            this.framePrincipal.getjDesktopPanePrincipal().add(internalNuevo2);
            
        }
                    if (item.getText().equals("Frecuencias Grises")){
           JInternalFrameImagen internal2 = (JInternalFrameImagen) this.framePrincipal.getjDesktopPanePrincipal().getSelectedFrame();
             
             Image imagen = internal2.getImagenOriginal();
              BufferedImage bi = herramientas.HerramientasImagen.toBufferedImage(internal2.getImagenOriginal());

              for (int x = 0; x < bi.getWidth(); x++) {
                    for (int y = 0; y < bi.getHeight(); y++) {
                        Color c1 = new Color(bi.getRGB(x, y));
                        int med = (c1.getRed() + c1.getGreen() + c1.getBlue()) / 3;
                        bi.setRGB(x, y, new Color(med, med, med).getRGB());
                    }
                }
                imagen = herramientas.HerramientasImagen.toImage(bi);
                internal2.setImagen(imagen);
             herramientas.HerramientasImagen.FrecuenciasGrises(imagen,this.framePrincipal);
             



//Image nueva2 = herramientas.HerramientasImagen.FrecuenciasInv(imagen);

//            JInternalFrameImagen internalNuevo2 = new JInternalFrameImagen(nueva);
//            internalNuevo2.setVisible(true);
//            this.framePrincipal.getjDesktopPanePrincipal().add(internalNuevo2);
            
//            
//            JInternalFrameImagen internalNuevo22 = new JInternalFrameImagen(nueva2);
//            internalNuevo22.setVisible(true);
//            this.framePrincipal.getjDesktopPanePrincipal().add(internalNuevo22);
        }
                   
    }
    
}
