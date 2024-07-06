package org.example;

public class Entity {

    private EntityType entityType;
    private String name;

    public Entity(EntityType entityType, String name) {
        this.entityType = entityType;
        this.name = name;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "entityType=" + entityType +
                ", name='" + name + '\'' +
                '}';
    }
}
