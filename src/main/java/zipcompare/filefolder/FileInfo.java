package zipcompare.filefolder;

/**
 * Describes the properties of the file need to start comparing their content
 */
public abstract class FileInfo implements ComparableObject<FileInfo>{
    public final String name;
    public final long   size;

    //check sum file.
    public final long crc;

    protected FileInfo(String name , long size , long crc){
        this.name = name;
        this.size = size;
        this.crc = crc;
    }

    /**
     * {@link ComparableObject}
     */
    public FileState compareToObject(FileInfo fileInfo){
        if(name.equals(fileInfo.name)){
            if(size == fileInfo.size && crc == fileInfo.crc)
                return FileState.EQUALS;
            return FileState.CHANGED;
        }
        else{
            if(size == fileInfo.size && crc == fileInfo.crc)
                return FileState.RENAMED;
            return FileState.NOT_EQUALS;
        }
    }

    /**
     * {@link ComparableObject}
     * @return  <code>this</code> object because it
     *          always participates the  comparison.
     */
    public FileInfo getComparedObject(){
        return this;
    }
}
