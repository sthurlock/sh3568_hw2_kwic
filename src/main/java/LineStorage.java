import java.util.List;

public class LineStorage {

    static String[] storage;
    public int addLines(String[] inputArray) {
        storage = new String[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            storage[i] = inputArray[i];
        }
        return storage.length;
    }
    public int addLinesFromList(List<String> inputList) {
        storage = new String[inputList.size()];
        for (int i = 0; i < inputList.size(); i++) {
            storage[i] = inputList.get(i);
        }
        return storage.length;
    }
    public String[] lines() {
        return storage;
    }
    public int numberOfLines() {
        return storage.length;
    }
}
