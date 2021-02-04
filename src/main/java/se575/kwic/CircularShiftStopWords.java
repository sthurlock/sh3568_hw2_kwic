package se575.kwic;

import java.util.Arrays;
import java.util.regex.Pattern;

public class CircularShiftStopWords extends CircularShiftDecorator {

    public CircularShiftStopWords(CircularShift circularShift) {
        this.circularShift = circularShift;
    }

    public String[] performShifts(String[] linesArray) {

        // first perform normal shift
        String[] shiftedLines = circularShift.performShifts(linesArray);
        String[] outputLines = new String[shiftedLines.length];

        // Now loop through and remove lines that have the stop words as the first word.
        // first split stop words string into array
        String[] stopWordsArray = stopWords.split("[,]");
        int outputLinesIndex=0;
        Boolean found;
        for (int i = 0; i < shiftedLines.length ; i++) {
            found = Boolean.FALSE;
            for (int j = 0; j < stopWordsArray.length; j++) {
                //System.out.println(shiftedLines[i] + " -- " + stopWordsArray[j]);
                String regex = "^" + stopWordsArray[j] + ".*$";  // need to match the whole damn line!
                //System.out.println(regex);
                //if (shiftedLines[i].matches("^" + stopWordsArray[j])) {
                if (Pattern.matches(regex, shiftedLines[i]) == Boolean.TRUE) {
                    found = Boolean.TRUE;
                    //System.out.println("..Found");
                    break;
                }
            }
            if (found == Boolean.FALSE) {
                outputLines[outputLinesIndex++] = shiftedLines[i];
            }
        }
        String[] outputArray = Arrays.copyOfRange(outputLines,0, outputLinesIndex );
/*        System.out.println("---outputArray:");
        for (int i = 0; i < outputArray.length; i++) {
            System.out.println(outputArray[i]);
        }*/
        return outputArray;

    }
}

