/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herramientas;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author working
 */
public class HerramientasImagen {

    public static Image abrirImagen() {

        try {
            // definir los filtros para lectura
            FileNameExtensionFilter filtro
                    = new FileNameExtensionFilter("Imagenes", "jpg", "jpeg", "png", "bmp");
            // crear un selector de archivos
            JFileChooser selector = new JFileChooser();
            // agregar el filtro al selector
            selector.addChoosableFileFilter(filtro);
            // especificar que solo se puedan abrir archivos
            selector.setFileSelectionMode(JFileChooser.FILES_ONLY);

            //ejecutar el selector de imagenes
            int res = selector.showOpenDialog(null);

            if (res == 1) {

                return null;

            }

            File archivo = selector.getSelectedFile();

            BufferedImage bi = ImageIO.read(archivo);

            return toImage(bi);
        } catch (IOException ex) {
            Logger.getLogger(HerramientasImagen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public static Image toImage(BufferedImage bi) {
        return bi.getScaledInstance(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB);
    }

    public static BufferedImage toBufferedImage(Image imagen) {
        // imagen es un objeto de tipo BufferedImage
        if (imagen instanceof BufferedImage) {
            return (BufferedImage) imagen;
        }
        BufferedImage bi
                = new BufferedImage(imagen.getWidth(null), imagen.getHeight(null), BufferedImage.TYPE_INT_RGB);

        Graphics2D nueva = bi.createGraphics();
        nueva.drawImage(imagen, 0, 0, null);
        nueva.dispose();

        return bi;
    }

    public static Image copiarImagen(Image i) {
        BufferedImage bi = toBufferedImage(i);
        return bi.getScaledInstance(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB);
    }

    public static Image modificarIluminacion(Image imagenOriginal, int valor) {

        BufferedImage bi = herramientas.HerramientasImagen.toBufferedImage(imagenOriginal);
        Color color;
        int i, j, aux;
        for (j = 0; j < bi.getHeight(); j++) {
            for (i = 0; i < bi.getWidth(); i++) {
                color = new Color(bi.getRGB(i, j));
                int r = color.getRed() + valor;
                int g = color.getGreen() + valor;
                int b = color.getBlue() + valor;

                color = new Color(verificar(r), verificar(g), verificar(b));
                bi.setRGB(i, j, color.getRGB());
            }
        }
        return herramientas.HerramientasImagen.toImage(bi);
    }

    public static Image expansionlineal(Image imagenOriginal, int r1, int r2) {

        BufferedImage bi = herramientas.HerramientasImagen.toBufferedImage(imagenOriginal);
        Color color;
        int i, j, aux;
        for (j = 0; j < bi.getHeight(); j++) {
            for (i = 0; i < bi.getWidth(); i++) {
                color = new Color(bi.getRGB(i, j));
                int r = (color.getRed() - r1) * (255 / r2 - r1);
                int g = (color.getGreen() - r1) * (255 / r2 - r1);
                int b = (color.getBlue() - r1) * (255 / r2 - r1);

                color = new Color(verificar(r), verificar(g), verificar(b));
                bi.setRGB(i, j, color.getRGB());
            }
        }
        return herramientas.HerramientasImagen.toImage(bi);
    }

    public static Image expansionexponencial(Image imagenOriginal, double z) {

        BufferedImage bi = herramientas.HerramientasImagen.toBufferedImage(imagenOriginal);
        Color color;
        int i, j, aux;
        for (j = 0; j < bi.getHeight(); j++) {
            for (i = 0; i < bi.getWidth(); i++) {
                color = new Color(bi.getRGB(i, j));
                int r = (int) (Math.pow(1 + z, color.getRed()) / z);
                int g = (int) (Math.pow(1 + z, color.getGreen()) / z);
                int b = (int) (Math.pow(1 + z, color.getBlue()) / z);

                color = new Color(verificar(r), verificar(g), verificar(b));
                bi.setRGB(i, j, color.getRGB());
            }
        }
        return herramientas.HerramientasImagen.toImage(bi);
    }

    public static Image expansionlog(Image imagenOriginal, double z) {

        BufferedImage bi = herramientas.HerramientasImagen.toBufferedImage(imagenOriginal);
        Color color;
        int i, j, aux;
        for (j = 0; j < bi.getHeight(); j++) {
            for (i = 0; i < bi.getWidth(); i++) {
                color = new Color(bi.getRGB(i, j));
                int r = (int) ((z * Math.log(1 + color.getRed())) / (Math.log(1 + z)));
                int g = (int) ((z * Math.log(1 + color.getGreen())) / (Math.log(1 + z)));
                int b = (int) ((z * Math.log(1 + color.getBlue())) / (Math.log(1 + z)));

                color = new Color(verificar(r), verificar(g), verificar(b));
                bi.setRGB(i, j, color.getRGB());
            }
        }
        return herramientas.HerramientasImagen.toImage(bi);
    }

    public static Image ecualizacion(double[]histograma, Image imagenOriginal, int z){
      int nxm = imagenOriginal.getHeight(null)*imagenOriginal.getWidth(null);
      int[] ecualizacion= new int[256];
      for(int g=0; g<z+1;g++){
          double tmp = 0;
        for(int i=0; i <=g;i++)
            tmp+=histograma[i];
            tmp/=nxm;
        ecualizacion[g]= (int)Math.round(tmp*z);
      }
        BufferedImage bio = herramientas.HerramientasImagen.toBufferedImage(imagenOriginal);
        BufferedImage nueva = new BufferedImage(imagenOriginal.getWidth(null)
                                  , imagenOriginal.getHeight(null),BufferedImage.TYPE_INT_RGB);
        for(int y=0; y<imagenOriginal.getHeight(null);y++)
            for(int x=0; x<imagenOriginal.getWidth(null);x++){
          
             Color color = new Color(bio.getRGB(x, y));
             int v = ecualizacion[color.getRed()];
             color = new Color(v, v, v);
             nueva.setRGB(x, y, color.getRGB());
            }
            
        return herramientas.HerramientasImagen.toImage(nueva);
    }
    
    public static int verificar(int valor) {
        if (valor > 255) {
            return 255;
        }
        if (valor < 0) {
            return 0;
        }
        return valor;
    }
}
