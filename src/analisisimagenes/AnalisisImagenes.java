/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisisimagenes;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import data.JframeImagen;

/**
 *
 * @author Dell
 */
public class AnalisisImagenes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       // visualización de la imagen dentro de la GUI
      cambiarRGB (new Color(125,255,0),herramientas.HerramientasImagenes.abrirImagen());
     
      
    }
    
    public static void cambiarRGB(Color color1, Image imagen ){
    
        // para cuantización de la imagen vamos a un ocupar BufferedImage
           BufferedImage bImagen = herramientas.HerramientasImagenes.toBufferedImage(imagen);
          JframeImagen frame = new JframeImagen(imagen);
           color1 = new Color(125,255,0);
           bImagen.setRGB(0,0,color1.getRGB());
                  
         JframeImagen frame1 = new JframeImagen(herramientas.HerramientasImagenes.toImage(bImagen));
    }
    
}
