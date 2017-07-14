package com.adaptionsoft.games.uglytrivia;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PlayersShould {

    @Test
    public void be_empty_by_default() {
        assertThat(new Players().playersSize(), is(0));
    }

}
