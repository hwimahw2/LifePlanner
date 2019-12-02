import java.util.*;

public class DayPlan {

    private String date;
    private Map<String, Map<String, String>> fieldSubjectAndPlan;

    DayPlan(String date){
        this.date = date;
        fieldSubjectAndPlan = new HashMap<>();
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, Map<String, String>> getFieldSubjectAndPlan() {
        return fieldSubjectAndPlan;
    }

    public void addFieldSubjectPlan(String field, Map<String, String> subjectAndPlan){
        fieldSubjectAndPlan.put(field, subjectAndPlan);
    }

    public void printDayPlan(){
        for(Map.Entry<String, Map<String, String>> entry:fieldSubjectAndPlan.entrySet()){
            System.out.println(entry.getKey());
            for(Map.Entry<String, String> innerEntry:entry.getValue().entrySet()){
                System.out.println(innerEntry.getKey() + " " + innerEntry.getValue());
            }
        }
    }

    public void build(String Date){
        fieldSubjectAndPlan = new HashMap();


    }

}
