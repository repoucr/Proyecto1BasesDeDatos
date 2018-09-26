/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.LinkedList;

/**
 *
 * @author fabian
 */
public class RelationshipSets {
    
   private String name;
   private String type;
   private LinkedList descriptiveAttributes;
   private LinkedList participationEntities;

    public RelationshipSets() {
    }

    public RelationshipSets(String name, String type, LinkedList descriptiveAttributes, LinkedList participationEntities) {
        this.name = name;
        this.type = type;
        this.descriptiveAttributes = descriptiveAttributes;
        this.participationEntities = participationEntities;
    }

   
   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LinkedList getDescriptiveAttributes() {
        return descriptiveAttributes;
    }

    public void setDescriptiveAttributes(LinkedList descriptiveAttributes) {
        this.descriptiveAttributes = descriptiveAttributes;
    }

    public LinkedList getParticipationEntities() {
        return participationEntities;
    }

    public void setParticipationEntities(LinkedList participationEntities) {
        this.participationEntities = participationEntities;
    }
   
   
}
