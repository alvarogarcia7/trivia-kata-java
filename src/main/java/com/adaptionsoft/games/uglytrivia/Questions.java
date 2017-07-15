package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class Questions {
    protected LinkedList popQuestions = new LinkedList();
    protected LinkedList scienceQuestions = new LinkedList();
    protected LinkedList sportsQuestions = new LinkedList();
    protected LinkedList rockQuestions = new LinkedList();

    protected void askQuestion(int currentPlayerPlace) {
        if (currentCategory(currentPlayerPlace) == "Pop")
            System.out.println(popQuestions.removeFirst());
        if (currentCategory(currentPlayerPlace) == "Science")
            System.out.println(scienceQuestions.removeFirst());
        if (currentCategory(currentPlayerPlace) == "Sports")
            System.out.println(sportsQuestions.removeFirst());
        if (currentCategory(currentPlayerPlace) == "Rock")
            System.out.println(rockQuestions.removeFirst());
    }

    String currentCategory(int place) {
        if (is0or4or8(place)) {
            return "Pop";
        } else if (is1or5or9(place)) {
            return "Science";
        } else if (is2or6or10(place)) {
            return "Sports";
        } else {
            return "Rock";
        }

    }

    private boolean is2or6or10(int place) {
        int n = place;
        return n == 2 || n == 6 || n == 10;
    }

    private boolean is1or5or9(int place) {
        int n = place;
        return n == 1 || n == 5 || n == 9;
    }

    private boolean is0or4or8(int place) {
        int n = place;
        return n == 0 || n == 4 || n == 8;
    }

    public static Questions standardSet() {
        Questions questions = new Questions();
        for (int i = 0; i < 50; i++) {
            questions.popQuestions.addLast("Pop Question " + i);
            questions.scienceQuestions.addLast(("Science Question " + i));
            questions.sportsQuestions.addLast(("Sports Question " + i));
            questions.rockQuestions.addLast(questions.createRockQuestion(i));
        }
        return questions;
    }

    public String createRockQuestion(int index) {
        return "Rock Question " + index;
    }
}
