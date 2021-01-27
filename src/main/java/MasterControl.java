import javax.sound.sampled.Line;
import java.io.IOException;
import java.util.AbstractList;

public class MasterControl {

    public static void main(String[] args) throws IOException {
        System.out.println("Module: Master Control");
        LineStorage inputLineStorage = new LineStorage();
        LineStorage circularShiftLineStorage = new LineStorage();
        LineStorage alphabetizerLineStorage = new LineStorage();
        String outputFilename = new String();

        Input inputHandler = new Input();
        CircularShift shiftHandler = new CircularShift();
        Alphabetizer alphabetizerHandler = new Alphabetizer();
        Output outputHandler = new Output();

        inputHandler.readInput(args[0], inputLineStorage);
        shiftHandler.performShifts(inputLineStorage, circularShiftLineStorage);
        alphabetizerHandler.sort(circularShiftLineStorage, alphabetizerLineStorage);
        outputHandler.writeToOutput(alphabetizerLineStorage, args[1]);

    }
}
