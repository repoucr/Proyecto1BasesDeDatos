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
public class Table {
    
    private String name;
    private String tableContent;
    private LinkedList<TableAttributes> attributes;
    private String type;

    public Table() {
    }

    public Table(String name, String tableContent, LinkedList<TableAttributes> attributes, String type) {
        this.name = name;
        this.tableContent = tableContent;
        this.attributes = attributes;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Table{" + "name=" + name + ", tableContent=" + tableContent + ", attributes=" + attributes + ", type=" + type + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTableContent() {
        return tableContent;
    }

    public void setTableContent(String tableContent) {
        this.tableContent = tableContent;
    }

    public LinkedList<TableAttributes> getAttributes() {
        return attributes;
    }

    public void setAttributes(LinkedList<TableAttributes> attributes) {
        this.attributes = attributes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



}
