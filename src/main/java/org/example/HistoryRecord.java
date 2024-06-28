package org.example;

public class HistoryRecord {

    private Entity entity;
    private Boolean toBe;

    public HistoryRecord(Entity entity, Boolean toBe) {
        this.entity = entity;
        this.toBe = toBe;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Boolean getToBe() {
        return toBe;
    }

    public void setToBe(Boolean exist) {
        toBe = exist;
    }
}
