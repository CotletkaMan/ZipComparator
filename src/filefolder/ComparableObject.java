package filefolder;

/**
 * Created by cotletkaman on 28.10.15.
 */
public interface ComparableObject <T> {
    FileState compareToObject(T object);
}
