package se575.kwic;

public abstract class FileHeaderFooterDecorator extends FileOutput implements OutputInterface {
    public FileOutput fileOutput;
    public abstract void writeOutput(String[] lines, String outputFilename);
}

