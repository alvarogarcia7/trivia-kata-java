package com.adaptionsoft.games.uglytrivia;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PlayersShould {

    @Test
    public void be_empty_by_default() {
        assertThat(new Players().playersSize(), is(0));
    }

    @Test
    public void add_a_player() {
        Players players = new Players();

        players.addPlayer("player1");

        assertThat(players.playersSize(), is(1));
    }

    @Test
    public void add_many_players() {
        Players players = new Players();

        IntStream.rangeClosed(1,1000).mapToObj(Integer::toString).forEach(players::addPlayer);

        assertThat(players.playersSize(), is(1000));
    }

}
