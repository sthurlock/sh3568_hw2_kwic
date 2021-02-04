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

    @Test
    public void testCircularShiftStopWords1() {
        CircularShift shiftStrategy = new CircularShift();
        CircularShift circularShiftStopWords = new CircularShiftStopWords(shiftStrategy);
        String[] inputLines = new String[5];
/*        inputLines[0] = "This and That";
        inputLines[1] = "Yoda and Soda";
        inputLines[2] = "Today is Done";*/
        inputLines[0] = "What is gooder than god";
        inputLines[1] = "more evil than the devil";
        inputLines[2] = "the rich need it";
        inputLines[3] = "the poor have it";
        inputLines[4] = "and if you eat it you will die";
        String stopWords = "is,and,the";
        String[]outputLines = shiftStrategy.performShifts(inputLines);
/*        for (int i = 0; i < outputLines.length; i++) {
            System.out.println(outputLines[i]);
        }*/
        assertEquals(26,outputLines.length);

        circularShiftStopWords.setStopWords(stopWords);
        String[] decoratorOutputLines = circularShiftStopWords.performShifts(inputLines);
        assertEquals(21, decoratorOutputLines.length);
    }
}
