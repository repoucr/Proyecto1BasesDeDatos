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
    private LinkedList<String> attributes;

    public Table() {
    }

    public Table(String name, String tableContent, LinkedList<String> attributes) {
        this.name = name;
        this.tableContent = tableContent;
        this.attributes = attributes;
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

    public LinkedList<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(LinkedList<String> attributes) {
        this.attributes = attributes;
    }

    

}
