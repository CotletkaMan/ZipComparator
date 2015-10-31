package zipcompare;

import zipcompare.common.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.List;

public class ZipCompare {
    public static void main(String[] arg) throws IOException{
        FilesChoicer fileChoicerZip;
        switch(arg.length){
            case  2: fileChoicerZip = new FilesChoicer(arg);
                     break;
            default: fileChoicerZip = new FilesChoicer(2);
        }
        List<File> files = fileChoicerZip.getFiles();
        BufferedWriter writer = new BufferedWriter(new FileWriter("report.txt"));
        writer.write(ZipFileUtility.differenceZipFiles(files.get(0).getAbsolutePath(), files.get(1).getAbsolutePath()));
        writer.close();
    }
}
