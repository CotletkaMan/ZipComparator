import zipcompare.common.FilesChoicer;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FileChoicerTest {
    private String pathToFile;
    private String notValidString;
    private String nullString;
    private FilesChoicer filesChoicer;

    @Before
    public void beginTest() {
        pathToFile = ".//src//main//resources//FileToFileChoicerTest";
        notValidString = ".//roadToNowhere";
        nullString = null;
    }

    //If file not exists method throw Exception
    @Test
    public void validFileChoicer() throws IOException {
        filesChoicer = new FilesChoicer(pathToFile);
    }

    @Test(expected = FileNotFoundException.class)
    public void notValidFileChoicer() throws IOException{
        filesChoicer = new FilesChoicer(notValidString);
    }

    @Test(expected = NullPointerException.class)
    public void nullFileChoicer() throws IOException{
        filesChoicer = new FilesChoicer(nullString);
    }
}
