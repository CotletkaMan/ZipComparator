package zipcompare.report;

import java.util.ArrayList;
import java.util.List;

/**
 * This is simple report. He supply add strings, get contains string
 * and monitors the width of the report.
 */
public class Report {
    private List<String> messages;
    private String handlerReport;
    private int rowWidth;

    public Report(String name){
        messages = new ArrayList<String>();
        rowWidth = name.length();
        handlerReport = name;
    }

    public boolean addMessage(String text){
        if(rowWidth < text.length())
            rowWidth = text.length();
        return messages.add(text);
    }

    public String get(int index){
        if(index >= 0 && index < messages.size())
            return messages.get(index);
        return null;
    }

    public int getRowWidth(){
        return rowWidth;
    }

    public int size(){
        return messages.size();
    }

    @Override
    public String toString(){
       return handlerReport;
    }

    public String getNameReport(){
        return handlerReport;
    }
}
