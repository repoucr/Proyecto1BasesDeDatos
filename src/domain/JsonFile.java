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
public class JsonFile {
    
    private LinkedList<RelationshipSets> relationshipSets= new LinkedList<>();
    private LinkedList<EntitySets> entitySets = new LinkedList<>();

    public JsonFile() {
    }

    public LinkedList<RelationshipSets> getRelationshipSets() {
        return relationshipSets;
    }

    public void setRelationshipSets(LinkedList<RelationshipSets> relationshipSets) {
        this.relationshipSets = relationshipSets;
    }

    public LinkedList<EntitySets> getEntitySets() {
        return entitySets;
    }

    public void setEntitySets(LinkedList<EntitySets> entitySets) {
        this.entitySets = entitySets;
    }

    
    
    
}
