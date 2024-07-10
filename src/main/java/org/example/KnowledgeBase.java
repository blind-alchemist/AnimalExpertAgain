package org.example;

import java.util.*;

public class KnowledgeBase {


    public Entity guessing(Map<Entity, List<Entity>> knowledgeBase, HashSet<HistoryRecord> userInput) {
        List<Entity> animalVariants = new ArrayList<>();
        List<Entity> charVariants = new ArrayList<>();
        HashSet<Entity> charFullList = new HashSet<>();
        for (List<Entity> c : knowledgeBase.values()) {
            charFullList.addAll(c);
        }
        System.out.println("charFullList: " + charFullList);
        for (Entity characteristic : charFullList) {
            if (!userInput.isEmpty()) {
                for (HistoryRecord userInputCheck : userInput) {
                    if (userInputCheck.getToBe()) {
                        if (characteristic.compareTo(userInputCheck.getEntity()) != 0) {
                            charVariants.add(characteristic);
                            break;
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
                            break;
                        }
                    }
                }
            } else {
                animalVariants.add(animal);
            }
        }
        System.out.println("userInput: " + userInput.toString());
        System.out.println("animalVariants: " + animalVariants);
        System.out.println("charVariants: " + charVariants);
        if (animalVariants.size() == 1) {
            return animalVariants.get(0);
        } else if (animalVariants.size() > 1) {
            if (charVariants.size() > 1) {
                return charVariants.get(0);
            } else {
                return animalVariants.get(0);
            }
        }
        return null;
    }

      public void update(Entity animal, List<Entity> newCharacteristic, Map<Entity, List<Entity>> knowledgeBase, HashSet<HistoryRecord> userInput) {
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
