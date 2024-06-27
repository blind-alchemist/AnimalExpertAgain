package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<String> userInput = null;

        Scanner enter = new Scanner(System.in);
        String startText = "Загадай животное и нажми Enter";
        System.out.println(startText);
        String e = enter.nextLine();

        while (true) {
            Entity variant = KnowledgeBase.knowledgeBaseGuessing();
            if (variant != null) {
                if (variant.getEntityType().equals("animal")) {
                    System.out.println("Животное, которое ты загадал - " + variant.getName());
                    String animalGuess = enter.nextLine();
                    if (animalGuess.equals("да")) {
                        System.out.println("Ура, я победил!");
                        userInput.clear();
                        System.out.println("-----\n" + startText);
                    } else {
                        KnowledgeBase.knowledgeBaseUpdate();
                    }
                } else  //variant type is "characteristic"
                {
                    System.out.println("Животное, которое ты загадал, имеет " + variant.getName());
                    String charAnswer = enter.nextLine();
                    assert userInput != null;
                    userInput.add(charAnswer);
                }
            } else {
                System.out.println("Я не могу угадать :( Кого ты загадал?");
                String animalName = enter.nextLine();
                KnowledgeBase.knowledgeBaseUpdate();
                userInput.clear();
                System.out.println(startText);
            }
        }

    }
}