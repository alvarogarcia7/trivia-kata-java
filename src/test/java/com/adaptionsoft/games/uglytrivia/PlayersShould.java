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

        gameWithPlayers();

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


    private void gameWithPlayers() {
        IntStream.rangeClosed(1,1000).mapToObj(Integer::toString).forEach(players::addPlayer);
    }

}
