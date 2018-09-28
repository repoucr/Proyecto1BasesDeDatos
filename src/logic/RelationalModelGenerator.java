/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import domain.Attributes;
import domain.EntitySets;
import domain.JsonFile;
import java.util.LinkedList;
import static proyecto1basesdedatos.FXMLDocumentController.jsonFile;

/**
 *
 * @author Wilmata
 */
public class RelationalModelGenerator {
    
    private String relationalModelText = "";
    
    public void entityGenerator(){
        JsonFile jsonFileObject = jsonFile;
        LinkedList <EntitySets> entitySetList =  jsonFileObject.getEntitySets();
        
        for (int i = 0; i < entitySetList.size(); i++) {
            EntitySets tempEntitySets = entitySetList.get(i);
            relationalModelText += "CREATE TABLE " + tempEntitySets.getName()+"( \n" ;
            LinkedList<Attributes> attributesList = tempEntitySets.getAttributes();
            for (int j = 0; j < attributesList.size(); j++) {
                Attributes tempAttributes = attributesList.get(j);
                relationalModelText += tempAttributes.getName() + " " + tempAttributes.getDomain() + "( " +tempAttributes.getPrecision() + " ) \n";
            }
            relationalModelText += " ) \n";
        }
          System.out.println(relationalModelText);
    }
    
}
