package se575.kwic;

public abstract class ConsoleOutputDecorator extends ConsoleOutput implements OutputInterface {
    public ConsoleOutput consoleOutput;
    public abstract void writeOutput(String[] lines, String outputFilename);
}

