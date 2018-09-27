/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;
import de.svenjacobs.loremipsum.LoremIpsum;
/**
 *
 * @author Wilmata
 */
public class Random {
    
    private LoremIpsum loremIpsum;
    
  public Random() {
      
    this.loremIpsum = new LoremIpsum();
  }
  
  public String generaPalabras(){
      
      String texto = loremIpsum.getWords(1500);
    
      int  inicioTexto = (int) (Math.random()*1500);
      int finalAleatorio = (int) (Math.random() * 7) +4;
      
      String aleatorio = texto.substring(inicioTexto, inicioTexto + finalAleatorio);
      
      return aleatorio;
  }
  
  

}
