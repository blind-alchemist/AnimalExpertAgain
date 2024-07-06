package org.example;

import java.util.*;

import static java.lang.System.out;

public class AnimalExpert {

    Scanner enter = new Scanner(System.in);
    String startText = "Загадай животное и нажми Enter";
    String animalName = "";
    String charName = "";

    public void gameplayLoop() {

        KnowledgeBase knowledgeBase = new KnowledgeBase();
        Map<Entity, List<Entity>> knowledgeBaseMap = new HashMap<>();
        List<HistoryRecord> userInputList = new ArrayList<>();
        out.println(startText);
        enter.nextLine();

        Entity fox = new Entity(EntityType.ANIMAL, "лиса");
        Entity spider = new Entity(EntityType.ANIMAL, "паук");
        Entity legs = new Entity(EntityType.CHARACTERISTIC, "ноги");
        Entity tail = new Entity(EntityType.CHARACTERISTIC, "хвост");
        Entity wool = new Entity(EntityType.CHARACTERISTIC, "шерсть");
        List <Entity> charFox = Arrays.asList(legs, tail, wool);
        List <Entity> charSpider = Arrays.asList(legs, wool);
        knowledgeBaseMap.put(spider, charSpider);
        knowledgeBaseMap.put(fox, charFox);

        while (true) {
            Entity variant = knowledgeBase.guessing(knowledgeBaseMap, userInputList);
            List<Entity> newChar = new ArrayList<>();
            if (variant == null) {
                out.println("Я не могу угадать :( Кого ты загадал?");
                animalName = enter.nextLine();
                out.println("Какие есть характеристики у этого животного? Напиши их через запятую");
                charName = enter.nextLine();
                charName.toLowerCase();
                String[] charNameArray = charName.split(",");
                for (String word : charNameArray) {
                    newChar.add(new Entity(EntityType.CHARACTERISTIC, word));
                }
                knowledgeBase.update(new Entity(EntityType.ANIMAL, animalName), newChar, knowledgeBaseMap, userInputList);
                knowledgeBase.update(new Entity(EntityType.CHARACTERISTIC, charName), newChar, knowledgeBaseMap, userInputList);
                userInputList.clear();
                out.println(startText);
            }  else if (variant.getEntityType().equals(EntityType.ANIMAL)) {
                out.println("Животное, которое ты загадал - " + variant.getName());
                String animalGuess = enter.nextLine();
                if (animalGuess.equals("да")) {
                    out.println("Ура, я победил!");
                    userInputList.clear();
                    out.println("-----\n" + startText);
                } else {
                    knowledgeBase.update(new Entity(EntityType.ANIMAL, animalName), newChar, knowledgeBaseMap, userInputList);
                    userInputList.add(new HistoryRecord(variant, Boolean.FALSE));
                }
            } else  //variant type is "characteristic"
            {
                out.println("Животное, которое ты загадал, имеет " + variant.getName());
                String charAnswer = enter.nextLine();
                if (charAnswer.equals("да")) {
                    userInputList.add(new HistoryRecord(variant, true));
                } else {
                    userInputList.add(new HistoryRecord(variant, false));
                }
            }
        }
    }
}
