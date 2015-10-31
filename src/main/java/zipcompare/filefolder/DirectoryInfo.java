package zipcompare.filefolder;

import java.util.ArrayList;

/**
 * Collection contains property files. The compassion saves the matched object.
 */
public class DirectoryInfo extends ArrayList<FileInfo> implements ComparableObject<FileInfo>{
    private FileInfo lastComparedObject = null;

    public FileState compareToObject(FileInfo fileInfo){
        for(FileInfo file : this){
            FileState fileState = file.compareToObject(fileInfo);
            if(fileState != FileState.NOT_EQUALS) {
                lastComparedObject = file;
                return fileState;
            }
        }
        return FileState.NOT_EQUALS;
    }

    public FileInfo getComparedObject(){
        return lastComparedObject;
    }
}
