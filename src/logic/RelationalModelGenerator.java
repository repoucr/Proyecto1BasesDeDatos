package logic;

import domain.Attributes;
import domain.Component;
import domain.DescriptiveAttributes;
import domain.EntitySets;
import domain.JsonFile;
import domain.ParticipationEntities;
import domain.RelationshipSets;
import domain.Table;
import domain.TableAttributes;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import javafx.stage.FileChooser;
import static proyecto1basesdedatos.FXMLDocumentController.jsonFile;

public class RelationalModelGenerator {

    private String relationalModelText = "";
    public static String message = "";

    public String entityGenerator() throws IOException {
        JsonFile jsonFileObject = jsonFile;
        Random random = new Random();
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
                        LinkedList<TableAttributes> tempAttributesList = auxTable.getAttributes();
                        for (int k = 0; k < tempAttributesList.size(); k++) {
                            if (tempAttributesList.get(k).getIsPrimary() == true) {
                                TableAttributes auxTableAttributes = new TableAttributes();
                                auxTableAttributes.setName(tempAttributesList.get(k).getName() + "_" + tempEntitySets.getParentEntitySet());
                                auxTableAttributes.setDomain(tempAttributesList.get(k).getDomain());
                                auxTableAttributes.setPresicion(tempAttributesList.get(k).getPresicion());
                                auxTableAttributes.setContent(tempAttributesList.get(k).getContent());
                                //auxTableAttributes.setIsPrimary(true);
                                auxTableAttributes.setIsForeign(true);
                                LinkedList<TableAttributes> tempTableAttributes = auxTable.getAttributes();
                                tempTableAttributes.add(auxTableAttributes);
                                tempTable.setAttributes(tempTableAttributes);
                                tempTable.setTableContent(tempTable.getTableContent() + auxTableAttributes.getName() + " " + auxTableAttributes.getDomain());
                                if (!auxTableAttributes.getDomain().equalsIgnoreCase("int") || !auxTableAttributes.getDomain().equalsIgnoreCase("datetime")) {
                                    tempTable.setTableContent(tempTable.getTableContent() + "(" + auxTableAttributes.getPresicion() + "), \n");
                                } else {
                                    tempTable.setTableContent(tempTable.getTableContent() + ",\n");
                                }
                                tempTable.setTableContent(tempTable.getTableContent() + "PRIMARY KEY (" + auxTableAttributes.getName() + "),\n");
                                tempTable.setTableContent(tempTable.getTableContent() + "FOREIGN KEY (" + auxTableAttributes.getName() + ") REFERENCES " + tempEntitySets.getParentEntitySet() + ",\n");
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

                    if (tempAttributes.getDomain().equalsIgnoreCase("int")) {
                        tempTableAttributes.setContent(random.numberGenerator(7));
                    } else if (tempAttributes.getDomain().equalsIgnoreCase("smallint")) {
                        tempTableAttributes.setContent(random.numberGenerator(4));
                    } else if (tempAttributes.getDomain().equalsIgnoreCase("numeric")) {
                        tempTableAttributes.setContent(random.numberGenerator(tempAttributes.getPrecision()));
                    } else if (tempAttributes.getDomain().equalsIgnoreCase("varchar")) {
                        tempTableAttributes.setContent(random.wordGenerator(tempAttributes.getPrecision() / 10));
                    } else if (tempAttributes.getDomain().equalsIgnoreCase("datetime")) {
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        tempTableAttributes.setContent(dtf.format(now));
                    }

                    tempTable.setTableContent(tempTable.getTableContent() + tempAttributes.getName() + " " + tempAttributes.getDomain());
                    if (!tempAttributes.getDomain().equalsIgnoreCase("int") || !tempAttributes.getDomain().equalsIgnoreCase("datetime")) {
                        tempTable.setTableContent(tempTable.getTableContent() + "(" + tempAttributes.getPrecision() + "), \n");
                    } else {
                        tempTable.setTableContent(tempTable.getTableContent() + ",\n");
                    }

                    if (tempAttributes.getIsPrimary() == true) {
                        tempTableAttributes.setIsPrimary(true);
                        tempTable.setTableContent(tempTable.getTableContent() + "PRIMARY KEY (" + tempAttributes.getName() + "),\n");
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

                        if (tempComponent.getDomain().equalsIgnoreCase("int")) {
                            tempTableAttributes.setContent(random.numberGenerator(7));
                        } else if (tempComponent.getDomain().equalsIgnoreCase("smallint")) {
                            tempTableAttributes.setContent(random.numberGenerator(4));
                        } else if (tempComponent.getDomain().equalsIgnoreCase("numeric")) {
                            tempTableAttributes.setContent(random.numberGenerator(tempComponent.getPrecision()));
                        } else if (tempComponent.getDomain().equalsIgnoreCase("varchar")) {
                            tempTableAttributes.setContent(random.wordGenerator(tempComponent.getPrecision() / 10));
                        } else if (tempComponent.getDomain().equalsIgnoreCase("datetime")) {
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            LocalDateTime now = LocalDateTime.now();
                            tempTableAttributes.setContent(dtf.format(now));
                        }

                        attributesTableList.add(tempTableAttributes);
                        tempTable.setTableContent(tempTable.getTableContent() + tempComponent.getName() + " " + tempComponent.getDomain());
                        if(!tempComponent.getDomain().equalsIgnoreCase("int")||!tempComponent.getDomain().equalsIgnoreCase("datetime")){
                            tempTable.setTableContent(tempTable.getTableContent() + "(" + tempComponent.getPrecision() + "), \n");
                        }else{
                            tempTable.setTableContent(tempTable.getTableContent() + "), \n");
                        }
                        if (tempComponent.getIsPrimary() == true) {
                            tempTable.setTableContent(tempTable.getTableContent() + "PRIMARY KEY (" + tempComponent.getName() + "),\n");
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
                LinkedList<TableAttributes> attributesList = new LinkedList<>();
                tempTable.setName(tempRelationshipSets.getName());
                tempTable.setType(tempRelationshipSets.getType());
                tempTable.setTableContent("");
                tempTable.setTableContent(tempTable.getTableContent() + "CREATE TABLE " + tempRelationshipSets.getName() + "( \n");
                LinkedList<ParticipationEntities> tempParticipationEntities = tempRelationshipSets.getParticipationEntities();
                for (int j = 0; j < tempParticipationEntities.size(); j++) {
                    ParticipationEntities temEntities = tempParticipationEntities.get(j);
                    for (int k = 0; k < tableList.size(); k++) {
                        if (temEntities.getEntityName().equalsIgnoreCase(tableList.get(k).getName())) {
                            LinkedList<TableAttributes> auxAttributesList = tableList.get(k).getAttributes();
                            for (int l = 0; l < auxAttributesList.size(); l++) {
                                if (auxAttributesList.get(l).getIsPrimary()) {
                                    TableAttributes auxAttribute = new TableAttributes();
                                    auxAttribute.setName(auxAttributesList.get(l).getName() + "_" + temEntities.getEntityName());
                                    auxAttribute.setDomain(auxAttributesList.get(l).getDomain());
                                    auxAttribute.setContent(auxAttributesList.get(l).getContent());
                                    auxAttribute.setPresicion(auxAttributesList.get(l).getPresicion());
                                    auxAttribute.setIsForeign(true);
                                    auxAttribute.setIsPrimary(true);
                                    tempTable.setTableContent(tempTable.getTableContent() + auxAttribute.getName() + " " + auxAttribute.getDomain());
                                    if (!auxAttribute.getDomain().equalsIgnoreCase("int") || !auxAttribute.getDomain().equalsIgnoreCase("datetime")) {
                                        tempTable.setTableContent(tempTable.getTableContent() + "(" + auxAttribute.getPresicion() + "), \n");
                                    } else {
                                        tempTable.setTableContent(tempTable.getTableContent() + ",\n");
                                    }
                                    tempTable.setTableContent(tempTable.getTableContent() + "PRIMARY KEY (" + auxAttribute.getName() + "),\n");
                                    tempTable.setTableContent(tempTable.getTableContent() + "FOREIGN KEY (" + auxAttribute.getName() + "), REFERENCES " + temEntities.getEntityName() + "\n");
                                    attributesList.add(auxAttribute);
                                }
                            }
                        }
                    }
                }
                LinkedList<DescriptiveAttributes> tempDescripAttribut = tempRelationshipSets.getDescriptiveAttributes();

                if (tempDescripAttribut != null) {
                    for (int j = 0; j < tempDescripAttribut.size(); j++) {
                        DescriptiveAttributes auxDescriptiveAttributes = tempDescripAttribut.get(j);
                        if (auxDescriptiveAttributes.getType().equalsIgnoreCase("Simple")) {
                            TableAttributes auxTableAttributes = new TableAttributes();
                            auxTableAttributes.setName(auxDescriptiveAttributes.getName());
                            auxTableAttributes.setDomain(auxDescriptiveAttributes.getDomain());
                            auxTableAttributes.setIsPrimary(auxDescriptiveAttributes.isIsPrimary());

                            if (auxDescriptiveAttributes.getDomain().equalsIgnoreCase("int")) {
                                auxTableAttributes.setContent(random.numberGenerator(7));
                            } else if (auxDescriptiveAttributes.getDomain().equalsIgnoreCase("smallint")) {
                                auxTableAttributes.setContent(random.numberGenerator(4));
                            } else if (auxDescriptiveAttributes.getDomain().equalsIgnoreCase("numeric")) {
                                auxTableAttributes.setContent(random.numberGenerator(auxDescriptiveAttributes.getPrecision()));
                            } else if (auxDescriptiveAttributes.getDomain().equalsIgnoreCase("varchar")) {
                                auxTableAttributes.setContent(random.wordGenerator(auxDescriptiveAttributes.getPrecision() / 10));
                            } else if (auxDescriptiveAttributes.getDomain().equalsIgnoreCase("datetime")) {
                                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                LocalDateTime now = LocalDateTime.now();
                                auxTableAttributes.setContent(dtf.format(now));
                            }
                            attributesList.add(auxTableAttributes);

                            tempTable.setTableContent(tempTable.getTableContent() + auxDescriptiveAttributes.getName() + " " + auxDescriptiveAttributes.getDomain());
                            if (!auxDescriptiveAttributes.getDomain().equalsIgnoreCase("int") || !auxDescriptiveAttributes.getDomain().equalsIgnoreCase("datetime")) {
                                tempTable.setTableContent(tempTable.getTableContent() + "(" + auxDescriptiveAttributes.getPrecision() + "), \n");
                            }else{
                                tempTable.setTableContent(tempTable.getTableContent() + "), \n");
                            }
                            if (auxDescriptiveAttributes.isIsPrimary() == true) {
                                tempTable.setTableContent(tempTable.getTableContent() + "PRIMARY KEY (" + auxDescriptiveAttributes.getName() + "),\n");
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
            relationalModelText += tableList.get(i).getTableContent();

            LinkedList<TableAttributes> tableAttributesList = tableList.get(i).getAttributes();
            relationalModelText += "INSERT INTO " + tableList.get(i).getName() + " VALUES (";
            for (int j = 0; j < tableAttributesList.size(); j++) {
                relationalModelText += tableAttributesList.get(j).getContent();
                if (j < tableAttributesList.size() - 1) {
                    relationalModelText += ",";
                }
            }
            relationalModelText += ");\n\n\n";

        }
        try {
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
        } catch (NullPointerException npe) {
            message = "Please select a specific path.";
        }
        return message;
      
    }
}
