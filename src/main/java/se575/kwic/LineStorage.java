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
    String propertiesFile;
    public InputInterface inputStrategy;
    public OutputInterface outputStrategy;
    public AlphabetizerInterface alphabetizerStrategy;
    public CircularShift shiftStrategy;

   public LineStorage(String[] args) {
        // --input inputFile  --output outputFile  --properties propFile --> ideal
        // for now:
        System.out.println("--In: LineStorage constructor");
/*       for (int i = 0; i < args.length; i++) {
           System.out.println("   arg[" + i + "] = {" + args[i] + "}");
       }*/
        inputFilename  = args[0];
        outputFilename = args[1];
        if (args.length==3) {
            propertiesFile = args[2];
        } else {
            propertiesFile = "configuration.properties";
        }
        System.out.println("  inputFilename: " + inputFilename);
        System.out.println("  outputFilename: " + outputFilename);
        System.out.println("  propertiesFile: " + propertiesFile);
        configureOptions(propertiesFile);
        System.out.println("--In: LineStorage constructor - END");
    }

    public int readInput()  {
        System.out.println("--In: LineStorage.readInput");
        try {
            inputLines = inputStrategy.readInput(inputFilename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputLines.length;
    }

    public void createKWIC() {
        System.out.println("--In: LineStorage.createKWIC");
        shiftedLines = shiftStrategy.performShifts(inputLines);
        sortedLines = alphabetizerStrategy.sort(shiftedLines);
    }

    public void writeOutput() {
        System.out.println("--In: LineStorage.writeOutput");
        outputStrategy.writeOutput(sortedLines, outputFilename);
    }

    public String[] getInputLines() {
        return inputLines;
    }
    public String[] getShiftedLines() {
        return shiftedLines;
    }
    public String[] getSortedLines() {
        return sortedLines;
    }

    // This method is the Context for the Strategy pattern where all of the correct objects are selected
    public void configureOptions(String parameterFile) {
       // open parameterFile, read input value, select ConsoleInput or FileInput as InputSource
        System.out.println("--In: ConfigureOptions: ." + parameterFile + ".");

        Properties properties = new Properties();
        URL url = ClassLoader.getSystemResource(parameterFile);
        //System.out.println("Working Directory = " + System.getProperty("user.dir"));
        System.out.println("--propertyFile url: " + url);
        Properties p = new Properties();
        try {
            p.load(new FileInputStream(new File(url.getFile())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("  input = " + p.getProperty("input"));
        System.out.println("  output = " + p.getProperty("output"));
        System.out.println("  sort = " + p.getProperty("sort"));
        // Strategy Pattern - use input from properties file to select OUTPUT behavior
        // TODO: these need to have try/catch and default to known class name if class not found!
        ObjectLoader objectLoader = new ObjectLoader();

        // I'm consistently getting this error when trying to load the ConsoleInput, for now using Ifs to get it to work
        // java.lang.NoSuchMethodException: se575.kwic.ConsoleInput.<init>()
        /*inputStrategy = (InputInterface) objectLoader.loadObject(p.getProperty("input"));
        if (inputStrategy == null) {
            System.out.println("ERROR: InputStrategy is NULL!");
        }*/
        if (p.getProperty("input").matches(".*Console.*")) {
            inputStrategy = (InputInterface) new ConsoleInput();
        } else {
            inputStrategy = (InputInterface) new FileInput();
        }
        alphabetizerStrategy = (AlphabetizerInterface) objectLoader.loadObject(p.getProperty("sort"));

        // get basic Output Strategy
        outputStrategy = (OutputInterface)  objectLoader.loadObject(p.getProperty("output"));
        // Now decorate based on the options

        if (p.getProperty("output").matches(".*File.*")) {
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
            if (p.getProperty("addLineCountBefore") != null) {
                FileOutputCountBefore fileOutputCountBefore = new FileOutputCountBefore((FileOutput) outputStrategy);
                outputStrategy = (OutputInterface) fileOutputCountBefore;
            }

            if (p.getProperty("addLineCountAfter") != null) {
                FileOutputCountAfter fileOutputCountAfter = new FileOutputCountAfter((FileOutput) outputStrategy);
                outputStrategy = (OutputInterface) fileOutputCountAfter;
            }
        } else {
            if (p.getProperty("addLineCountBefore") != null) {
                ConsoleOutputCountBefore consoleOutputCountBefore = new ConsoleOutputCountBefore((ConsoleOutput) outputStrategy);
                outputStrategy = (OutputInterface) consoleOutputCountBefore;
            } else {
                // just do After OR Before - otherwise the after count includes the count added before!
                if (p.getProperty("addLineCountAfter") != null) {
                    ConsoleOutputCountAfter consoleOutputCountAfter = new ConsoleOutputCountAfter((ConsoleOutput) outputStrategy);
                    outputStrategy = (OutputInterface) consoleOutputCountAfter;
                }
            }
        }



        // select CircularShift Strategy --> if stopwords exists, then use CircularShiftStopWords
        shiftStrategy = new CircularShift();
        if (p.getProperty("stopWords") != null) {
            System.out.println("  stopWords = " + p.getProperty("stopWords"));
            shiftStrategy = new CircularShiftStopWords(shiftStrategy);
            shiftStrategy.setStopWords(p.getProperty("stopWords"));
        }

    }
}
