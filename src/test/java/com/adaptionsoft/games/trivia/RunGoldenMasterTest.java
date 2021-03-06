package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.fileutils.FileUtils;
import com.adaptionsoft.games.uglytrivia.Game;
import com.gmaur.legacycode.legacyutils.output.MockSystemOutput;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;
import java.util.Random;

import static java.nio.file.StandardOpenOption.CREATE_NEW;
import static java.util.Collections.singletonList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RunGoldenMasterTest {

    private static Path executionPath = Paths.get("target/executions/");
    private static Path goldenMasterPath = Paths.get("src/test/resources/golden-master/");

    @BeforeClass
    public static void removeFolderContents() {
        FileUtils.emptyFolder(executionPath);
    }

    @Test
    public void create_executions() throws Exception {
        Random random = new Random(213L);
        for (int i = 1; i < 1000; i++) {
            int seed = random.nextInt();
            Path actualPath = executionPath.resolve("output_" + seed + ".actual");
            Path expectedPath = goldenMasterPath.resolve("output_" + seed + ".expected");
            writeExecution(seed, actualPath);
            assertThat(Files.readAllLines(expectedPath), is(Files.readAllLines(actualPath)));
        }
    }

    private void writeExecution(long seed, Path destination) throws IOException {
        MockSystemOutput inject = MockSystemOutput.inject();

        Random rand = new Random(seed);
        new GameRunner(rand).run();

        Files.write(destination, singletonList(inject.toString()), CREATE_NEW);

    }

}
