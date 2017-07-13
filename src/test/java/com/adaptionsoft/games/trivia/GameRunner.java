package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Game;

import java.util.Random;

public class GameRunner {
    private final Random random;

    public GameRunner(Random random) {
        this.random = random;
    }

    public void run() {
        boolean notAWinner;
        Game aGame = new Game();

        int numberOfPlayers = random.nextInt(4);
        aGame.add("Chet");
        aGame.add("Pat");
        for (int i = 0; i < numberOfPlayers; i++) {
            aGame.add("Player " + i);
        }

        do {
            aGame.roll(random.nextInt(5) + 1);
            if (random.nextInt(9) == 7) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }
        } while (notAWinner);
    }
}
