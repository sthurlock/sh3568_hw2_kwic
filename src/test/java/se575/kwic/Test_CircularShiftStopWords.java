package se575.kwic;

import org.junit.Test;
import se575.kwic.*;
//import org.mockito.Mock;
//import org.mockito.Mockito;


import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

import static org.junit.Assert.assertEquals;
public class Test_CircularShiftStopWords {
    @Test
    public void testCircularShiftStopWords() {
        CircularShift shiftStrategy = new CircularShift();
        CircularShift circularShiftStopWords = new CircularShiftStopWords(shiftStrategy);
        String[] inputLines = new String[3];
        inputLines[0] = "This and That";
        inputLines[1] = "Yoda and Soda";
        inputLines[2] = "Today is Done";
        String stopWords = "and,is";
        String[]outputLines = shiftStrategy.performShifts(inputLines);
        assertEquals(outputLines.length, 9);

        circularShiftStopWords.setStopWords(stopWords);
        String[] decoratorOutputLines = circularShiftStopWords.performShifts(inputLines);
        assertEquals(6, decoratorOutputLines.length);
    }
}
