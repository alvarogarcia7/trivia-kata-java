package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class Game {
	private Players players = new Players();

    private LinkedList popQuestions = new LinkedList();
    private LinkedList scienceQuestions = new LinkedList();
    private LinkedList sportsQuestions = new LinkedList();
    private LinkedList rockQuestions = new LinkedList();

    private boolean isGettingOutOfPenaltyBox;

    public  Game(){
    	for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast(createRockQuestion(i));
    	}
    }

	public String createRockQuestion(int index){
		return "Rock Question " + index;
	}

	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean add(String playerName) {
	    players.addPlayer(playerName);

	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + players.playersSize());
		return true;
	}

	public int howManyPlayers() {
		return players.playersSize();
	}

	public void roll(int roll) {
		System.out.println(players.currentPlayerName() + " is the current player");
		System.out.println("They have rolled a " + roll);

		if (players.isCurrentPlayerinPenaltyBox()) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;

				System.out.println(players.currentPlayerName() + " is getting out of the penalty box");
				players.advancePlace(roll);

				System.out.println(players.currentPlayerName()
						+ "'s new location is "
						+ players.getCurrentPlayerPlace());
				System.out.println("The category is " + currentCategory());
				askQuestion();
			} else {
				System.out.println(players.currentPlayerName() + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}

		} else {

			players.advancePlace(roll);

			System.out.println(players.currentPlayerName()
					+ "'s new location is "
					+ players.getCurrentPlayerPlace());
			System.out.println("The category is " + currentCategory());
			askQuestion();
		}

	}

	private void askQuestion() {
		if (currentCategory() == "Pop")
			System.out.println(popQuestions.removeFirst());
		if (currentCategory() == "Science")
			System.out.println(scienceQuestions.removeFirst());
		if (currentCategory() == "Sports")
			System.out.println(sportsQuestions.removeFirst());
		if (currentCategory() == "Rock")
			System.out.println(rockQuestions.removeFirst());
	}

	private String currentCategory() {
		if (is0or4or8()) {
			return "Pop";
		} else if (is1or5or9()) {
			return "Science";
		} else if (is2or6or10()) {
			return "Sports";
		} else {
			return "Rock";
		}

	}

	private boolean is2or6or10() {
		int n = players.getCurrentPlayerPlace();
		return n == 2 || n == 6 || n == 10;
	}

	private boolean is1or5or9() {
		int n = players.getCurrentPlayerPlace();
		return n == 1 || n == 5 || n == 9;
	}

	private boolean is0or4or8() {
		int n = players.getCurrentPlayerPlace();
		return n == 0 || n == 4 || n == 8;
	}

	public boolean wasCorrectlyAnswered() {
		if (players.isCurrentPlayerinPenaltyBox()){
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				players.currentPlayerWonACoin();
				System.out.println(players.currentPlayerName()
						+ " now has "
						+ players.getCurrentPlayerCoins()
						+ " Gold Coins.");

				boolean winner = players.didPlayerWin();
				players.next();

				return winner;
			} else {
				players.next();
				return true;
			}



		} else {

			System.out.println("Answer was corrent!!!!");
			players.currentPlayerWonACoin();
			System.out.println(players.currentPlayerName()
					+ " now has "
					+ players.getCurrentPlayerCoins()
					+ " Gold Coins.");

			boolean winner = players.didPlayerWin();
			players.next();
			return winner;
		}
	}

	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(players.currentPlayerName() + " was sent to the penalty box");
		players.sendCurrentPlayertoPenaltyBox();

		players.next();
		return true;
	}

}
