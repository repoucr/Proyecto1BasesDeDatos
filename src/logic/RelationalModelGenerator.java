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
import domain.TableAttributes;
import java.util.LinkedList;
import static proyecto1basesdedatos.FXMLDocumentController.jsonFile;

/**
 *
 * @author Wilmata
 */

public class RelationalModelGenerator {

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
                LinkedList<TableAttributes> attributesTableList = new LinkedList<>();
                for (int j = 0; j < attributesList.size(); j++) {

                    Attributes tempAttributes = attributesList.get(j);
                    if (tempAttributes.getType().equalsIgnoreCase("Simple")) {
                        TableAttributes tempTableAttributes = new TableAttributes();
                        tempTableAttributes.setName(tempAttributes.getName());
                        tempTableAttributes.setDomain(tempAttributes.getDomain());
                        tempTableAttributes.setIsPrimary(tempAttributes.getIsPrimary());
                        attributesTableList.add(tempTableAttributes);
                        tempTable.setTableContent(tempTable.getTableContent() + tempAttributes.getName() + " " + tempAttributes.getDomain() + "(" + tempAttributes.getPrecision() + ") \n");
                        if (tempAttributes.getIsPrimary() == true) {
                            tempTable.setTableContent(tempTable.getTableContent() + "PRIMARY KEY (" + tempAttributes.getName() + ")\n");
                        }
                    } else if (tempAttributes.getType().equalsIgnoreCase("Composed")) {
                        LinkedList<Component> componentList = tempAttributes.getComponentList();
                        for (int k = 0; k < componentList.size(); k++) {
                            Component tempComponent = componentList.get(k);
                            TableAttributes tempTableAttributes = new TableAttributes();
                            tempTableAttributes.setName(tempComponent.getName());
                            tempTableAttributes.setDomain(tempComponent.getDomain());
                            tempTableAttributes.setIsPrimary(tempComponent.getIsPrimary());
                            attributesTableList.add(tempTableAttributes);
                            tempTable.setTableContent(tempTable.getTableContent() + tempComponent.getName() + " " + tempComponent.getDomain() + "(" + tempComponent.getPrecision() + ") \n");
                            if (tempComponent.getIsPrimary() == true) {
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
