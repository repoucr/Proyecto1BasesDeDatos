package logic;

import domain.Attributes;
import domain.Component;
import domain.DescriptiveAttributes;
import domain.EntitySets;
import domain.JsonFile;
import domain.RelationshipSets;
import domain.Table;
import domain.TableAttributes;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import javafx.stage.FileChooser;
import static proyecto1basesdedatos.FXMLDocumentController.jsonFile;

public class RelationalModelGenerator {

    private String relationalModelText = "";

    public void entityGenerator() throws IOException {
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
            
            if (tempEntitySets.getParentEntitySet() != null) {
                for (int j = 0; j < tableList.size(); j++) {
                    if (tableList.get(j).getName().equalsIgnoreCase(tempEntitySets.getParentEntitySet())) {
                        Table auxTable = tableList.get(j);
                        LinkedList <TableAttributes> tempAttributesList = auxTable.getAttributes();
//                        System.out.println(tempAttributesList.size());
                        for (int k = 0; k < tempAttributesList.size(); k++) {
                            if (tempAttributesList.get(k).getIsPrimary()==true) {
                                
                                TableAttributes auxTableAttributes = new TableAttributes();
                                auxTableAttributes.setName(tempAttributesList.get(k).getName());
                                auxTableAttributes.setDomain(tempAttributesList.get(k).getDomain());
                                auxTableAttributes.setPresicion(tempAttributesList.get(k).getPresicion());
                                auxTableAttributes.setIsPrimary(true);
                                auxTableAttributes.setIsForeign(true);
//                               

//                                LinkedList<TableAttributes> tempTableAttributes = auxTable.getAttributes();
//                                tempTableAttributes.add(auxTableAttributes);
//                                tempTable.setAttributes(tempTableAttributes);
                                tempTable.setTableContent( tempTable.getTableContent() + tempAttributesList.get(k).getName() + " " + tempAttributesList.get(k).getDomain() + "(" + tempAttributesList.get(k).getPresicion() + ") \n");
                                tempTable.setTableContent( tempTable.getTableContent() + "PRIMARY KEY (" + tempAttributesList.get(k).getName() + ")\n");
                                
                                tempTable.setTableContent( tempTable.getTableContent() + "FOREIGN KEY (" + tempEntitySets.getParentEntitySet() + ")\n" ) ;
                            }
                        }                        
                    }
                }
            }
            for (int j = 0; j < attributesList.size(); j++) {

                Attributes tempAttributes = attributesList.get(j);
                if (tempAttributes.getType().equalsIgnoreCase("Simple")) {
                    TableAttributes tempTableAttributes = new TableAttributes();
                    tempTableAttributes.setName(tempAttributes.getName());
                    tempTableAttributes.setDomain(tempAttributes.getDomain());
                    tempTableAttributes.setPresicion(tempAttributes.getPrecision());
                    
                    
                    tempTable.setTableContent(tempTable.getTableContent() + tempAttributes.getName() + " " + tempAttributes.getDomain() + "(" + tempAttributes.getPrecision() + ") \n");
                    if (tempAttributes.getIsPrimary() == true) {
                        tempTableAttributes.setIsPrimary(true);
                        tempTable.setTableContent(tempTable.getTableContent() + "PRIMARY KEY (" + tempAttributes.getName() + ")\n");
                    }
                    attributesTableList.add(tempTableAttributes);
                } else if (tempAttributes.getType().equalsIgnoreCase("Composed")) {
                    LinkedList<Component> componentList = tempAttributes.getComponentList();
                    for (int k = 0; k < componentList.size(); k++) {
                        Component tempComponent = componentList.get(k);
                        TableAttributes tempTableAttributes = new TableAttributes();
                        tempTableAttributes.setName(tempComponent.getName());
                        tempTableAttributes.setDomain(tempComponent.getDomain());
                        tempTableAttributes.setPresicion(tempComponent.getPrecision());
                        attributesTableList.add(tempTableAttributes);
                        tempTable.setTableContent(tempTable.getTableContent() + tempComponent.getName() + " " + tempComponent.getDomain() + "(" + tempComponent.getPrecision() + ") \n");
                        if (tempComponent.getIsPrimary() == true) {
                            tempTable.setTableContent(tempTable.getTableContent() + "PRIMARY KEY ( " + tempComponent.getName() + " )\n");
                        }
                    }
                }
            }
            tempTable.setAttributes(attributesTableList);
            tempTable.setTableContent(tempTable.getTableContent() + " );\n\n\n");
            tableList.add(tempTable);

        }
        for (int i = 0; i < relationSetLists.size(); i++) {
            RelationshipSets tempRelationshipSets = relationSetLists.get(i);
            if (tempRelationshipSets.getType().equalsIgnoreCase("Strong")) {
                Table tempTable = new Table();

                //no funciona el nombre
                tempTable.setName(tempRelationshipSets.getName());
                tempTable.setType(tempRelationshipSets.getType());
                tempTable.setTableContent("");
                tempTable.setTableContent(tempTable.getTableContent() + "CREATE TABLE " + tempRelationshipSets.getName() + "( \n");
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
                tempTable.setTableContent(tempTable.getTableContent() + " );\n\n\n");
                tableList.add(tempTable);
            }

        }
        for (int i = 0; i < tableList.size(); i++) {
//            System.out.println(tableList.get(i).getTableContent());
            relationalModelText += tableList.get(i).getTableContent();
        }

        FileChooser saveProyect = new FileChooser();
        saveProyect.getExtensionFilters().add(new FileChooser.ExtensionFilter("SQL (*.sql)", "*.sql"));
        File file = saveProyect.showSaveDialog(null);
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SQL (*.sql)", "*.sql");
        saveProyect.getExtensionFilters().add(extFilter);
        if (!file.getName().contains(".")) {
            file = new File(file.getAbsolutePath() + ".sql");
        }
        FileWriter fileWriter = null;
        fileWriter = new FileWriter(file);
        fileWriter.write(relationalModelText);
        fileWriter.close();
    }
}
