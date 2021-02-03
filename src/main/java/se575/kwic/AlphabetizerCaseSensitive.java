package se575.kwic;

import java.util.Arrays;

public class AlphabetizerCaseSensitive implements AlphabetizerInterface {
    public String[] sort(String[] lines) {
        Arrays.sort(lines);
        return(lines);
    }
}
