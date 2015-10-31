package zipcompare.report;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Store simple <code>Report</code> and responsible for their printing.
 */

public class GeneralReport extends HashSet<Report>{
    private String columnSeparator;
    private char rowSeparator;

    public GeneralReport(Set<Report> reports){
        columnSeparator = "|";
        rowSeparator = '_';
    }

    public GeneralReport(){
        columnSeparator = "|";
        rowSeparator = '_';
    }

    public void addReport(Report report){
        add(report);
    }

    public void setColumnSeparator(char sym){
        columnSeparator = Character.toString(sym);
    }

    public void setRowSeparator(char sym){
        rowSeparator = sym;
    }

    /**
     * Create general report of inner simple reports.
     * @return general report like this:
     *      | <name_report1> | <name_report2> | *** |
     *      |_______________________________________|
     *      |   message1     |   message2     | *** |
     */
    public String printReport() {
        if(!isEmpty()) {
            String generalReport = printHead();
            return generalReport + printBody();
        }
        return "";
    }


    /**
     * Create string with left alignment and width define in <code>width</code>
     * @param report    message in report
     * @param width     length new string plus two space symbols
     * @return          new string for general report
     */
    private String createLineReportForPrint(String report , int width){
        return String.format(" %-" + (width) + "s " , report) + columnSeparator;
    }

    /**
     * Finds the maximum size of the report contained reports
     * @return  maximum size report
     */
    private int findMaxSize(){
        int size = 0;
        for(Report report : this)
            if(report.size() > size)
                size = report.size();
        return size;
    }

    /**
     * Create string consisting of characters _ long length
     * @param   length string
     * @return  line separator String
     */
    private String createLineSeparator(int length){
        final char buffer[] = new char[length];
        Arrays.fill(buffer, rowSeparator);
        return columnSeparator + new String(buffer) + columnSeparator;
    }

    /**
     * Make first string in general report. It contains name reports.
     * @return  string like this:
     *      | <name_report1> | <name_report2> | *** |
     *      |_______________________________________|
     */
    private String printHead() {
        String headString = columnSeparator;
        for (Report report : this) {
            headString += createLineReportForPrint(report.getNameReport() , report.getRowWidth());
        }
        return headString + "\n" + createLineSeparator(headString.length() - 2) + "\n";
    }

    /**
     * Create main part of general report. It contains all messages in inner reports.
     * @return string like this:
     *          |   message1.1     |   message2.1     | *** |
     *          |   message1.2     |   message2.2     | *** |
     *          |   message1.3     |   message2.3     | *** |
     */
    private String printBody(){
        StringBuilder body = new StringBuilder();
        int maxSize = findMaxSize();
        for(int i = 0 ; i < maxSize ; i++){
            String bodyString = columnSeparator;
            for(Report report : this)
                bodyString += createLineReportForPrint(report.get(i) , report.getRowWidth());
            body.append(bodyString + "\n");
        }
        return body.toString();
    }

}
