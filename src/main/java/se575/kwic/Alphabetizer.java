package se575.kwic;
import java.util.Arrays;

public class Alphabetizer {
    public String[] sort(String[] lines) {
        Arrays.sort(lines, String.CASE_INSENSITIVE_ORDER);
        return(lines);
    }
}
