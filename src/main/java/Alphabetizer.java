import java.util.Arrays;

public class Alphabetizer {
    public void sort(LineStorage circularShiftLineStorage, LineStorage alphabetizerLineStorage) {
        String[] lines = circularShiftLineStorage.lines();
        Arrays.sort(lines, String.CASE_INSENSITIVE_ORDER);
        for (int a = 0; a < lines.length; a++) {
            System.out.println(lines[a]);
        }
        alphabetizerLineStorage.addLines(lines);
    }
}
