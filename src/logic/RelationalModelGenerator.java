/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import domain.EntitySets;
import domain.JsonFile;
import java.util.LinkedList;
import static proyecto1basesdedatos.FXMLDocumentController.jsonFile;

/**
 *
 * @author Wilmata
 */
public class RelationalModelGenerator {
    
    private String relationalModelText;
    
    public void entityGenerator(){
        JsonFile jsonFileObject = jsonFile;
        LinkedList <EntitySets> entitySetList =  jsonFileObject.getEntitySets();
        
        for (int i = 0; i < entitySetList.size(); i++) {
            
        }
    }
    
}
