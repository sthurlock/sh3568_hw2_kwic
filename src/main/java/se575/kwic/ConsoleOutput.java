package se575.kwic;

import se575.kwic.OutputInterface;

public class ConsoleOutput implements OutputInterface {
    public void writeOutput(String[] lines, String outputFilename)
    {
        System.out.println("In: ConsoleOutput");
        for (int i = 0; i < lines.length; i++) {
            System.out.println(lines[i]);
        }
    }
}
