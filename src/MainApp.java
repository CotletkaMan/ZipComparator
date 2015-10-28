import common.FileChoicer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MainApp {
    public static void main(String[] arg) throws IOException{
        FileChoicer fileChoicerZip1 = new FileChoicer();
        FileChoicer fileChoicerZip2 = new FileChoicer();
        BufferedWriter writer = new BufferedWriter(new FileWriter("report.txt"));
        writer.write(ZipFileUtility.DifferenceZipFiles(fileChoicerZip1.getFile().getName() , fileChoicerZip2.getFile().getName()));
        writer.close();
    }
}
