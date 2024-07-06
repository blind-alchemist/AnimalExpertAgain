package org.example;

import java.util.*;

public class KnowledgeBase {


    public Entity guessing(Map<Entity, List<Entity>> knowledgeBase, List<HistoryRecord> userInput) {
        List<Entity> animalVariants = new ArrayList<>();
        List<Entity> charVariants = new ArrayList<>();
        for (Map.Entry<Entity, List<Entity>> entry : knowledgeBase.entrySet()) {
            boolean ignoreAnimal = false;
                for (HistoryRecord historyRecord : userInput) {
                    if (historyRecord.getEntity().getEntityType().equals(EntityType.ANIMAL)) {
                        ignoreAnimal = true;
                        break;
                    } else {
                        if (historyRecord.getToBe()) {
                            for (Entity c : charVariants) {
                                if (!c.equals(historyRecord.getEntity())) {
                                    ignoreAnimal = true;
                                    break;
                                }
                            }
                        } else {
                            for (Entity c : charVariants) {
                                if (c.equals(historyRecord.getEntity())) {
                                    ignoreAnimal = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            if (!ignoreAnimal) {
                animalVariants.add(entry.getKey());
                charVariants.addAll(entry.getValue());
                System.out.println(charVariants);
                System.out.println(animalVariants);
            }
        }
        if (animalVariants.size() == 1) {
            return animalVariants.get(0);
            }
        else if (animalVariants.size() > 1) {
            if (!userInput.isEmpty()) {
                for (HistoryRecord historyRecord : userInput) {
                    if (historyRecord.getEntity().getEntityType().equals(EntityType.CHARACTERISTIC)) {
                        charVariants.remove(historyRecord.getEntity());
                    }
                    if (charVariants.isEmpty()) {
                        return animalVariants.get(0);
                    } else {
                        return charVariants.get(0);
                    }
                }
            } else {
                return charVariants.get(0);
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
