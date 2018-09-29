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
public class Table {
    
    private String name;
    private String tableContent;

    public Table(String name, String tableContent) {
        this.name = name;
        this.tableContent = tableContent;
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

    public Table() {
    }
   
    
    
}
