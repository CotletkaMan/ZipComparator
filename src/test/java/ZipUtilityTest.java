import zipcompare.ZipFileUtility;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;


public class ZipUtilityTest {
    private String pathToZip;
    private String pathToFile;

    @Before
    public void beginTest() throws IOException{
        pathToZip = ".//src//main//resources//one.zip";
        pathToFile = ".//src//main//resources//FileToFileChoicerTest";
    }

    //2 потому что в архиве 2 файла
    @Test
    public void getContentsZipTest() throws IOException{
        Assert.assertTrue(ZipFileUtility.getContentsZip(new ZipFile(pathToZip)).size() == 2);
    }

    @Test(expected = ZipException.class)
    public void differenceZipFilesTest() throws IOException{
        ZipFileUtility.differenceZipFiles(pathToZip , pathToFile);
    }

}
