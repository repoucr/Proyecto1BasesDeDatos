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
    private boolean isForeign;
    private int precision;

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

    public boolean getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    public boolean getIsForeign() {
        return isForeign;
    }

    public void setIsForeign(boolean isForeign) {
        this.isForeign = isForeign;
    }

    public int getPresicion() {
        return precision;
    }

    public void setPresicion(int presicion) {
        this.precision = presicion;
    }

    @Override
    public String toString() {
        return "TableAttributes{" + "name=" + name + ", domain=" + domain + ", isPrimary=" + isPrimary + ", isForeign=" + isForeign + ", precision=" + precision + '}';
    }

    public TableAttributes(String name, String domain, boolean isPrimary, boolean isForeign, int presicion) {
        this.name = name;
        this.domain = domain;
        this.isPrimary = isPrimary;
        this.isForeign = isForeign;
        this.precision = presicion;
    }

    public TableAttributes() {
    }
  

}