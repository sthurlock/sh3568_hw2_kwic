package se575.kwic;

import java.util.Arrays;

public class AlphabetizerCaseInsensitive implements AlphabetizerInterface {
    public String[] sort(String[] lines) {
        Arrays.sort(lines, String.CASE_INSENSITIVE_ORDER);
        return(lines);
    }
}
