package se575.kwic;

public class FileOutputCountAfter extends FileHeaderFooterDecorator implements OutputInterface {

    public FileOutputCountAfter(FileOutput fileOutput) {
        this.fileOutput = fileOutput;
    }
    public void writeOutput(String[] lines, String outputFilename) {
        int count = lines.length;
        String[] newlines = new String[lines.length + 1];
        for (int i = 0; i < lines.length; i++) newlines[i + 1] = lines[i];
        newlines[count] = "Line Count = " + count;
        for (int i = 0; i < newlines.length ; i++) System.out.println("line -- " + newlines[i]);  //debug
        fileOutput.writeOutput(newlines, outputFilename);
    }
}