import java.util.Arrays;

public class CircularShift {
    public void performShifts(LineStorage inputLineStorage, LineStorage circularShiftLineStorage) {
        int nLines = inputLineStorage.numberOfLines();
        String[] linesArray = inputLineStorage.lines();
        // create an array to hold the arrays of the shifted lines
        String[][] allShiftedLines = new String[nLines][];
        int totalWords = 0;
        for (int i = 0; i < nLines; i++) {
            String line = linesArray[i];
            // split string by space
            // loop through words and create string with each word first
            String[] words = line.split("\\s+");
            String[] both = Arrays.copyOf(words, words.length + words.length);
            System.arraycopy(words, 0, both, words.length, words.length);
            String[] shiftedLines = new String[words.length];
            totalWords = totalWords + words.length;
            for (int j = 0; j < words.length; j++) {
                String shiftedWord = "";
                for (int k = j; k < j + words.length; k++) {
                    if (k==j) {
                        shiftedWord = both[k];
                    } else {
                        shiftedWord = shiftedWord + " " + both[k];
                    }
                }
                //System.out.println(shiftedWord);
                shiftedLines[j] = shiftedWord;
            }
            allShiftedLines[i] = shiftedLines;

        }
        // make array of size totalWords
        // copy each array into the fullList, then add it to the LineStorage
        String[] fullList = new String[totalWords];
        int fullListIndex = 0;
        for (int i = 0; i < nLines; i++) {
            //System.out.println(allShiftedLines[i]);
            for (int j = 0; j < allShiftedLines[i].length; j++) {
                //System.out.println(allShiftedLines[i][j]);
                fullList[fullListIndex] = allShiftedLines[i][j];
                fullListIndex++;
            }
        }
        circularShiftLineStorage.addLines(fullList);
    }
}
