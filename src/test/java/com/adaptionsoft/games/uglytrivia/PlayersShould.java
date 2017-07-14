package com.adaptionsoft.games.uglytrivia;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PlayersShould {

    private Players players;

    @Before
    public void setUp() throws Exception {
        players = new Players();
    }

    @Test
    public void be_empty_by_default() {
        assertThat(players.playersSize(), is(0));
    }

    @Test
    public void add_a_player() {

        players.addPlayer("player1");

        assertThat(players.playersSize(), is(1));
    }

    @Test
    public void add_many_players() {

        aNewGameWithPlayers(1000);

        assertThat(players.playersSize(), is(1000));
    }

    @Test
    public void advances_the_players() {

        players.addPlayer("player 1");
        players.addPlayer("player 2");

        assertThat(players.currentPlayerName(), is("player 1"));

        players.next();

        assertThat(players.currentPlayerName(), is("player 2"));
    }

    @Test
    public void rotates_the_players() {

        players.addPlayer("player 1");
        players.addPlayer("player 2");

        assertThat(players.currentPlayerName(), is("player 1"));

        players.next();

        assertThat(players.currentPlayerName(), is("player 2"));

        players.next();

        assertThat(players.currentPlayerName(), is("player 1"));
    }

    @Test
    public void the_player_starts_at_place_0() {
        assertThat(players.getCurrentPlayerPlace(), is(0));
    }

    @Test
    public void advance_the_players_place() {
        players = aNewGameWithPlayers(1);

        players.advancePlace(1);

        assertThat(players.getCurrentPlayerPlace(), is(1));
    }

    @Test
    public void cumulate_advances_the_players_place() {
        players = aNewGameWithPlayers(2);

        players.advancePlace(1);
        players.advancePlace(2);

        assertThat(players.getCurrentPlayerPlace(), is(3));

        players.next();

        players.advancePlace(4);

        assertThat(players.getCurrentPlayerPlace(), is(4));

        players.next(); //wrap back to the beginning
        assertThat(players.getCurrentPlayerPlace(), is(3));

        players.next();
        assertThat(players.getCurrentPlayerPlace(), is(4));
    }


    private Players aNewGameWithPlayers(int amount) {
        Players players = new Players();
        IntStream.rangeClosed(1, amount).mapToObj(Integer::toString).forEach(players::addPlayer);
        return players;
    }

}
