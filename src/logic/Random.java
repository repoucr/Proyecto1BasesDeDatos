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

    public String wordGenerator(int size) {

        String text = loremIpsum.getWords(1500);

        int startText = (int) (Math.random() * 1000);

        String randomText = "'"+text.substring(startText, startText + size)+"'";

        return randomText;
    }

    public String numberGenerator(int size){
    
        String randomNumber="";
        
        for (int i = 0; i < size; i++) {
            randomNumber+= (int) (Math.random() * 10);
        }
        return randomNumber;
    }
    
    public String stringGenerator(int size){
        System.out.println(size);
        String text="abcdefghijklmnopqrstuvwxyz";
        String randomString = "'" ;
        for (int i = 0; i < size; i++) {
            int random = (int) (Math.random() * 26);
            randomString+= text.substring(random,random+1);
        }
        return randomString+"'";
    }
}
