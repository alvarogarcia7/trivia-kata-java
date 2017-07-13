package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.fileutils.FileUtils;
import com.adaptionsoft.games.uglytrivia.Game;
import com.gmaur.legacycode.legacyutils.output.MockSystemOutput;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.SecureRandom;
import java.util.Random;

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.TERMINATE;
import static java.nio.file.StandardOpenOption.CREATE_NEW;
import static java.util.Collections.singletonList;

public class CreateGoldenMaster {

    private static Path goldenMasterPath = Paths.get("src/test/resources/golden-master/");

    @BeforeClass
    public static void removeFolderContents() {
        Path path = goldenMasterPath;
        FileUtils.emptyFolder(path);

    }

    @Test
    public void create_test_cases() throws Exception {
        Random random = new Random(213L);
        for (int i = 1; i < 1000; i++) {
            writeGoldenMaster(random.nextInt());
        }
    }

    private void writeGoldenMaster(long seed) throws IOException {
        MockSystemOutput inject = MockSystemOutput.inject();

        boolean notAWinner;
        Game aGame = new Game();

        Random rand = new Random(seed);
        int numberOfPlayers = rand.nextInt(4);
        aGame.add("Chet");
        aGame.add("Pat");
        for (int i = 0; i < numberOfPlayers; i++) {
            aGame.add("Player " + i);
        }

        do {
            aGame.roll(rand.nextInt(5) + 1);
            if (rand.nextInt(9) == 7) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }
        } while (notAWinner);

        Files.write(goldenMasterPath.resolve("output_" + seed + ".expected"), singletonList(inject.toString()), CREATE_NEW);
    }

}
