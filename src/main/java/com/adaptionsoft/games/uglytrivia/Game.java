package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class Game {
	private Players playersObject = new Players();
    private int[] places = new int[6];
    private int[] purses  = new int[6];
    private boolean[] inPenaltyBox  = new boolean[6];

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


	    playersObject.addPlayer(playerName);
		playersObject.initializePlace();
		purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;

	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + playersObject.playersSize());
		return true;
	}

	public int howManyPlayers() {
		return playersObject.playersSize();
	}

	public void roll(int roll) {
		System.out.println(playersObject.currentPlayer() + " is the current player");
		System.out.println("They have rolled a " + roll);

		if (inPenaltyBox[playersObject.getCurrentPlayer()]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;

				System.out.println(playersObject.currentPlayer() + " is getting out of the penalty box");
				playersObject.advancePlace(roll);

				System.out.println(playersObject.currentPlayer()
						+ "'s new location is "
						+ playersObject.getCurrentPlayerPlace());
				System.out.println("The category is " + currentCategory());
				askQuestion();
			} else {
				System.out.println(playersObject.currentPlayer() + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}

		} else {

			playersObject.advancePlace(roll);

			System.out.println(playersObject.currentPlayer()
					+ "'s new location is "
					+ playersObject.getCurrentPlayerPlace());
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
		if (playersObject.getCurrentPlayerPlace() == 0) return "Pop";
		if (playersObject.getCurrentPlayerPlace() == 4) return "Pop";
		if (playersObject.getCurrentPlayerPlace() == 8) return "Pop";
		if (playersObject.getCurrentPlayerPlace() == 1) return "Science";
		if (playersObject.getCurrentPlayerPlace() == 5) return "Science";
		if (playersObject.getCurrentPlayerPlace() == 9) return "Science";
		if (playersObject.getCurrentPlayerPlace() == 2) return "Sports";
		if (playersObject.getCurrentPlayerPlace() == 6) return "Sports";
		if (playersObject.getCurrentPlayerPlace() == 10) return "Sports";
		return "Rock";
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[playersObject.getCurrentPlayer()]){
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				purses[playersObject.getCurrentPlayer()]++;
				System.out.println(playersObject.currentPlayer()
						+ " now has "
						+ purses[playersObject.getCurrentPlayer()]
						+ " Gold Coins.");

				boolean winner = didPlayerWin();
				playersObject.setCurrentPlayer(playersObject.getCurrentPlayer() + 1);
				if (playersObject.getCurrentPlayer() == playersObject.playersSize()) playersObject.setCurrentPlayer(0);

				return winner;
			} else {
				playersObject.setCurrentPlayer(playersObject.getCurrentPlayer() + 1);
				if (playersObject.getCurrentPlayer() == playersObject.playersSize()) playersObject.setCurrentPlayer(0);
				return true;
			}



		} else {

			System.out.println("Answer was corrent!!!!");
			purses[playersObject.getCurrentPlayer()]++;
			System.out.println(playersObject.currentPlayer()
					+ " now has "
					+ purses[playersObject.getCurrentPlayer()]
					+ " Gold Coins.");

			boolean winner = didPlayerWin();
			playersObject.setCurrentPlayer(playersObject.getCurrentPlayer() + 1);
			if (playersObject.getCurrentPlayer() == playersObject.playersSize()) playersObject.setCurrentPlayer(0);

			return winner;
		}
	}

	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(playersObject.currentPlayer() + " was sent to the penalty box");
		inPenaltyBox[playersObject.getCurrentPlayer()] = true;

		playersObject.setCurrentPlayer(playersObject.getCurrentPlayer() + 1);
		if (playersObject.getCurrentPlayer() == playersObject.playersSize()) playersObject.setCurrentPlayer(0);
		return true;
	}


	private boolean didPlayerWin() {
		return !(purses[playersObject.getCurrentPlayer()] == 6);
	}

}
