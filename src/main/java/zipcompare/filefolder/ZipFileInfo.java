package zipcompare.filefolder;

import java.util.zip.ZipEntry;

/**
 * Describe create FileInfo by ZipEntry.
 */
public class ZipFileInfo extends FileInfo {
    public ZipFileInfo(ZipEntry entry){
        super(entry.getName() , entry.getSize() , entry.getCrc());
    }
}
