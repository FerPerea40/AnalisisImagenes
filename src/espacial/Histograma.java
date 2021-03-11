/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espacial;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import org.jfree.chart.plot.XYPlot;

/**
 *
 * @author working
 */
public class Histograma {
    
    private Image imagenOriginal;
    private int[]r;
    private int[]g;
    private int[]b;

    public Histograma(Image imagen) {
        this.imagenOriginal = imagen;
        this.r = new int[256];
        this.g = new int[256];
        this.b = new int[256];
    }
    public void calcularHistogramas(){
        // recorrer la imagen
        BufferedImage bi = herramientas.HerramientasImagen.toBufferedImage(imagenOriginal);
        Color color;
        for(int x=0; x<bi.getWidth();x++)
            for(int y=0; y< bi.getHeight();y++){
                // extraer el color
                color = new Color(bi.getRGB(x, y));
                int r = color.getRed(); // (0-255)
                int g = color.getGreen(); // (0-255)
                int b = color.getBlue(); // (0-255)
                this.r[r]++;
                this.g[g]++;
                this.b[b]++;
                
            }
        
    }
       
    
    public void graficar (){
      Grafica graph = new Grafica("Magnitud","Frecuencia","Histograma de imagen");
        graph.crearSerie("Rojo", r);
        graph.crearSerie("Azul", b);
        graph.crearSerie("Verde",g);
        
        graph.mostrarGrafica();

     /*   XYPlot plot = graph.getGrafica().getXYPlot();
        plot.getRenderer().setSeriesPaint(0, new Color(Color.RED.getRGB()));
        plot.getRenderer().setSeriesPaint(1, new Color(Color.BLUE.getRGB()));
        plot.getRenderer().setSeriesPaint(2, new Color(Color.GREEN.getRGB()));
        graph.muestraGrafica();*/
    }

    /**
     * @return the r
     */
    public int[] getR() {
        return r;
    }

    /**
     * @return the g
     */
    public int[] getG() {
        return g;
    }

    /**
     * @return the b
     */
    public int[] getB() {
        return b;
    }
    
}
