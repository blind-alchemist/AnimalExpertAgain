package org.example;

public class Entity {

    private String entityType;
    private String name;

    public Entity(String entityType, String name) {
        this.entityType = entityType;
        this.name = name;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
