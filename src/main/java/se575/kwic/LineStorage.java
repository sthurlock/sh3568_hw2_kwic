package se575.kwic;

import se575.kwic.FileInput;
import se575.kwic.InputInterface;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import static java.lang.Class.forName;

public class LineStorage {

    String[] storage;
    String[] inputLines;
    String[] shiftedLines;
    String[] sortedLines;
    String inputFilename;
    String outputFilename;
    public InputInterface inputStrategy;
    public OutputInterface outputStrategy;

    // make readInput() that uses inputSource.readInput()

   public LineStorage(String[] args) {
        // --input inputFile  --output outputFile  --properties propFile --> ideal
        // for now:
        System.out.println("In: LineStorage constructor");
        inputFilename  = args[0];
        outputFilename = args[1];
        configureOptions("configuration.properties");
    }

    public int readInput()  {

        System.out.println("In: LineStorage.readInput");
        try {
            inputLines = inputStrategy.readInput(inputFilename);
        } catch (IOException e) { e.printStackTrace(); }
        return inputLines.length;
    }

    public void writeOutput() {
        System.out.println("In: LineStorage.writeOutput");
        try {
            outputStrategy.writeOutput(sortedLines, outputFilename);
        } catch (IOException e) { e.printStackTrace(); }
    }
    public void createKWIC() {
        System.out.println("In: LineStorage.createKWIC");
        CircularShift shiftHandler = new CircularShift();
        Alphabetizer alphabetizerHandler = new Alphabetizer();
        shiftedLines = shiftHandler.performShifts(inputLines);
        sortedLines = alphabetizerHandler.sort(shiftedLines);
    }

/*    public int addLines(String[] inputArray) {
        System.out.println("In: LineStorage.addLines");
        storage = new String[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            storage[i] = inputArray[i];
        }
        return storage.length;
    }
    public int addLinesFromList(List<String> inputList) {
        System.out.println("In: LineStorage.addLinesFromList");
        storage = new String[inputList.size()];
        for (int i = 0; i < inputList.size(); i++) {
            storage[i] = inputList.get(i);
        }
        return storage.length;
    }*/
    public String[] getInputLines() {
        return inputLines;
    }
    public String[] getShiftedLines() {
        return shiftedLines;
    }
    public String[] getSortedLines() {
        return sortedLines;
    }

    public void configureOptions(String parameterFile) {
       // open parameterFile, read input value, select ConsoleInput or FileInput as InputSource
        ObjectLoader objectLoader = new ObjectLoader();
        Properties properties = new Properties();
        URL url = ClassLoader.getSystemResource(parameterFile);

        Properties p = new Properties();
        try {
            p.load(new FileInputStream(new File(url.getFile())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("input = " + p.getProperty("input"));
        System.out.println("output = " + p.getProperty("output"));
        // Strategy Pattern - use input from properties file to select OUTPUT behavior
        // TODO: these need to have try/catch and default to known class name if class not found!
        inputStrategy = (InputInterface) objectLoader.loadObject(p.getProperty("input"));
        outputStrategy = (OutputInterface) objectLoader.loadObject(p.getProperty("output"));

    }
}
