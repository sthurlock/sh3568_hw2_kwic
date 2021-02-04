package se575.kwic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class ConsoleInput implements InputInterface {

// pass in LineStorage object & filename, null filename means read from console
// read input from console or filename

    public String[] readInput(String filename) throws IOException {

        String[] consoleInput = null;
        try (InputStreamReader in = new InputStreamReader(System.in);
             BufferedReader buffer = new BufferedReader(in))
        {
            String line;
            // read one line that has all lines separated by pipe delimiters, then split
            line = buffer.readLine();
            line = line.replaceAll("[,.?]", "");
            consoleInput = line.split("[|]");
            return consoleInput;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return consoleInput;
    }

}

