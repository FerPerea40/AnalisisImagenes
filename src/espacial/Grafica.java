/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espacial;

import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Dell
 */
public class Grafica {



    private String tituloGrafica;
    private String tituloEjeX,tituloEjeY;
    private JFreeChart grafica;
    private XYSeriesCollection series;

    public Grafica(String titulo,String tituloX,String tituloY) 
    {
        this.tituloGrafica = "Gráfica";
        this.tituloEjeX = tituloX;
        this.tituloEjeY = tituloY;
        
        this.series = new XYSeriesCollection();
    }
    
    public void crearSerie(String nombre,int[] datos)
    {
      
        XYSeries serie = new XYSeries(nombre);
        // agregar cada uno de los datos en la serie 
        for (int x=0; x < datos.length;x++){
            serie.add(x, datos[x]);
        }
        // agregamos la serie que se generÃ³ 
        this.series.addSeries(serie);
    }
    
    public void mostrarGrafica()
    {
    
        this.grafica = ChartFactory.createXYLineChart(this.tituloGrafica, tituloEjeX, tituloEjeY,
                    series);
        // crear un panel con la grafica
        ChartFrame panel = new ChartFrame(null, grafica);
        panel.pack();
        panel.setVisible(true);
        
    }
}