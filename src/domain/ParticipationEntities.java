/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Wilmata
 */
public class ParticipationEntities {
    
    private String entityName;
    private String cardinality;
    private String participationType;

    @Override
    public String toString() {
        return "ParticipationEntities{" + "entityName=" + entityName + ", cardinality=" + cardinality + ", participationType=" + participationType + '}';
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getCardinality() {
        return cardinality;
    }

    public void setCardinality(String cardinality) {
        this.cardinality = cardinality;
    }

    public String getParticipationType() {
        return participationType;
    }

    public void setParticipationType(String participationType) {
        this.participationType = participationType;
    }
}
