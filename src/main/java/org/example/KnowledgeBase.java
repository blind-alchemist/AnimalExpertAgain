package org.example;

import java.util.*;

public class KnowledgeBase {


    public Entity guessing(Map<Entity, List<Entity>> knowledgeBase, List<HistoryRecord> userInput) {
        List<Entity> animalVariants = new ArrayList<>();
        List<Entity> charVariants = new ArrayList<>();
        List<Entity> charFullList = new ArrayList<>();
        for (List<Entity> v : knowledgeBase.values()) {
            if (!charFullList.contains(v)) {
            charFullList.addAll(v);
            }
        }
        System.out.println(charFullList);
        for (Entity characteristic : charFullList) {
            if (!userInput.isEmpty()) {
                for (HistoryRecord userInputCheck : userInput) {
                    if (userInputCheck.getToBe()) {
                        if (characteristic.compareTo(userInputCheck.getEntity()) != 0) {
                            charVariants.add(characteristic);
                        }
                    }
                }
            } else {
                charVariants.add(characteristic);
            }
        }
        for (Entity animal : knowledgeBase.keySet()) {
            if (!userInput.isEmpty()) {
                for (HistoryRecord userInputCheck : userInput) {
                    if (userInputCheck.getToBe()) {
                        if (animal.compareTo(userInputCheck.getEntity()) != 0) {
                            animalVariants.add(animal);
                        }
                    }
                }
            } else {
                animalVariants.add(animal);
            }
        }
        System.out.println(animalVariants);
        System.out.println(charVariants);
        if (animalVariants.size() == 1) {
            return animalVariants.get(0);
        } else if (!charVariants.isEmpty()) {
            return charVariants.get(0);
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
