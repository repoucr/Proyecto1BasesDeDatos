/*}
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import domain.Attributes;
import domain.Component;
import domain.DescriptiveAttributes;
import domain.EntitySets;
import domain.JsonFile;
import domain.ParticipationEntities;
import domain.RelationshipSets;
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

    public JsonFile readJson(String json) throws ParseException, FileNotFoundException, IOException {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(json);
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray jsonArrayEntitySets = (JSONArray) jsonObject.get("EntitySets");
        JSONArray jsonArrayRelationshipSets = (JSONArray) jsonObject.get("RelationshipSets");

        JsonFile jsonFile = new JsonFile();

        LinkedList<RelationshipSets> relationshipSetList = new LinkedList<>();
        LinkedList<EntitySets> entitySetsList = new LinkedList<>();

        for (int i = 0; i < jsonArrayEntitySets.size(); i++) {
            JSONObject tempJOEntitySets = (JSONObject) jsonArrayEntitySets.get(i);
            EntitySets tempEntitySets = new EntitySets();
            tempEntitySets.setName(tempJOEntitySets.get("Name").toString());
            tempEntitySets.setType(tempJOEntitySets.get("Type").toString());
            if (tempJOEntitySets.get("ParentEntitySet") != null) {
                tempEntitySets.setParentEntitySet(tempJOEntitySets.get("ParentEntitySet").toString());
            }
            JSONArray jsonArrayAttributes = (JSONArray) tempJOEntitySets.get("Attributes");
            LinkedList<Attributes> tempAttributesList = new LinkedList<>();
            for (int j = 0; j < jsonArrayAttributes.size(); j++) {
                JSONObject tempJOAttributes = (JSONObject) jsonArrayAttributes.get(j);
                Attributes tempAttributes = new Attributes();
                tempAttributes.setName(tempJOAttributes.get("Name").toString());
                tempAttributes.setDomain(tempJOAttributes.get("Domain").toString());
                tempAttributes.setType(tempJOAttributes.get("Type").toString());
                if (tempJOAttributes.get("Type").equals("Composed")) {
                    JSONArray jsonArrayComponents = (JSONArray) tempJOAttributes.get("ComponentList");
                    LinkedList<Component> linkedListComponents = new LinkedList<>();

                    for (int k = 0; k < jsonArrayComponents.size(); k++) {
                        JSONObject tempJOComponent = (JSONObject) jsonArrayComponents.get(k);
                        Component tempComponent = new Component();
                        tempComponent.setName(tempJOComponent.get("Name").toString());
                        tempComponent.setDomain(tempJOComponent.get("Domain").toString());
                        tempComponent.setType(tempJOComponent.get("Type").toString());
                        if (tempJOComponent.get("Type").equals("Composed")) {
                            JSONArray jsonArrayComponents2 = (JSONArray) tempJOComponent.get("ComponentList");
                            LinkedList<Component> linkedListComponents2 = new LinkedList<>();
                            for (int l = 0; l < jsonArrayComponents2.size(); l++) {
                                JSONObject tempJOComponent2 = (JSONObject) tempJOComponent.get(l);
                                Component tempComponent2 = new Component();
                                tempComponent2.setName(tempJOComponent2.get("Name").toString());
                                tempComponent2.setDomain(tempJOComponent2.get("Domain").toString());
                                tempComponent2.setType(tempJOComponent2.get("Type").toString());
                                tempComponent2.setIsPrimary((boolean) tempJOComponent2.get("IsPrimary"));
                                tempComponent2.setIsDiscriminator((boolean) tempJOComponent2.get("IsDiscriminator"));
                                tempComponent2.setPrecision(Integer.parseInt((tempJOComponent2.get("Precision").toString())));
                                linkedListComponents2.add(tempComponent2);
                            }
                            tempComponent.setComponentList(linkedListComponents2);
                        }
                        tempComponent.setIsPrimary((boolean) tempJOComponent.get("IsPrimary"));
                        tempComponent.setIsDiscriminator((boolean) tempJOComponent.get("IsDiscriminator"));
                        tempComponent.setPrecision(Integer.parseInt((tempJOComponent.get("Precision").toString())));
                        linkedListComponents.add(tempComponent);
                    }
                    tempAttributes.setComponentList(linkedListComponents);
                }
                tempAttributes.setIsPrimary((boolean) tempJOAttributes.get("IsPrimary"));
                tempAttributes.setIsDiscriminator((boolean) tempJOAttributes.get("IsDiscriminator"));
                tempAttributes.setPrecision(Integer.parseInt(tempJOAttributes.get("Precision").toString()));

                tempAttributesList.add(tempAttributes);
            }
            tempEntitySets.setAttributes(tempAttributesList);
            entitySetsList.add(tempEntitySets);
        }

        for (int i = 0; i < jsonArrayRelationshipSets.size(); i++) {
            JSONObject tempJORelationshipSets = (JSONObject) jsonArrayRelationshipSets.get(i);
            RelationshipSets tempRelationshipSets = new RelationshipSets();
            tempRelationshipSets.setName(tempJORelationshipSets.get("Name").toString());
            tempRelationshipSets.setType(tempJORelationshipSets.get("Type").toString());

            LinkedList<DescriptiveAttributes> arrayDescriptiveAttributes = new LinkedList();
            LinkedList<ParticipationEntities> arrayParticipationEntities = new LinkedList();
            if (tempJORelationshipSets.get("DescriptiveAttributes") != null) {
                JSONArray jsonArrayDescriptiveAttributes = (JSONArray) tempJORelationshipSets.get("DescriptiveAttributes");
                for (int j = 0; j < jsonArrayDescriptiveAttributes.size(); j++) {
                    JSONObject tempJODescriptiveAttributes = (JSONObject) jsonArrayDescriptiveAttributes.get(j);
                    DescriptiveAttributes tempDescriptiveAttributes = new DescriptiveAttributes();
                    tempDescriptiveAttributes.setName(tempJODescriptiveAttributes.get("Name").toString());
                    tempDescriptiveAttributes.setDomain(tempJODescriptiveAttributes.get("Domain").toString());
                    tempDescriptiveAttributes.setType(tempJODescriptiveAttributes.get("Type").toString());
                    if (tempJODescriptiveAttributes.get("Type").toString().equalsIgnoreCase("Composed")) {
                        JSONArray componentListArray = (JSONArray) tempJODescriptiveAttributes.get("ComponentList");
                        LinkedList<Component> componentList = new LinkedList<>();
                        for (int k = 0; k < componentListArray.size(); k++) {
                            JSONObject tempJOComponent = (JSONObject) componentListArray.get(k);
                            Component tempComponent = new Component();
                            tempComponent.setName(tempJOComponent.get("Name").toString());
                            tempComponent.setDomain(tempJOComponent.get("Domain").toString());
                            tempComponent.setType(tempJOComponent.get("Type").toString());
                            if (tempJOComponent.get("Type").toString().equalsIgnoreCase("Composed")) {
                                JSONArray componentListArray2 = (JSONArray) tempJOComponent.get("ComponentList");
                                LinkedList<Component> componentList2 = new LinkedList<>();
                                for (int l = 0; l < componentListArray2.size(); l++) {
                                    JSONObject tempJOComponent2 = (JSONObject) componentListArray.get(l);
                                    Component tempComponent2 = new Component();
                                    tempComponent2.setName(tempJOComponent2.get("Name").toString());
                                    tempComponent2.setDomain(tempJOComponent2.get("Domain").toString());
                                    tempComponent2.setType(tempJOComponent2.get("Type").toString());
                                    tempComponent2.setIsPrimary((boolean) tempJOComponent2.get("IsPrimary"));
                                    tempComponent2.setIsDiscriminator((boolean) tempJOComponent2.get("IsDiscriminator"));
                                    tempComponent2.setPrecision(Integer.parseInt(tempJOComponent2.get("Precision").toString()));
                                    componentList2.add(tempComponent2);
                                }
                                tempComponent.setComponentList(componentList2);
                            }
                            tempComponent.setIsPrimary((boolean) tempJOComponent.get("IsPrimary"));
                            tempComponent.setIsDiscriminator((boolean) tempJOComponent.get("IsDiscriminator"));
                            tempComponent.setPrecision(Integer.parseInt(tempJOComponent.get("Precision").toString()));
                            componentList.add(tempComponent);
                        }
                        tempDescriptiveAttributes.setComponentList(componentList);
                    }
                    tempDescriptiveAttributes.setIsPrimary((boolean) tempJODescriptiveAttributes.get("IsPrimary"));
                    tempDescriptiveAttributes.setIsDiscriminator((boolean) tempJODescriptiveAttributes.get("IsDiscriminator"));
                    tempDescriptiveAttributes.setPrecision(Integer.parseInt(tempJODescriptiveAttributes.get("Precision").toString()));
                    arrayDescriptiveAttributes.add(tempDescriptiveAttributes);
                }
                tempRelationshipSets.setDescriptiveAttributes(arrayDescriptiveAttributes);
            }

            if (tempJORelationshipSets.get("ParticipationEntities") != null) {
                JSONArray jsonArrayParticipationEntities = (JSONArray) tempJORelationshipSets.get("ParticipationEntities");
                for (int j = 0; j < jsonArrayParticipationEntities.size(); j++) {
                    JSONObject tempJOParticipationEntities = (JSONObject) jsonArrayParticipationEntities.get(j);
                    ParticipationEntities tempParticipationEntities = new ParticipationEntities();
                    tempParticipationEntities.setEntityName(tempJOParticipationEntities.get("EntityName").toString());
                    tempParticipationEntities.setCardinality(tempJOParticipationEntities.get("Cardinality").toString());
                    tempParticipationEntities.setParticipationType(tempJOParticipationEntities.get("ParticipationType").toString());
                    arrayParticipationEntities.add(tempParticipationEntities);
                }
                tempRelationshipSets.setParticipationEntities(arrayParticipationEntities);
            }
            relationshipSetList.add(tempRelationshipSets);

            jsonFile.setRelationshipSets(relationshipSetList);
            jsonFile.setEntitySets(entitySetsList);

        }
        return jsonFile;
    }
}
