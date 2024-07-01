package org.example;

import java.util.*;

public class KnowledgeBase {


    public Entity guessing(Map<Entity, List<Entity>> knowledgeBase, List<HistoryRecord> userInput) {
        List<Entity> variants = new ArrayList<>();
        List<Entity> charList = new ArrayList<>();
        for (Map.Entry<Entity, List<Entity>> entry : knowledgeBase.entrySet()) {
            boolean ignoreAnimal = false;
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
                variants.addAll(charList);
                charList.addAll(entry.getValue());
            }
        }
        if (variants.size() == 1) {
            for (Entity v : variants) {
                if (v.getEntityType().equals(EntityType.ANIMAL)) {
                    return variants.get(0);
                }
            }
        } else if (variants.size() > 1) {
            if (!userInput.isEmpty()) {
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
            } else {
                return variants.get(0);
            }
        }
        return null;
    }

      public void update(Entity animal, List<Entity> newCharacteristic, Map<Entity, List<Entity>> knowledgeBase, List<HistoryRecord> userInput) {
        List<Entity> characteristics = new ArrayList<>();
        for (HistoryRecord historyRecord : userInput) {
            if (historyRecord.getEntity().getEntityType().equals(EntityType.CHARACTERISTIC) && historyRecord.getToBe()) {
                characteristics.add(historyRecord.getEntity());
            }
        }
        if (knowledgeBase.containsKey(animal)) {
        characteristics.addAll(knowledgeBase.get(animal));
    }
        characteristics.addAll(newCharacteristic);
        knowledgeBase.put(animal, characteristics);
    }

}
