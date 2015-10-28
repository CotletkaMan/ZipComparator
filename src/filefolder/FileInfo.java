package filefolder;

/**
 * Created by cotletkaman on 28.10.15.
 */
public abstract class FileInfo implements ComparableObject<FileInfo>{
    public final String name;
    public final long   size;
    public final long crc;
    protected FileInfo(String name , long size , long crc){
        this.name = name;
        this.size = size;
        this.crc = crc;
    }
    public FileState compareToObject(FileInfo fileInfo){
        if(name.equals(fileInfo.name)){
            if(size == fileInfo.size && crc == fileInfo.crc)
                return FileState.EQUALS;
            return FileState.CHANCED;
        }
        else{
            if(size == fileInfo.size && crc == fileInfo.crc)
                return FileState.RENAMED;
            return FileState.NOT_EQUALS;
        }
    }
}
