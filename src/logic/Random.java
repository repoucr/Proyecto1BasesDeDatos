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
  
  public void generaPalabras(){
      
      String aleatorio = loremIpsum.getWords( 50 );
      
      System.out.println(aleatorio);
  }
  
  

}
