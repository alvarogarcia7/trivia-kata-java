package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Game;
import com.gmaur.legacycode.legacyutils.output.MockSystemOutput;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Random;

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.TERMINATE;
import static java.nio.file.StandardOpenOption.CREATE_NEW;
import static java.util.Collections.singletonList;

public class CreateGoldenMasterTest {

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
