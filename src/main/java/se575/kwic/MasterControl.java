package se575.kwic;

public class MasterControl {
    public static void main(String[] args) {
        LineStorage lines = new LineStorage(args); // include config.properties here or just expect it to exist?
        lines.readInput();
        lines.createKWIC();
        lines.writeOutput();
    }
}
