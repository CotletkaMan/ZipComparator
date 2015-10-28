package filefolder;

import java.util.zip.ZipEntry;

/**
 * Created by cotletkaman on 28.10.15.
 */
public class ZipFileInfo extends FileInfo {
    public ZipFileInfo(ZipEntry entry){
        super(entry.getName() , entry.getSize() , entry.getCrc());
    }
}
