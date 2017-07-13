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
		System.out.println(players.currentPlayer() + " is the current player");
		System.out.println("They have rolled a " + roll);

		if (players.isCurrentPlayerinPenaltyBox()) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;

				System.out.println(players.currentPlayer() + " is getting out of the penalty box");
				players.advancePlace(roll);

				System.out.println(players.currentPlayer()
						+ "'s new location is "
						+ players.getCurrentPlayerPlace());
				System.out.println("The category is " + currentCategory());
				askQuestion();
			} else {
				System.out.println(players.currentPlayer() + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}

		} else {

			players.advancePlace(roll);

			System.out.println(players.currentPlayer()
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
		if (players.getCurrentPlayerPlace() == 0) return "Pop";
		if (players.getCurrentPlayerPlace() == 4) return "Pop";
		if (players.getCurrentPlayerPlace() == 8) return "Pop";
		if (players.getCurrentPlayerPlace() == 1) return "Science";
		if (players.getCurrentPlayerPlace() == 5) return "Science";
		if (players.getCurrentPlayerPlace() == 9) return "Science";
		if (players.getCurrentPlayerPlace() == 2) return "Sports";
		if (players.getCurrentPlayerPlace() == 6) return "Sports";
		if (players.getCurrentPlayerPlace() == 10) return "Sports";
		return "Rock";
	}

	public boolean wasCorrectlyAnswered() {
		if (players.isCurrentPlayerinPenaltyBox()){
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				players.currentPlayerWonACoin();
				System.out.println(players.currentPlayer()
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
			System.out.println(players.currentPlayer()
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
		System.out.println(players.currentPlayer() + " was sent to the penalty box");
		players.sendCurrentPlayertoPenaltyBox();

		players.next();
		return true;
	}

}
