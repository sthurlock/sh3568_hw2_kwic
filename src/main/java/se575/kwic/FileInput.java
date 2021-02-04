package se575.kwic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileInput implements InputInterface {
    //public List<String> readFromConsole() throws IOException
    public String[] readInput(String filename) throws IOException
    {
        System.out.println("In: FileInput.readInput()");
        Path path1 = Paths.get(filename);
        List<String> inputLines = Files.readAllLines(path1);

        String[] inputNoPunctuation = new String[inputLines.size()];
        for (int i = 0; i < inputLines.size(); i++) {
            //System.out.println(inputLines.get(i));
            inputNoPunctuation[i] = (inputLines.get(i)).replaceAll("[,.?]", "");
        }

        return inputNoPunctuation;
    }
}