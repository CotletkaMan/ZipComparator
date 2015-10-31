package zipcompare.common;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class opens the file specified in the <code>filePath</code> or
 * open  ChooseDialogWindow.
 */
public class FilesChoicer{
    private List<File> files = new ArrayList<File>();

    /**
     * Open the file specified in the <code>filePath</code>
     * @param filePath                  path to file
     * @throws FileNotFoundException    if file isn't exists
     */
    public FilesChoicer(String filePath) throws FileNotFoundException {
        initializeOneFile(filePath);
    }

    /**
     * Open more than one file.
     * @param filepaths                 array paths to file
     * @throws FileNotFoundException    if file isn't exists
     */
    public FilesChoicer(String[] filepaths) throws FileNotFoundException{
        for(String filepath : filepaths)
            initializeOneFile(filepath);
    }

    /**
     * Show dialog window, allows choose several files.
     * After save in local storage <code>files</code>.
     */
    public FilesChoicer() {
        files = Arrays.asList(chooseFilesInFrame());
    }

    /**
     * Show dialog window and allow choose <code>count</code> files.
     * After save in local storage <code>files</code>.
     * @param count     count file to opening
     */
    public FilesChoicer(int count) {
        files = Arrays.asList(chooseFilesInFrame(count));
    }

    /**
     * Show dialog window, allows choose similarly files.
     * @return array chooses files.
     */
    public static File[] chooseFilesInFrame() {
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION)
            return chooser.getSelectedFiles();
        return null;
    }

    /**
     * Show dialog window, allows choose count files.
     * @param count count file to opening.
     * @return      array chooses files.
     */
    public static File[] chooseFilesInFrame(int count){
        File[] files = chooseFilesInFrame();
        if(files.length != count)
            throw new IllegalArgumentException("You not choose files\n");
        return files;
    }

    public List<File> getFiles() {
        return files;
    }

    /**
     * Open file and check him exists.
     * @param filePath                  path to file.
     * @throws FileNotFoundException    if file isn't exist
     */
    private void initializeOneFile(String filePath) throws FileNotFoundException{
        File file = new File(filePath);
        if (file.exists())
            files.add(file);
        else
            throw new FileNotFoundException("File " + file.getName() + " exist");
    }
}
