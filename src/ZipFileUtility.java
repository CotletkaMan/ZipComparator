import filefolder.DirectoryInfo;
import filefolder.FileInfo;
import filefolder.ZipFileInfo;
import report.ControlReport;
import report.FileReport;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by cotletkaman on 28.10.15.
 */
public class ZipFileUtility {
    private static ControlReport controlReport;

    public static String DifferenceZipFiles(String pathToZip1 , String pathToZip2) throws IOException{
        if(pathToZip1.equals(pathToZip2))
            return "Compare same file\n";
        ZipInputStream zip1 = openZip(pathToZip1);
        ZipInputStream zip2 = openZip(pathToZip2);
        DirectoryInfo infoZip1 = getContentsZip(zip1);
        DirectoryInfo infoZip2 = getContentsZip(zip2);

        controlReport = new ControlReport();
        FileReport reportZip1 = new FileReport(pathToZip1);
        FileReport reportZip2 = new FileReport(pathToZip2);

        compareDirectoryInfo(reportZip1 , infoZip1 , reportZip2 , infoZip2);

        controlReport.add(reportZip1);
        controlReport.add(reportZip2);

        return controlReport.printReport();
    }

    public static ZipInputStream openZip(String pathToZip) throws IOException{
        byte checkZip[] = new byte[2];
        ZipInputStream input = new ZipInputStream(new FileInputStream(pathToZip));
        input.read(checkZip);
        if(!Arrays.equals(checkZip , new byte[]{'P','K'})) //compare magic number
            throw new IllegalArgumentException("This is file not Jar or Zip\n");
        input.reset();
        return input;
    }

    public static DirectoryInfo getContentsZip(ZipInputStream zip) throws IOException{
        ZipEntry entry;
        DirectoryInfo directoryInfo = new DirectoryInfo();
        while((entry = zip.getNextEntry()) != null){
            directoryInfo.add(new ZipFileInfo(entry));
            zip.closeEntry();
        }
        return directoryInfo;
    }

    private static void compareDirectoryInfo(FileReport reportZip1 , DirectoryInfo infoZip1 , FileReport reportZip2 , DirectoryInfo infoZip2){
        for(FileInfo info : infoZip1){
            switch (infoZip2.compareToObject(info)){
                case CHANCED:
                    reportZip1.fileChanged(info.name);
                    reportZip2.fileChanged(info.name);
                    infoZip2.remove(info);
                    break;
                case RENAMED:
                    reportZip1.fileRenamed(info.name);
                    reportZip2.fileRenamed(info.name);
                    infoZip2.remove(info);
                    break;
                case NOT_EQUALS:
                    reportZip1.fileRemoved(info.name);
                    reportZip2.addMessage("");
                    break;
            }
        }
        for(FileInfo info : infoZip2){
            reportZip2.fileAdd(info.name);
        }
    }
}
