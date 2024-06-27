package org.example;

public class HistoryRecord {

    private Entity entity;
    private Boolean isExist;

    public HistoryRecord(Entity entity, Boolean isExist) {
        this.entity = entity;
        this.isExist = isExist;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Boolean getExist() {
        return isExist;
    }

    public void setExist(Boolean exist) {
        isExist = exist;
    }
}
