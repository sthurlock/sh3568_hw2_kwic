import org.junit.Test;

import javax.sound.sampled.Line;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

import static org.junit.Assert.assertEquals;


public class Test_MasterControl {

    @Test
    public void testInputWithFilename() throws IOException {
        String expected_value = "test: testInput";

        String file = "src/test/resources/input.txt";
        Path path = Paths.get(file);
        List<String> inputLines = Files.readAllLines(path);

        LineStorage lineStorage = new LineStorage();
        Input inputHandler = new Input();
        inputHandler.readInput(file, lineStorage);
        String[] output = lineStorage.lines();

        for (int i = 0; i < inputLines.size(); i++) {
            // remove punctuation from inputLines before comparing
            String inputWithNoPunctuation = inputLines.get(i);
            inputWithNoPunctuation = inputWithNoPunctuation.replaceAll("[,.?]", "");
            assertEquals(inputWithNoPunctuation, output[i] );
        }
    }

    @Test
    public void testInputfromConsole() throws IOException {
        Input inputHandler = new Input();
        inputHandler.readFromConsole();
    }

    @Test
    public void testCircularShift() throws IOException {
        LineStorage lineStorage = new LineStorage();
        String[] testInput = new String[2];
        testInput[0] = "Here is the first line";  // 5 words
        testInput[1] = "Second Line Here is"; // 4 words  --> total of 9 words
        lineStorage.addLines(testInput);
        CircularShift circularShifter = new CircularShift();
        LineStorage outputStorage = new LineStorage();

        circularShifter.performShifts(lineStorage, outputStorage);

        assertEquals((outputStorage.lines()).length,9);  // one line per number of words
        String[] outputStorageArray = outputStorage.lines();
        /*
        for (int i = 0; i < outputStorageArray.length; i++) {
            System.out.println(outputStorageArray[i]);
        }
        */
        assertEquals( "is Second Line Here", outputStorageArray[8]);  // check last line

    }
    @Test
    public void testAlphabetizer() throws IOException {
        // create input
        LineStorage lineStorage = new LineStorage();
        LineStorage outputStorage = new LineStorage();
        String[] testInput = new String[5];
        testInput[0] = "Zoo B"; // this should be the last line in the output storage array
        testInput[1] = "Too";
        testInput[2] = "Flew";
        testInput[3] = "Moo";
        testInput[4] = "Zoo A";
        lineStorage.addLines(testInput);

        Alphabetizer alphabetizer = new Alphabetizer();
        alphabetizer.sort(lineStorage, outputStorage);

        assertEquals((outputStorage.lines()).length,5);
        String[] outputStorageArray = outputStorage.lines();
        /*
        for (int i = 0; i < outputStorageArray.length; i++) {
            System.out.println(outputStorageArray[i]);
        }
        */
        assertEquals( "Zoo B", outputStorageArray[4]);

    }
    @Test
    public void testOutputToFile() throws IOException {
        // create input
        LineStorage lineStorage = new LineStorage();
        String[] testInput = new String[2];
        testInput[0] = "Here is the first line";
        testInput[1] = "Second Line Here is";
        lineStorage.addLines(testInput);

        String outputFile = "src/test/resources/test_output.txt";
        Output outputHandler = new Output();
        outputHandler.writeToOutput(lineStorage, outputFile);

        Path path = Paths.get(outputFile);
        List<String> inputLines = Files.readAllLines(path);
        assertEquals(inputLines.size(),2);
        assertEquals(inputLines.get(1), testInput[1] );

    }
    @Test
    public void testMasterControlWithFileInputandOutput() throws IOException {
        String expected_value = "Hello, world!";

        String infile = "src/test/resources/input.txt";
        String outfile = "src/test/resources/test_output_1.txt";

        MasterControl masterControl = new MasterControl();
        String[] args = new String[2];
        args[0] = infile;
        args[1] = outfile;
        masterControl.main(args);

        // get expected output and compare
        Path path1 = Paths.get("src/test/resources/expected_output.txt");
        List<String> expectedlines = Files.readAllLines(path1);
        Path path = Paths.get(outfile);
        List<String> actualLines = Files.readAllLines(path);

        for (int i = 0; i < expectedlines.size(); i++) {
            // System.out.println(expectedlines.get(i));
            // System.out.println(actualLines.get(i));
            assertEquals(expectedlines.get(i), actualLines.get(i) );
        }
    }
}
