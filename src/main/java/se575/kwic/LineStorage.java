package se575.kwic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class LineStorage {

    //String[] storage;
    String[] inputLines;
    String[] shiftedLines;
    String[] sortedLines;
    String inputFilename;
    String outputFilename;
    public InputInterface inputStrategy;
    public OutputInterface outputStrategy;
    public AlphabetizerInterface alphabetizerStrategy;
    public CircularShift shiftStrategy;

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
        } catch (IOException e) {
            e.printStackTrace();
        }
/*        try {
            inputLines = inputStrategy.readInput(inputFilename);
        } catch (IOException e) { e.printStackTrace(); }*/
        return inputLines.length;
    }


    public void createKWIC() {
        System.out.println("In: LineStorage.createKWIC");
        CircularShift shiftHandler = new CircularShift();
        shiftedLines = shiftHandler.performShifts(inputLines);
        sortedLines = alphabetizerStrategy.sort(shiftedLines);
    }

    public void writeOutput() {
        System.out.println("In: LineStorage.writeOutput");
        outputStrategy.writeOutput(sortedLines, outputFilename);
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

    public void method () {
        //ObjectLoader x = new ObjectLoader();

    }

    public void configureOptions(String parameterFile) {
       // open parameterFile, read input value, select ConsoleInput or FileInput as InputSource


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
        System.out.println("output = " + p.getProperty("sort"));
        // Strategy Pattern - use input from properties file to select OUTPUT behavior
        // TODO: these need to have try/catch and default to known class name if class not found!
        ObjectLoader objectLoader = new ObjectLoader();
        inputStrategy = (InputInterface) objectLoader.loadObject(p.getProperty("input"));
        alphabetizerStrategy = (AlphabetizerInterface) objectLoader.loadObject(p.getProperty("sort"));

        String header = p.getProperty("header");
        String footer = p.getProperty("footer");
        if (header == null && footer == null) {
            outputStrategy = (OutputInterface) new FileOutput();
        } else {
            FileHeaderFooterOutput fileHeaderFooterOutput = new FileHeaderFooterOutput(new FileOutput());
            fileHeaderFooterOutput.setHeader(header);
            fileHeaderFooterOutput.setFooter(footer);
            outputStrategy = (OutputInterface) fileHeaderFooterOutput;
        }

        // select CircularShift Strategy --> if stopwords exists, then use CircularShiftStopWords
        shiftStrategy = new CircularShift();
        if (p.getProperty("stopWords") != null) {
            shiftStrategy.setStopWords(p.getProperty("stopWords"));
            shiftStrategy = new CircularShiftStopWords(shiftStrategy);
        }

    }
}
