import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import static java.lang.Class.forName;

public class LineStorage {

    static String[] storage;
    static String[] inputLines;
    static String[] shiftedLines;
    static String[] sortedLines;
    static InputInterface inputSource;
    // make readInput() that uses inputSource.readInput()
    public int readInput(String filename) {
        System.out.println("in: LineStorage.readInput " + filename);
        try {
            String[] inputLines = inputSource.readInput(filename);
            System.out.println("inputLines.length =  " + inputLines.length);
            addLines(inputLines);
            System.out.println("after addLines");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return storage.length;
    }
    public int addLines(String[] inputArray) {
        System.out.println("in: LineStorage.addLines ");
        storage = new String[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            storage[i] = inputArray[i];
        }
        return storage.length;
    }
    public int addLinesFromList(List<String> inputList) {
        storage = new String[inputList.size()];
        for (int i = 0; i < inputList.size(); i++) {
            storage[i] = inputList.get(i);
        }
        return storage.length;
    }
    public String[] lines() {
        return storage;
    }
    public int numberOfLines() {
        return storage.length;
    }

    public void configureOptions(String parameterFile) throws IOException{
       // open parameterFile, read input value, select ConsoleInput or FileInput as InputSource
        Properties properties = new Properties();
        URL url = ClassLoader.getSystemResource("configuration.properties");
        System.out.println(url);
        Properties p = new Properties();
        p.load(new FileInputStream(new File(url.getFile())));
        System.out.println(p.getProperty("input"));
        // for now select fileInput:
        inputSource = new FileInput();

        // need to Dynamically load the class here
        //Class inputClass = forName("FileInput");
        //System.out.println(inputClass);
        //TxtInput i = (TxtInput) inputClass.getConstructor(String.class).newInstance("input.txt");
        //i.readLines();
    }
}
