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
	    places[howManyPlayers()] = 0;
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
		System.out.println(playersObject.currentPlayer(this.getCurrentPlayer()) + " is the current player");
		System.out.println("They have rolled a " + roll);

		if (inPenaltyBox[getCurrentPlayer()]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;

				System.out.println(playersObject.currentPlayer(this.getCurrentPlayer()) + " is getting out of the penalty box");
				places[getCurrentPlayer()] = places[getCurrentPlayer()] + roll;
				if (places[getCurrentPlayer()] > 11) places[getCurrentPlayer()] = places[getCurrentPlayer()] - 12;

				System.out.println(playersObject.currentPlayer(this.getCurrentPlayer())
						+ "'s new location is "
						+ places[getCurrentPlayer()]);
				System.out.println("The category is " + currentCategory());
				askQuestion();
			} else {
				System.out.println(playersObject.currentPlayer(this.getCurrentPlayer()) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}

		} else {

			places[getCurrentPlayer()] = places[getCurrentPlayer()] + roll;
			if (places[getCurrentPlayer()] > 11) places[getCurrentPlayer()] = places[getCurrentPlayer()] - 12;

			System.out.println(playersObject.currentPlayer(this.getCurrentPlayer())
					+ "'s new location is "
					+ places[getCurrentPlayer()]);
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
		if (places[getCurrentPlayer()] == 0) return "Pop";
		if (places[getCurrentPlayer()] == 4) return "Pop";
		if (places[getCurrentPlayer()] == 8) return "Pop";
		if (places[getCurrentPlayer()] == 1) return "Science";
		if (places[getCurrentPlayer()] == 5) return "Science";
		if (places[getCurrentPlayer()] == 9) return "Science";
		if (places[getCurrentPlayer()] == 2) return "Sports";
		if (places[getCurrentPlayer()] == 6) return "Sports";
		if (places[getCurrentPlayer()] == 10) return "Sports";
		return "Rock";
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[getCurrentPlayer()]){
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				purses[getCurrentPlayer()]++;
				System.out.println(playersObject.currentPlayer(this.getCurrentPlayer())
						+ " now has "
						+ purses[getCurrentPlayer()]
						+ " Gold Coins.");

				boolean winner = didPlayerWin();
				setCurrentPlayer(getCurrentPlayer() + 1);
				if (getCurrentPlayer() == playersObject.playersSize()) setCurrentPlayer(0);

				return winner;
			} else {
				setCurrentPlayer(getCurrentPlayer() + 1);
				if (getCurrentPlayer() == playersObject.playersSize()) setCurrentPlayer(0);
				return true;
			}



		} else {

			System.out.println("Answer was corrent!!!!");
			purses[getCurrentPlayer()]++;
			System.out.println(playersObject.currentPlayer(this.getCurrentPlayer())
					+ " now has "
					+ purses[getCurrentPlayer()]
					+ " Gold Coins.");

			boolean winner = didPlayerWin();
			setCurrentPlayer(getCurrentPlayer() + 1);
			if (getCurrentPlayer() == playersObject.playersSize()) setCurrentPlayer(0);

			return winner;
		}
	}


	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(playersObject.currentPlayer(this.getCurrentPlayer()) + " was sent to the penalty box");
		inPenaltyBox[getCurrentPlayer()] = true;

		setCurrentPlayer(getCurrentPlayer() + 1);
		if (getCurrentPlayer() == playersObject.playersSize()) setCurrentPlayer(0);
		return true;
	}

	private boolean didPlayerWin() {
		return !(purses[getCurrentPlayer()] == 6);
	}

	public int getCurrentPlayer() {
		return playersObject.getCurrentPlayer();
	}

	public void setCurrentPlayer(int currentPlayer) {
		playersObject.setCurrentPlayer(currentPlayer);
	}
}
