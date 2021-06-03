/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herramientas;

import gui.JFramePrincipal;
import gui.JInternalFrameFiltrado;
import gui.JInternalFrameImagen;
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

    private static Image imagenOriginal;
    private static double kernel[][];

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

    public static Image ecualizacion(double[] histograma, Image imagenOriginal, int z) {
        int nxm = imagenOriginal.getHeight(null) * imagenOriginal.getWidth(null);
        int[] ecualizacion = new int[256];
        for (int g = 0; g < z + 1; g++) {
            double tmp = 0;
            for (int i = 0; i <= g; i++) {
                tmp += histograma[i];
            }
            tmp /= nxm;
            ecualizacion[g] = (int) Math.round(tmp * z);
        }
        BufferedImage bio = herramientas.HerramientasImagen.toBufferedImage(imagenOriginal);
        BufferedImage nueva = new BufferedImage(imagenOriginal.getWidth(null),
                imagenOriginal.getHeight(null), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < imagenOriginal.getHeight(null); y++) {
            for (int x = 0; x < imagenOriginal.getWidth(null); x++) {

                Color color = new Color(bio.getRGB(x, y));
                int v = ecualizacion[color.getRed()];
                color = new Color(v, v, v);
                nueva.setRGB(x, y, color.getRGB());
            }
        }

        return herramientas.HerramientasImagen.toImage(nueva);
    }

    public static Image aplicarConvolucion(Image io, int[][] mascara, int div, int offset) {

        BufferedImage bi = HerramientasImagen.toBufferedImage(io);
        BufferedImage bnuevo = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB);
        // recorres el buffer
        for (int x = 0; x < bi.getWidth(); x++) {
            for (int y = 0; y < bi.getHeight(); y++) {
                int rgb = calcularNuevoTono(x, y, bi, mascara, div, offset);
                bnuevo.setRGB(x, y, rgb);
            }
        }
        return HerramientasImagen.toBufferedImage(bnuevo);
    }

    private static int calcularNuevoTono(int x, int y, BufferedImage bi, int[][] mascara, int div, int offset) {

        // recorrer la mascara
        // int r = x -1;
        //int c = y -1;
        int auxR = 0, auxG = 0, auxB = 0;
        Color color = null;
        int k = 0;
        int auxr;
        int auxc;

        if (mascara.length == 3) {
            auxr = x - 1;
            auxc = y - 1;

        } else {
            auxr = x - 2;
            auxc = y - 2;
        }

        for (int i = 0, r = auxr; i < mascara.length; i++, r++) {
            for (int j = 0, c = auxc; j < mascara[0].length; j++, c++) {
                // todo: quitar el if
                if (mascara[i][j] != 0) {
                    try {
                        int rgb = bi.getRGB(r, c);
                        k++;
                        color = new Color(rgb);
                        // acomodar la multiplicación
                        auxR += color.getRed() * mascara[i][j];
                        auxG += color.getGreen() * mascara[i][j];
                        auxB += color.getBlue() * mascara[i][j];

                    } catch (Exception e) {
                        // nada de nada 
                    }
                }
            }
        }
        // quitar k
        // quitar el if
        if (k != 0) {
            auxR /= div;
            auxG /= div;
            auxB /= div;
        }
        color = new Color(verificar(auxR + offset), verificar(auxG + offset), verificar(auxB + offset));
        return color.getRGB();
    }

    public static Image aplicarKirsch(int divisor, Image io) {
        HerramientasImagen.kernel = new double[3][3];
        HerramientasImagen.imagenOriginal = io;
        BufferedImage nueva = new BufferedImage(HerramientasImagen.imagenOriginal.getWidth(null), HerramientasImagen.imagenOriginal.getHeight(null), BufferedImage.TYPE_INT_RGB);
        BufferedImage bi = herramientas.HerramientasImagen.toBufferedImage(imagenOriginal);

        //System.out.println("W:"+nueva.getWidth()+"H:"+nueva.getHeight());
        //proceso iterativo para generar un imagen nueva
        for (int x = 0; x < HerramientasImagen.imagenOriginal.getWidth(null); x++) {
            for (int y = 0; y < HerramientasImagen.imagenOriginal.getHeight(null); y++) {
                double muestra[][] = extraerMuestra(x, y, bi);
                //System.out.println(x+","+y);
                // validar que la muestra se generó 
                if (muestra != null) {
                    // proceso evolutivo para Kirsch
                    // recorremos las mascaras     

                    // Color colorRes = convulacionar(kernel,muestra,divisor);
                    Color colorRes = convolucionarKirsch(muestra, divisor);

                    nueva.setRGB(x, y, colorRes.getRGB());

                } else {
                    nueva.setRGB(x, y, new Color(255, 255, 255).getRGB());

                }

            }
        }

        return herramientas.HerramientasImagen.toImage(nueva);
    }

    public static Color convulacionar(double[][] kernel, double[][] muestra, int divisor) {
        int acumuladorR = 0;
        int acumuladorG = 0;
        int acumuladorB = 0;
        Color aux;
        // recorremos el kernel y la muestra 
        for (int x = 0; x < kernel[0].length; x++) {
            for (int y = 0; y < kernel[0].length; y++) {
                aux = new Color((int) muestra[x][y]);
                acumuladorR += (kernel[x][y] * aux.getRed());
                acumuladorG += (kernel[x][y] * aux.getGreen());
                acumuladorB += (kernel[x][y] * aux.getBlue());

            }
        }
        acumuladorR /= divisor;
        acumuladorG /= divisor;
        acumuladorB /= divisor;

        return new Color(verificar(acumuladorR), verificar(acumuladorG), verificar(acumuladorB));

    }

    private static double[][] extraerMuestra(int x, int y, BufferedImage bi) {
        double matriz[][] = new double[HerramientasImagen.kernel[0].length][HerramientasImagen.kernel[0].length];
        int xx = 0;
        int yy = 0;
        try {
            // recorremos la imagen 
            for (int i = x - (HerramientasImagen.kernel[0].length - 1) / 2; i <= x + (HerramientasImagen.kernel[0].length - 1) / 2; i++) {
                for (int j = y - (HerramientasImagen.kernel[0].length - 1) / 2; j <= y + (HerramientasImagen.kernel[0].length - 1) / 2; j++) {
                    matriz[xx][yy] = bi.getRGB(i, j);
                    yy++;
                }
                yy = 0;
                xx++;
            }
            return matriz;
        } catch (Exception e) {
            // System.out.println("Indice no valido");
            return null;
        }

    }

    private static Color convolucionarKirsch(double[][] muestra, int divisor) {
        // recorremos las mascaras 
        int mayorR = -1;
        int mayorG = -1;
        int mayorB = -1;
        int r, g, b;
        for (int i = 0; i < 8; i++) {
            Color color = convulacionar(herramientas.MascarasBordes.arregloMascaras[i], muestra, divisor);
            r = color.getRed();
            g = color.getGreen();
            b = color.getBlue();
            if (r > mayorR) {
                mayorR = r;
            }
            if (g > mayorG) {
                mayorG = g;
            }
            if (b > mayorB) {
                mayorB = b;
            }

        }
        return new Color(verificar(mayorR), verificar(mayorG), verificar(mayorB));

    }

    public static Image aplicarsal(int porcentaje, boolean pimienta, boolean sal, Image imagenOriginal) {

        BufferedImage bi = herramientas.HerramientasImagen.toBufferedImage(imagenOriginal);
        Color color;
        int modpix = bi.getWidth() * bi.getHeight() * porcentaje / 100;
        int aux = 0;

        if (pimienta == true && sal == false) {
            while (aux <= modpix) {
                color = new Color(0, 0, 0);
                bi.setRGB((int) (Math.random() * bi.getWidth()) + 0, (int) (Math.random() * bi.getHeight()) + 0, color.getRGB());
                aux++;
            }
        } else if (pimienta == false && sal == true) {
            while (aux <= modpix) {
                color = new Color(255, 255, 255);
                bi.setRGB((int) (Math.random() * bi.getWidth()) + 0, (int) (Math.random() * bi.getHeight()) + 0, color.getRGB());
                aux++;
            }
        } else if (pimienta == true && sal == true) {
            while (aux <= modpix / 2) {
                color = new Color(255, 255, 255);
                bi.setRGB((int) (Math.random() * bi.getWidth()) + 0, (int) (Math.random() * bi.getHeight()) + 0, color.getRGB());
                aux++;
            }
            aux = 0;
            while (aux <= modpix / 2) {
                color = new Color(0, 0, 0);
                bi.setRGB((int) (Math.random() * bi.getWidth()) + 0, (int) (Math.random() * bi.getHeight()) + 0, color.getRGB());
                aux++;
            }
        }

        return herramientas.HerramientasImagen.toImage(bi);
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

    public static Image MaxMin(Image io) {

        BufferedImage bi = HerramientasImagen.toBufferedImage(io);
        BufferedImage bnuevo = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB);
        // recorres el buffer
        for (int x = 0; x < bi.getWidth(); x++) {
            for (int y = 0; y < bi.getHeight(); y++) {
                if (bi.getRGB(x, y) == new Color(0, 0, 0).getRGB()) {
                    int rgb = Maximizar(x, y, bi);
                    bnuevo.setRGB(x, y, rgb);
                } else if (bi.getRGB(x, y) == new Color(255, 255, 255).getRGB()) {
                    int rgb = Minimizar(x, y, bi);
                    bnuevo.setRGB(x, y, rgb);
                }else{
                 bnuevo.setRGB(x, y, bi.getRGB(x, y));
                }
            }
        }
        return HerramientasImagen.toBufferedImage(bnuevo);
    }

    private static int Maximizar(int x, int y, BufferedImage bi) {

               
        Color MaxPixel = new Color(bi.getRGB(x, y));
        int MauxR =  MaxPixel.getRed(),
            MauxG = MaxPixel.getGreen(),
            MauxB = MaxPixel.getBlue();
        
        
        Color color = null;

        int auxr;
        int auxc;

        auxr = x - 2;
        auxc = y - 2;

        for (int i = 0, r = auxr; i < 5; i++, r++) {
            for (int j = 0, c = auxc; j < 5; j++, c++) {
                // todo: quitar el if
              
               try {
                        int rgb = bi.getRGB(r, c);
                        color = new Color(rgb);
                       
                        // acomodar la multiplicación
                        if(MauxR<color.getRed()){
                        MauxR = color.getRed();
                        }
                        if(MauxG<color.getGreen()){
                        MauxG =color.getGreen();
                        }
                        if(MauxB<color.getBlue()){
                        MauxB = color.getBlue();
                        }

                    } catch (Exception e) {
                        // nada de nada 
                    }
                
               
                
            }
        }
        // quitar k
      
        color = new Color(verificar(MauxR), verificar(MauxG), verificar(MauxB));
        return color.getRGB();
    }

    
    private static int Minimizar(int x, int y, BufferedImage bi) {

         Color MaxPixel = new Color(bi.getRGB(x, y));
        int MauxR =  MaxPixel.getRed(),
            MauxG = MaxPixel.getGreen(),
            MauxB = MaxPixel.getBlue();
        
        
        Color color = null;

        int auxr;
        int auxc;

        auxr = x - 2;
        auxc = y - 2;

        for (int i = 0, r = auxr; i < 5; i++, r++) {
            for (int j = 0, c = auxc; j < 5; j++, c++) {
                // todo: quitar el if
                
               try {
                        int rgb = bi.getRGB(r, c);
                        color = new Color(rgb);
                       
                        // acomodar la multiplicación
                        if(MauxR>color.getRed()){
                        MauxR = color.getRed();
                        }
                        if(MauxG>color.getGreen()){
                        MauxG =color.getGreen();
                        }
                        if(MauxB>color.getBlue()){
                        MauxB = color.getBlue();
                        }

                    } catch (Exception e) {
                        // nada de nada 
                    }
                        
                
            }
        }
        // quitar k
      
        color = new Color(verificar(MauxR), verificar(MauxG), verificar(MauxB));
        return color.getRGB();
    }
    
    
    public static Image FrecuenciasGrises(Image io, JFramePrincipal j) {
        BufferedImage bi = HerramientasImagen.toBufferedImage(io);
        BufferedImage bnuevo = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB);
        // recorres el buffer
        
        
        
        GestorGrises g = new GestorGrises (bi);
        
            bnuevo = g.obtenerImagenFrecuencias(true);
            JInternalFrameImagen internalNuevo2 = new JInternalFrameImagen(bnuevo);
            internalNuevo2.setVisible(true);
            j.getjDesktopPanePrincipal().add(internalNuevo2);
            
            
            JInternalFrameFiltrado Filtro = new JInternalFrameFiltrado(j,io,bi.getHeight(),g);
            Filtro.setVisible(true);
            j.getjDesktopPanePrincipal().add(Filtro);
            
            
           //esto es la inversa no importa ahorita 
//            bnuevo = g.obtenerImagenEspacial();
//            JInternalFrameImagen internalNuevo23 = new JInternalFrameImagen(bnuevo);
//            internalNuevo23.setVisible(true);
//            j.getjDesktopPanePrincipal().add(internalNuevo23);
            
         return HerramientasImagen.toBufferedImage(bnuevo);
    }
    
   
    
}
