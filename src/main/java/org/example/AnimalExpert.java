package org.example;

import java.util.Scanner;

import static java.lang.System.out;

public class AnimalExpert {

    Scanner enter = new Scanner(System.in);
    String startText = "Загадай животное и нажми Enter";
    String animalName = "";
    String charName = "";

    public void gameplayLoop() {

        KnowledgeBase knowledgeBase = new KnowledgeBase();
        out.println(startText);
        enter.nextLine();

        while (true) {
            Entity variant = knowledgeBase.guessing();
            if (variant == null) {
                out.println("Я не могу угадать :( Кого ты загадал?");
                animalName = enter.nextLine();
                out.println("Какие есть характеристики у этого животного?");
                charName = enter.nextLine();
                knowledgeBase.update(new Entity(EntityType.ANIMAL, animalName));
                knowledgeBase.update(new Entity(EntityType.CHARACTERISTIC, charName));
                knowledgeBase.userInput.clear();
                out.println(startText);
            }  else if (variant.getEntityType().equals(EntityType.ANIMAL)) {
                out.println("Животное, которое ты загадал - " + variant.getName());
                String animalGuess = enter.nextLine();
                if (animalGuess.equals("да")) {
                    out.println("Ура, я победил!");
                    knowledgeBase.userInput.clear();
                    out.println("-----\n" + startText);
                } else {
                    knowledgeBase.update(new Entity(EntityType.ANIMAL, animalName));
                }
            } else  //variant type is "characteristic"
            {
                out.println("Животное, которое ты загадал, имеет " + variant.getName());
                String charAnswer = enter.nextLine();
                knowledgeBase.userInput.add(new HistoryRecord(variant, charAnswer.equals("да")));
            }
        }
    }
}
