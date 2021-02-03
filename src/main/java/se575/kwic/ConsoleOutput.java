import se575.kwic.OutputInterface;

public class ConsoleOutput implements OutputInterface {
    public void writeToOutput(LineStorage lineStorage, String outputFilename) //throws IOException
    {
        String[] lines = lineStorage.lines();

        // write to console
        for (int i = 0; i < lines.length; i++) {
            System.out.println(lines[i]);
        }
    }
}
