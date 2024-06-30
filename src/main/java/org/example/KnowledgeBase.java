package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KnowledgeBase {

    Map<Entity, List<Entity>> knowledgeBase = new HashMap<>();

    List<HistoryRecord> userInput = new ArrayList<>();

    public Entity guessing() {
        List<Entity> variants = new ArrayList<>();
        List<Entity> charList = new ArrayList<>();
        boolean ignoreAnimal = false;
        for (Map.Entry<Entity, List<Entity>> entry : knowledgeBase.entrySet()) {
            for (HistoryRecord historyRecord : userInput) {
                if (historyRecord.getEntity().getEntityType().equals(EntityType.ANIMAL)) {
                    ignoreAnimal = true;
                    break;
                } else {
                    if (historyRecord.getToBe()) {
                        if (!entry.getKey().equals(historyRecord.getEntity())) {
                            ignoreAnimal = true;
                            break;
                        } else {
                            ignoreAnimal = true;
                            break;
                        }
                    }
                }
            }
            if (!ignoreAnimal) {
                variants.add(entry.getKey());
                charList.addAll(entry.getValue());
            }
        }
        if (variants.size() == 1) {
            return variants.get(0);
        } else if (variants.size() > 1) {
            for (HistoryRecord historyRecord : userInput) {
                if (historyRecord.getEntity().getEntityType().equals(EntityType.CHARACTERISTIC)) {
                    charList.remove(historyRecord.getEntity());
                }
                if (charList.isEmpty()) {
                    return variants.get(0);
                } else {
                    return charList.get(0);
                }
            }
        }
        return null;
    }

    public void update(Entity animal) {
        List<Entity> characteristics = new ArrayList<>();
        for (HistoryRecord historyRecord : userInput) {
            if (historyRecord.getEntity().getEntityType().equals(EntityType.CHARACTERISTIC) && historyRecord.getToBe()) {
                characteristics.add(historyRecord.getEntity());
            }
        }
        if (knowledgeBase.containsKey(animal)) {
        characteristics.addAll(knowledgeBase.get(animal));
    }
    knowledgeBase.put(animal, characteristics);
    }
}
