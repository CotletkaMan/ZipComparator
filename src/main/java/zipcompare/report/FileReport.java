package zipcompare.report;

/**
 * Wrap to <code>Report</code> class.
 * He need for generate messages for
 * <code>General report</code>
 */
public class FileReport extends Report {
    public FileReport(String name){
        super(name);
    }

    public void fileChanged(String name){
        addMessage("* " + name);
    }

    public void fileRemoved(String name){
        addMessage("- " + name);
    }

    public void fileAdd(String name){
        addMessage("+ " + name);
    }

    public void fileRenamed(String name){
        addMessage("# " + name);
    }
}
