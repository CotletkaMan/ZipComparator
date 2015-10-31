import zipcompare.ZipFileUtility;
import zipcompare.filefolder.DirectoryInfo;
import zipcompare.filefolder.FileInfo;
import zipcompare.filefolder.FileState;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.zip.ZipFile;

/**
 * Created by cotletkaman on 31.10.15.
 */
public class DirectoryInfoTest {
    private DirectoryInfo directoryInfo;
    private String pathToEqualsFile;
    private String pathToNotEqualsFile;
    private String pathToChangeFile;
    private String pathToRenameFile;
    private String pathToGeneralFile;

    @Before
    public void beginTest() throws IOException{
        pathToEqualsFile = ".//src//main//resources//EqualFile.zip";
        pathToNotEqualsFile = ".//src//main//resources//NotEqualFile.zip";
        pathToChangeFile = ".//src//main//resources//ChangeFile.zip";
        pathToRenameFile = ".//src//main//resources//RenameFile.zip";
        pathToGeneralFile = ".//src//main//resources//General.zip";

        directoryInfo = ZipFileUtility.getContentsZip(new ZipFile(pathToGeneralFile));
    }

    @Test
    public void testEqualsFile() throws IOException{
        Assert.assertTrue(directoryInfo.compareToObject(getFileInfoFromZip(pathToEqualsFile)).equals(FileState.EQUALS));
    }

    @Test
    public void testNotEqualsFile() throws IOException{
        Assert.assertTrue(directoryInfo.compareToObject(getFileInfoFromZip(pathToNotEqualsFile)).equals(FileState.NOT_EQUALS));
    }

    @Test
    public void testChangedFile() throws IOException{
        Assert.assertTrue(directoryInfo.compareToObject(getFileInfoFromZip(pathToChangeFile)).equals(FileState.CHANGED));
    }

    @Test
    public void testRenameFile() throws IOException{
        Assert.assertTrue(directoryInfo.compareToObject(getFileInfoFromZip(pathToRenameFile)).equals(FileState.RENAMED));
    }


    private FileInfo getFileInfoFromZip(String pathToFile) throws IOException{
        return ZipFileUtility.getContentsZip(new ZipFile(pathToFile)).get(0);
    }
}
