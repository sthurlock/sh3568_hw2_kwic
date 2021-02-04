package se575.kwic;

public abstract class CircularShiftDecorator extends CircularShift {
    public CircularShift circularShift;
    public abstract String[] performShifts(String[] linesArray);
}
