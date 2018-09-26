/*}
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import domain.Attributes;
import domain.EntitySets;
import domain.ParticipationEntities;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author fabian
 */
public class JsonAdmin {
    
    public void readJson(File json) throws ParseException, FileNotFoundException, IOException{
        ArrayList<EntitySets> entitySetsList = new ArrayList();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(json));
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray jsonArray = (JSONArray) jsonObject.get("EntitySets");  
        for (int i = 0; i <jsonArray.size(); i++) {
            LinkedList<Attributes> attributesList = new LinkedList();
            EntitySets tempEntitySets = new EntitySets();
            JSONObject tempJsonObject = (JSONObject) jsonArray.get(i);
            tempEntitySets.setName(tempJsonObject.get("Name").toString());
            tempEntitySets.setParentEntitySet(tempJsonObject.get("ParentEntitySet").toString());
            tempEntitySets.setType(tempJsonObject.get("Type").toString());
            System.out.println(tempJsonObject.get("Type").toString());
            JSONArray jsonArrayAttributes = (JSONArray) jsonObject.get("EntitySets");
            
            for (int j = 0; j <jsonArrayAttributes.size(); j++) {
                LinkedList<String> componentList = new LinkedList();
                JSONObject tempJsonObjectAttributes = (JSONObject) jsonArray.get(i);
                Attributes tempAttribute = new Attributes();
                tempAttribute.setName(tempJsonObjectAttributes.get("Name").toString());
                tempAttribute.setDomain(tempJsonObjectAttributes.get("Domain").toString());
                tempAttribute.setType(tempJsonObjectAttributes.get("Type").toString());
                JSONArray jsonArrayComponents = (JSONArray) jsonObject.get("ComponentList");
               
                for (int k = 0; k <jsonArrayComponents.size(); k++) {
                    componentList.add(jsonArray.get(i).toString());
                }
                tempAttribute.setComponentList(componentList);
                tempAttribute.setIsPrimary((Boolean) tempJsonObjectAttributes.get("IsPrimary"));
                tempAttribute.setIsDiscriminator((Boolean) tempJsonObjectAttributes.get("IsDiscriminator"));
                tempAttribute.setPrecision((int)tempJsonObjectAttributes.get("Precision"));
                attributesList.add(tempAttribute);
            }
            
        tempEntitySets.setAttributes(attributesList);
        entitySetsList.add(tempEntitySets);
        }
        
        ArrayList<ParticipationEntities> participationEntitiesList = new ArrayList();
        JSONParser parserParticipationEntities = new JSONParser();
        Object objParticipationEntities = parserParticipationEntities.parse(new FileReader(json));
        JSONObject jsonObjectParticipationEntities = (JSONObject) objParticipationEntities;
        JSONArray jsonArrayParticipationEntities = (JSONArray) jsonObject.get("ParticipationEntities");  
        for (int i = 0; i < jsonArrayParticipationEntities.size(); i++) {
            
        }
        
    }  
}
