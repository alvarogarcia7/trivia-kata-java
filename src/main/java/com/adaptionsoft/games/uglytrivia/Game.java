package com.adaptionsoft.games.uglytrivia;

public class Game {
	private final Questions questions;
	private Players players;

	private boolean isGettingOutOfPenaltyBox;

    public  Game(){
    	questions = Questions.standardSet();
		players = new Players();
	}

	public String createRockQuestion(int index){
		return questions.createRockQuestion(index);
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
				System.out.println("The category is " + questions.currentCategory(players.getCurrentPlayerPlace()));
				questions.askQuestion(players.getCurrentPlayerPlace());
			} else {
				System.out.println(players.currentPlayerName() + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}

		} else {

			players.advancePlace(roll);

			System.out.println(players.currentPlayerName()
					+ "'s new location is "
					+ players.getCurrentPlayerPlace());
			System.out.println("The category is " + questions.currentCategory(players.getCurrentPlayerPlace()));
			questions.askQuestion(players.getCurrentPlayerPlace());
		}

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
