package se575.kwic;

public class ConsoleOutputCountBefore extends ConsoleOutputDecorator implements OutputInterface {

    public ConsoleOutputCountBefore(ConsoleOutput consoleOutput) {
        this.consoleOutput = consoleOutput;
    }
    public void writeOutput(String[] lines, String outputConsolename) {
        int count = lines.length;
        String[] newlines = new String[lines.length + 1];
        newlines[0] = "Line Count = " + count;
        for (int i = 0; i < lines.length; i++) newlines[i+1] = lines[i];
        //for (int i = 0; i < newlines.length ; i++) System.out.println("line -- " + newlines[i]);  //debug
        consoleOutput.writeOutput(newlines, outputConsolename);
    }
}
