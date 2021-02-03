package se575.kwic;

import se575.kwic.OutputInterface;

import java.io.FileWriter;
import java.io.IOException;

public class FileOutput implements OutputInterface {
    public void writeOutput(String[] lines, String outputFilename) throws IOException
    {
        //String[] lines = lineStorage.lines();
        FileWriter fw = new FileWriter(outputFilename);
        for (int i = 0; i < lines.length; i++) {
            // write each line to the file.
            fw.write(lines[i] + "\n");
        }
        fw.close();
    }
}
