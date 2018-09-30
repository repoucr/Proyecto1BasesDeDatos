package logic;

import domain.Attributes;
import domain.Component;
import domain.DescriptiveAttributes;
import domain.EntitySets;
import domain.JsonFile;
import domain.RelationshipSets;
import domain.Table;
import domain.TableAttributes;
import java.util.LinkedList;
import static proyecto1basesdedatos.FXMLDocumentController.jsonFile;

public class RelationalModelGenerator {

    private String relationalModelText = "";

    public void entityGenerator() {
        JsonFile jsonFileObject = jsonFile;
        LinkedList<Table> tableList = new LinkedList<>();
        LinkedList<EntitySets> entitySetList = jsonFileObject.getEntitySets();
        LinkedList<RelationshipSets> relationSetLists = jsonFileObject.getRelationshipSets();
        for (int i = 0; i < entitySetList.size(); i++) {
            EntitySets tempEntitySets = entitySetList.get(i);

            Table tempTable = new Table();
            tempTable.setType(tempEntitySets.getType());
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
                    attributesTableList.add(tempTableAttributes);
                    tempTable.setTableContent(tempTable.getTableContent() + tempAttributes.getName() + " " + tempAttributes.getDomain() + "(" + tempAttributes.getPrecision() + ") \n");
                    if (tempAttributes.isIsPrimary() == true) {
                        tempTable.setTableContent(tempTable.getTableContent() + "PRIMARY KEY (" + tempAttributes.getName() + ")\n");
                    }
                } else if (tempAttributes.getType().equalsIgnoreCase("Composed")) {
                    LinkedList<Component> componentList = tempAttributes.getComponentList();
                    for (int k = 0; k < componentList.size(); k++) {
                        Component tempComponent = componentList.get(k);
                        TableAttributes tempTableAttributes = new TableAttributes();
                        tempTableAttributes.setName(tempComponent.getName());
                        tempTableAttributes.setDomain(tempComponent.getDomain());
                        attributesTableList.add(tempTableAttributes);
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
        for (int i = 0; i < relationSetLists.size(); i++) {
            RelationshipSets tempRelationshipSets = relationSetLists.get(i);
            if (tempRelationshipSets.getType().equalsIgnoreCase("Strong")) {
                Table tempTable = new Table();
                tempTable.setName(tempRelationshipSets.getName());
                tempTable.setType(tempRelationshipSets.getType());
                LinkedList<DescriptiveAttributes> tempDescripAttribut = tempRelationshipSets.getDescriptiveAttributes();
                LinkedList<TableAttributes> attributesList = new LinkedList<>();
                if (tempDescripAttribut != null) {
                    for (int j = 0; j < tempDescripAttribut.size(); j++) {
                        DescriptiveAttributes auxDescriptiveAttributes = tempDescripAttribut.get(j);
                        if (auxDescriptiveAttributes.getType().equalsIgnoreCase("Simple")) {
                            TableAttributes auxTableAttributes = new TableAttributes();
                            auxTableAttributes.setName(auxDescriptiveAttributes.getName());
                            auxTableAttributes.setDomain(auxDescriptiveAttributes.getDomain());
                            auxTableAttributes.setIsPrimary(auxDescriptiveAttributes.isIsPrimary());
                            attributesList.add(auxTableAttributes);

                            tempTable.setTableContent(tempTable.getTableContent() + auxDescriptiveAttributes.getName() + " " + auxDescriptiveAttributes.getDomain() + "(" + auxDescriptiveAttributes.getPrecision() + ") \n");

                            if (auxDescriptiveAttributes.isIsPrimary() == true) {
                                tempTable.setTableContent(tempTable.getTableContent() + "PRIMARY KEY ( " + auxDescriptiveAttributes.getName() + " )\n");
                            }
                        }
                    }
                }

                tempTable.setAttributes(attributesList);
                tempTable.setTableContent(tempTable.getTableContent() + " );");
                tableList.add(tempTable);
            }

        }
        for (int i = 0; i < tableList.size(); i++) {
            System.out.println(tableList.get(i).getTableContent());
        }
    }
}