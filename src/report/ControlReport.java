package report;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cotletkaman on 27.10.15.
 */
public class ControlReport extends HashSet<Report> implements Reportable{
    private String columnSeparator;
    private char rowSeparator;

    public ControlReport(Set<Report> reports){
        columnSeparator = "|";
        rowSeparator = '_';
    }

    public ControlReport(){}

    public void addReport(Report report){
        add(report);
    }

    public void setColumnSeparator(char sym){
        columnSeparator = Character.toString(sym);
    }

    public void setRowSeparator(char sym){
        rowSeparator = sym;
    }

    public String printReport(){
        if(!isEmpty()) {
            String generalReport = printHead();
            return generalReport + printBody();
        }
        return "";
    }

    private String createLineReportForPrint(String report , int width){
        return String.format("%-" + width + "s" , report) + columnSeparator;
    }

    private int findMaxSize(){
        int size = 0;
        for(Report report : this)
            if(report.size() > size)
                size = report.size();
        return size;
    }

    private String createLineSeparator(int length){
        final char buffer[] = new char[length];
        Arrays.fill(buffer, rowSeparator);
        return new String(buffer);
    }

    private String printHead() {
        String headString = columnSeparator;
        for (Report report : this) {
            headString += createLineReportForPrint(report.getNameReport() , report.getRowWidth());
        }
        return headString + "\n" + createLineSeparator(headString.length());
    }

    private String printBody(){
        StringBuilder body = new StringBuilder();
        int maxSize = findMaxSize();
        for(int i = 0 ; i < maxSize ; i++){
            String bodyString = columnSeparator;
            for(Report report : this)
                columnSeparator += createLineReportForPrint(report.get(i) , report.getRowWidth());
            body.append(columnSeparator + "\n");
        }
        return body.toString();
    }

}
