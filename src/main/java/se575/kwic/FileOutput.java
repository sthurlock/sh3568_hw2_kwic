package se575.kwic;

import se575.kwic.OutputInterface;

import java.io.FileWriter;
import java.io.IOException;

public class FileOutput implements OutputInterface {
    public String header;
    public String footer;
    public void setHeader(String h) {
        header = h;
    }
    public void setFooter(String f) {
        footer = f;
    }
    public void writeOutput(String[] lines, String outputFilename)
    {

        FileWriter fw = null;
        try {
            fw = new FileWriter(outputFilename);
            for (int i = 0; i < lines.length; i++) {
                // write each line to the file.
                System.out.print("line: " + lines[i] + "\n");
                fw.write(lines[i] + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
