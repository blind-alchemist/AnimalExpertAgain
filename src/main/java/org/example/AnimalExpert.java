package org.example;

import java.util.Scanner;

import static java.lang.System.out;

public class AnimalExpert {

    Scanner enter = new Scanner(System.in);
    String startText = "Загадай животное и нажми Enter";

    public void gameplayLoop() {

        KnowledgeBase knowledgeBase = new KnowledgeBase();
        out.println(startText);
        enter.nextLine();

        while (true) {
            Entity variant = knowledgeBase.guessing();
            if (variant == null) {
                out.println("Я не могу угадать :( Кого ты загадал?");
                String animalName = enter.nextLine();
                knowledgeBase.update();
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
                    knowledgeBase.update();
                }
            } else  //variant type is "characteristic"
            {
                out.println("Животное, которое ты загадал, имеет " + variant.getName());
                String charAnswer = enter.nextLine();
            }
        }
    }
}
