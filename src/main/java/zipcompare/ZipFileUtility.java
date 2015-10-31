package zipcompare;

import zipcompare.filefolder.*;
import zipcompare.report.*;

import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Utility class to handle ZIP files.
 */
public class ZipFileUtility{
    /**
     * Create report of difference ZIP files. This is folder must
     * provide one level of nesting
     * @param pathToZip1    path the first ZIP file
     * @param pathToZip2    path the second ZIP file
     * @return              report consisting of differences of files
     * @throws IOException  if occur I/O error
     */
    public static String differenceZipFiles(String pathToZip1, String pathToZip2) throws IOException{
        if(pathToZip1.equals(pathToZip2))
            return "Compare same file\n";
        DirectoryInfo infoZip1 = getContentsZip(new ZipFile(pathToZip1));
        DirectoryInfo infoZip2 = getContentsZip(new ZipFile(pathToZip2));

        GeneralReport generalReport = new GeneralReport();
        FileReport reportZip1 = new FileReport(pathToZip1);
        FileReport reportZip2 = new FileReport(pathToZip2);

        compareDirectoryInfo(reportZip1 , infoZip1 , reportZip2 , infoZip2);

        generalReport.add(reportZip1);
        generalReport.add(reportZip2);

        return generalReport.printReport();
    }

    /**
     * Finds all information files are in ZIP file
     * @param zip           is open ZipInputStream
     * @return              collection FileInfo files
     * @throws IOException  if zip stream isn't opened
     */
    public static DirectoryInfo getContentsZip(ZipFile zip) throws IOException {
        DirectoryInfo directoryInfo = new DirectoryInfo();
        Enumeration<? extends ZipEntry> entryEnumeration = zip.entries();
        for (ZipEntry zipEntry; entryEnumeration.hasMoreElements(); ) {
            zipEntry = entryEnumeration.nextElement();
            directoryInfo.add(new ZipFileInfo(zipEntry));
        }
        return directoryInfo;
    }

    /**
     * Fill simple reports in during compassion ZIP file.
     * @param reportZip1    the report of the first ZIP file
     * @param infoZip1      the ZipInputStream of the first ZIP file
     * @param reportZip2    the report of the second ZIP file
     * @param infoZip2      the ZipInputStream of the second ZIP file
     */
    private static void compareDirectoryInfo(FileReport reportZip1 , DirectoryInfo infoZip1 , FileReport reportZip2 , DirectoryInfo infoZip2){
        for(FileInfo info : infoZip1){
            FileState state = infoZip2.compareToObject(info);
            FileInfo info2 = infoZip2.getComparedObject();
            switch (state){
                case CHANGED:
                    reportZip1.fileChanged(info.name);
                    reportZip2.fileChanged(info2.name);
                    infoZip2.remove(info2);
                    break;
                case RENAMED:
                    reportZip1.fileRenamed(info.name);
                    reportZip2.fileRenamed(info2.name);
                    infoZip2.remove(info2);
                    break;
                case NOT_EQUALS:
                    reportZip1.fileRemoved(info.name);
                    reportZip2.addMessage(" ");
                    break;
                case EQUALS:
                    infoZip2.remove(info2);
            }
        }
        for(FileInfo info : infoZip2){
            reportZip2.fileAdd(info.name);
            reportZip1.addMessage(" ");
        }
    }
}
