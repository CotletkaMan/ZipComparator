package report;

import java.io.File;
/**
 * Created by cotletkaman on 27.10.15.
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
