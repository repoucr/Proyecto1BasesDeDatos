/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import domain.Attributes;
import domain.Component;
import domain.EntitySets;
import domain.JsonFile;
import domain.Table;
import java.util.LinkedList;
import static proyecto1basesdedatos.FXMLDocumentController.jsonFile;

/**
 *
 * @author Wilmata
 */
public class RelationalModelGenerator {

    private String relationalModelText = "";

    public void entityGenerator() {
        JsonFile jsonFileObject = jsonFile;
        LinkedList<Table> tableList = new LinkedList<>();
        LinkedList<EntitySets> entitySetList = jsonFileObject.getEntitySets();
        for (int i = 0; i < entitySetList.size(); i++) {
            EntitySets tempEntitySets = entitySetList.get(i);
            if (tempEntitySets.getType().equalsIgnoreCase("Strong")) {
                Table tempTable = new Table();

                tempTable.setName(tempEntitySets.getName());
                tempTable.setTableContent("");
                tempTable.setTableContent(tempTable.getTableContent() + "CREATE TABLE " + tempEntitySets.getName() + "( \n");
                LinkedList<Attributes> attributesList = tempEntitySets.getAttributes();
                LinkedList<String> attributesTableList = new LinkedList<>();
                for (int j = 0; j < attributesList.size(); j++) {

                    Attributes tempAttributes = attributesList.get(j);
                    if (tempAttributes.getType().equalsIgnoreCase("Simple")) {
                        attributesTableList.add(tempAttributes.getName());
                        tempTable.setTableContent(tempTable.getTableContent() + tempAttributes.getName() + " " + tempAttributes.getDomain() + "(" + tempAttributes.getPrecision() + ") \n");
                        if (tempAttributes.isIsPrimary() == true) {
                            tempTable.setTableContent(tempTable.getTableContent() + "PRIMARY KEY (" + tempAttributes.getName() + ")\n");
                        }
                    } else if (tempAttributes.getType().equalsIgnoreCase("Composed")) {
                        LinkedList<Component> componentList = tempAttributes.getComponentList();
                        for (int k = 0; k < componentList.size(); k++) {
                            Component tempComponent = componentList.get(k);
                            attributesTableList.add(tempComponent.getName());
                            tempTable.setTableContent(tempTable.getTableContent() + tempComponent.getName() + " " + tempComponent.getDomain() + "(" + tempComponent.getPrecision() + ") \n");
                            if (tempComponent.isIsPrimary() == true) {
                                tempTable.setTableContent(tempTable.getTableContent() + "PRIMARY KEY ( " + tempComponent.getName() + " )\n");
                            }
                        }
                    }
                }
                tempTable.setAttributes(attributesTableList);
                tempTable.setTableContent(tempTable.getTableContent() + " );");
                tableList.add(tempTable);
            }
        }
        for (int i = 0; i < tableList.size(); i++) {
            System.out.println(tableList.get(i).getTableContent());
        }
    }
}
