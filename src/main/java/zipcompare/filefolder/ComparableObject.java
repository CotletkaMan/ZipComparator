package zipcompare.filefolder;

/**
 * Interface defines a property comparison.
 * @param <T>
 */
public interface ComparableObject <T> {

    /**
     * Compares this object with the specified object for order.  Returns a
     * CHANGED, RENAMED, EQUALS or a NOT_EQUALS as this object was changed
     * , renamed to, equal or not equal than the specified object.
     * @param object    the object to be compared.
     * @return          {@link FileState}
     */
    FileState compareToObject(T object);

    /**
     * @return last object participating in a comparison
     */
    T getComparedObject();
}
