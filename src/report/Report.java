package report;

import java.util.ArrayList;

public class Report {
    private ArrayList<String> messages;
    private String handlerReport;
    private int rowWidth;

    public Report(String name){
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
