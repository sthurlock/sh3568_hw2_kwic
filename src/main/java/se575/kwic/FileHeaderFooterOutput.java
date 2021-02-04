package se575.kwic;

public class FileHeaderFooterOutput extends FileHeaderFooterDecorator implements OutputInterface {

    public FileHeaderFooterOutput(FileOutput fileOutput) {
        this.fileOutput = fileOutput;
    }
    public void writeOutput(String[] lines, String outputFilename) {
        String[] newlines = new String[lines.length + 2];
        newlines[0] = this.header;
        for (int i = 0; i < lines.length; i++) newlines[i + 1] = lines[i];
        newlines[lines.length + 1] = this.footer;
        for (int i = 0; i < newlines.length ; i++) System.out.println("line -- " + newlines[i]);  //debug
        fileOutput.writeOutput(newlines, outputFilename);
    }
}
