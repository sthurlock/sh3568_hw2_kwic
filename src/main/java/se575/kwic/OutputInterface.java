import java.io.IOException;

public interface OutputInterface {
    public void writeToOutput(LineStorage lineStorage, String outputFilename) throws IOException;
}

