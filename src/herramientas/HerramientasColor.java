/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herramientas;

import java.awt.Color;

/**
 *
 * @author Dell
 */
public class HerramientasColor {
      public enum CanalColor {ROJO(2),VERDE(1),AZUL(0);
                
            private int index;
            
            private CanalColor(int index){
            this.index = index;
            }
            
            public int getColorIndex(){
            
            return index;
            }
    }
    public static int obtenerValorPorCanal(int rgb ){
    
        Color color = new Color(rgb);
        int valor = 0;
      
              valor = color.getRed();
              
           
       
       
       return valor;
        
    }
    
    
    
    public static int acumularColor(int color1, int color2 ){
     return color1 | color2;
    }
}
