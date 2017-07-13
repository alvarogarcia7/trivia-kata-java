package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Game;
import com.gmaur.legacycode.legacyutils.output.MockSystemOutput;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import static java.nio.file.StandardOpenOption.CREATE_NEW;
import static java.util.Collections.singletonList;

public class CreateGoldenMasterTest {

    private static Path goldenMasterPath = Paths.get("src/test/resources/golden-master/");

    @BeforeClass
    public static void removeFolderContents() {
        Path path = goldenMasterPath;
        try {
            Files.deleteIfExists(path);
            Files.createDirectory(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void create_a_single_actual_case() throws Exception {
        MockSystemOutput inject = MockSystemOutput.inject();

        boolean notAWinner;
        Game aGame = new Game();

        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");

        Random rand = new Random(1L);

        do {
            aGame.roll(rand.nextInt(5) + 1);
            if (rand.nextInt(9) == 7) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }
        } while (notAWinner);

        Files.write(goldenMasterPath.resolve("test1.actual"), singletonList(inject.toString()), CREATE_NEW);
    }
}
