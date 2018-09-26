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
public class DescriptiveAttributes {
    private String name;
    private String domain;
    private String type;
    private LinkedList componentList;
    private boolean isPrimary;
    private boolean isDiscriminator;
    private int precision;

    public DescriptiveAttributes() {
    }

    public DescriptiveAttributes(String name, String domain, String type, LinkedList componentList, boolean isPrimary, boolean isDiscriminator, int precision) {
        this.name = name;
        this.domain = domain;
        this.type = type;
        this.componentList = componentList;
        this.isPrimary = isPrimary;
        this.isDiscriminator = isDiscriminator;
        this.precision = precision;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LinkedList getComponentList() {
        return componentList;
    }

    public void setComponentList(LinkedList componentList) {
        this.componentList = componentList;
    }

    public boolean isIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    public boolean isIsDiscriminator() {
        return isDiscriminator;
    }

    public void setIsDiscriminator(boolean isDiscriminator) {
        this.isDiscriminator = isDiscriminator;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }
    
    
    
}
