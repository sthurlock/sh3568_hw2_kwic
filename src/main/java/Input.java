import javax.swing.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Input {

    // pass in LineStorage object & filename, null filename means read from console
    // read input from console or filename

    public void readInput(String inputFilename, LineStorage lineStorage) throws IOException {
        if (inputFilename == null) {
            lineStorage.addLinesFromList(readFromConsole());
        } else {
            Path path1 = Paths.get("src/test/resources/input.txt");
            List<String> inputLines = Files.readAllLines(path1);
            String[] inputNoPunctuation = new String[inputLines.size()];
            for (int i = 0; i < inputLines.size(); i++) {
                System.out.println(inputLines.get(i));
                inputNoPunctuation[i] = (inputLines.get(i)).replaceAll("[,.?]", "");
            }
            lineStorage.addLines(inputNoPunctuation);
        }
    }


    public List<String> readFromConsole() throws IOException
    {
        int inputType = 1;
        List<String> consoleInput = new ArrayList<>();
        if (inputType==1) {
            try (InputStreamReader in = new InputStreamReader(System.in);
                 BufferedReader buffer = new BufferedReader(in)) {
                String line;
                while ((line = buffer.readLine()) == null) {
                    System.out.println(line);
                    consoleInput.add(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            List<String> tokens = new ArrayList<>();
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                tokens.add(scanner.next());
            }
            scanner.close();

        }
        return consoleInput;
    }
}


