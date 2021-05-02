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
        
    }
    
}
