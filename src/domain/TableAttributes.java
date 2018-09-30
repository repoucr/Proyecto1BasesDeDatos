/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author fabian
 */
public class TableAttributes {
    
    private String name;
    private String domain;
    private boolean isPrimary;

    public TableAttributes() {
    }

    public TableAttributes(String name, String domain, boolean isPrimary) {
        this.name = name;
        this.domain = domain;
        this.isPrimary = isPrimary;
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

    public boolean isIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    

    
}
