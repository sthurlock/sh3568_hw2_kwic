package se575.kwic;

import org.junit.Test;
import se575.kwic.*;
//import org.mockito.Mock;
//import org.mockito.Mockito;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.verify;

public class Test_MasterControl {

    public void makePropertiesFiles(String fileName, String[] keyValuePairs) {
        File outfile = new File(fileName);
        outfile.delete();
        FileWriter fw = null;
        try {
            fw = new FileWriter(fileName);
            for (int i = 0; i < keyValuePairs.length; i++) {
                // write each line to the file.
                System.out.print("line: " + keyValuePairs[i] + "\n");
                fw.write(keyValuePairs[i] + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadConfigAndReadFileInput()  {
        String[] args = new String[2];

        String infile = "src/test/resources/input.txt";
        String outfileName = "src/test/resources/output_test.txt";
        File outfile = new File(outfileName);
        outfile.delete();
        args[0] = infile; args[1] = outfileName;
        LineStorage lineStorage = new LineStorage(args);
        lineStorage.configureOptions("configuration.properties");
        int nLines = lineStorage.readInput();

        Path path = Paths.get(infile);
        List<String> inputLines = null;
        try {
            inputLines = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(inputLines.size(),nLines);
     }
    @Test
    public void testConfigureOptions() {
        String[] args = new String[2];
        args[0] = "src/test/resources/input.txt";
        args[1] = "src/test/resources/outputxxx.txt";
        File outfile = new File(args[1]);
        outfile.delete();
        LineStorage lineStorage = new LineStorage(args);
        //lineStorage.configureOptions("configuration.properties");
        lineStorage.readInput();
        assertEquals(lineStorage.getInputLines().length, 5);
    }

    @Test
    public void testMasterControlWithFileInputAndOutput() throws IOException {
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
            System.out.println(expectedlines.get(i));
            System.out.println(actualLines.get(i));
            assertEquals(expectedlines.get(i), actualLines.get(i) );
        }
    }


    //@Test
    public void testMasterControlWithConsoleInputAndFileOutput() throws IOException {
        String infile = null;
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
    public void runTest( String infile, String outfile, String props, String expectedOutput) throws IOException {
        MasterControl masterControl = new MasterControl();
        String[] args = new String[3];
        args[0] = infile;
        args[1] = outfile;
        args[2] = props;
        masterControl.main(args);

        // get expected output and compare
        Path path1 = Paths.get(expectedOutput);
        List<String> expectedlines = Files.readAllLines(path1);
        Path path = Paths.get(outfile);
        List<String> actualLines = Files.readAllLines(path);
        //assertEquals(expectedlines.size(), actualLines.size() );
        for (int i = 0; i < expectedlines.size(); i++) {
            System.out.println(expectedlines.get(i));
            System.out.println(actualLines.get(i));
            assertEquals(expectedlines.get(i), actualLines.get(i) );
        }
    }
    @Test
    public void testMasterControlFileInputFileOutput_caseInsensitve_vanilla() throws IOException {
        String infile = "src/test/resources/input.txt";
        String outfile = "src/test/resources/test_output_1.txt";
        String props = "test_vanilla.properties";
        String propsPath = "src/test/resources/" + props;
        String expectedOutput = "src/test/resources/expected_output.txt";
        String[] keyValues = new String[] {
                "input=se575.kwic.FileInput",
                "output=se575.kwic.FileOutput",
                "shift=se575.kwic.CircularShift",
                "sort=se575.kwic.AlphabetizerCaseInsensitive"
        };
        //makePropertiesFiles(propsPath, keyValues);  // Use pre-written file instead of writing here
        runTest( infile, outfile, props, expectedOutput);
    }

    @Test
    public void testMasterControlFileInputFileOutput_caseSensitve() throws IOException {
        String infile = "src/test/resources/input.txt";
        String outfile = "src/test/resources/test_output_1.txt";
        String props = "testMasterControlFileInputFileOutput_caseSensitve.properties";
        String propsPath = "src/test/resources/" + props;
        String expectedOutput = "src/test/resources/expected_output_caseSensitive.txt";
        String[] keyValues = new String[]{
                "input=se575.kwic.FileInput",
                "output=se575.kwic.FileOutput",
                "shift=se575.kwic.CircularShift",
                "sort=se575.kwic.AlphabetizerCaseSensitive"
        };
        //makePropertiesFiles(propsPath, keyValues);
        runTest(infile, outfile, props, expectedOutput);
    }
    @Test
    public void testMasterControlFileInputFileOutput_caseSensitve_stopwords() throws IOException {
        String infile = "src/test/resources/input.txt";
        String outfile = "src/test/resources/test_output_2.txt";
        String props = "testMasterControlFileInputFileOutput_caseSensitve_stopword.properties";
        String propsPath = "src/test/resources/" + props;
        String expectedOutput = "src/test/resources/expected_output_caseSensitive_noStopWords.txt";
        String[] keyValues = new String[]{
                "input=se575.kwic.FileInput",
                "output=se575.kwic.FileOutput",
                "shift=se575.kwic.CircularShift",
                "sort=se575.kwic.AlphabetizerCaseSensitive",
                "stopWords=and,is,the"
        };
        //makePropertiesFiles(propsPath, keyValues);
        runTest(infile, outfile, props, expectedOutput);
    }
    @Test
    public void testMasterControlFileInputFileOutput_caseSensitve_stopwords_linecountBefore() throws IOException {
        String infile = "src/test/resources/input.txt";
        String outfile = "src/test/resources/test_output_2.txt";
        String props = "test_LineCountBefore.properties";
        String propsPath = "src/test/resources/" + props;
        String expectedOutput = "src/test/resources/expected_output_caseSensitive_noStopWords_linecountbefore.txt";
        runTest(infile, outfile, props, expectedOutput);
    }

    @Test
    public void testMasterControlFileInputFileOutput_caseSensitve_stopwords_linecountAfter() throws IOException {
        String infile = "src/test/resources/input.txt";
        String outfile = "src/test/resources/test_output_2.txt";
        String props = "test_LineCountAfter.properties";
        String propsPath = "src/test/resources/" + props;
        String expectedOutput = "src/test/resources/expected_output_caseSensitive_noStopWords_linecountafter.txt";
        runTest(infile, outfile, props, expectedOutput);
    }




    //@Test
    public void testMasterControlConsoleInputConsoleOutput_caseInsensitve_vanilla() throws IOException {
        // input expected on console: What is gooder than god,|more evil than the devil,|the rich need it,|the poor have it,|and if you eat it you will die?
        String infile = "src/test/resources/input.txt";
        String outfile = "src/test/resources/test_output_1.txt";
        String props = "console_test.properties";
        String propsPath = "src/test/resources/" + props;
        String expectedOutput = "src/test/resources/expected_output.txt";
        /*  String[] keyValues = new String[] {
                "input=se575.kwic.ConsoleInput",
                "output=se575.kwic.ConsoleOutput",
                "shift=se575.kwic.CircularShift",
                "sort=se575.kwic.AlphabetizerCaseInsensitive"
        };
        makePropertiesFiles(propsPath, keyValues);*/

        MasterControl masterControl = new MasterControl();
        String[] args = new String[3];
        args[0] = infile;
        args[1] = outfile;
        args[2] = props;
        masterControl.main(args);

        // CHECK MANUALLY FOR NOW

    }

}
