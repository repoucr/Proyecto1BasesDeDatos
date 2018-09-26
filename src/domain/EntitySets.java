/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.LinkedList;

/**
 *
 * @author Wilmata
 */
public class EntitySets {
    
    private String name;
    private String type;
    private String parentEntitySet;
    private LinkedList attributes;

    @Override
    public String toString() {
        return "EntitySets{" + "name=" + name + ", type=" + type + ", parentEntitySet=" + parentEntitySet + ", attributes=" + attributes + '}';
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

    public String getParentEntitySet() {
        return parentEntitySet;
    }

    public void setParentEntitySet(String parentEntitySet) {
        this.parentEntitySet = parentEntitySet;
    }

    public LinkedList getAttributes() {
        return attributes;
    }

    public void setAttributes(LinkedList attributes) {
        this.attributes = attributes;
    }
}
