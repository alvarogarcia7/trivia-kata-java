package com.adaptionsoft.games.trivia;

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
        try {
            shallowDeleteFolder(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void create_a_single_actual_case() throws Exception {
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

        Files.write(goldenMasterPath.resolve("output_" + seed + ".actual"), singletonList(inject.toString()), CREATE_NEW);
    }

    private static void shallowDeleteFolder(Path path) throws IOException {
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs)
                    throws IOException {
                Files.delete(file);
                return CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(final Path file, final IOException e) {
                return handleException(e);
            }

            private FileVisitResult handleException(final IOException e) {
                e.printStackTrace(); // replace with more robust error handling
                return TERMINATE;
            }

            @Override
            public FileVisitResult postVisitDirectory(final Path dir, final IOException e)
                    throws IOException {
                return TERMINATE;
            }
        });
    }

}
