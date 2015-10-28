package common;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by cotletkaman on 27.10.15.
 */
public class FileChoicer {
    private File file;

    public FileChoicer(String FilePath) throws FileNotFoundException{
        File file = new File(FilePath);
        if(file.exists())
            this.file = file;
        else
            throw new FileNotFoundException("File isn't exist");
    }

    public FileChoicer(){
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(null);
        if(result == JFileChooser.APPROVE_OPTION)
            this.file = chooser.getSelectedFile();
        else
            throw new IllegalStateException("Error in work Dialog window");
    }

    public File getFile(){
        return file;
    }

    public String getFileName(){
        return file.getName();
    }
}
