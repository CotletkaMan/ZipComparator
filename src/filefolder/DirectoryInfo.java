package filefolder;

import java.util.ArrayList;

public class DirectoryInfo extends ArrayList<FileInfo> implements ComparableObject<FileInfo>{
    public FileState compareToObject(FileInfo fileInfo){
        for(FileInfo file : this){
            FileState fileState = file.compareToObject(fileInfo);
            if(fileState != FileState.NOT_EQUALS)
                return fileState;
        }
        return FileState.NOT_EQUALS;
    }
}
