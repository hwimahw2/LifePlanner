import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DayPlan {

    private String date;
    private Map<String, Map<String, String>> fieldSubjectAndPlan;

    DayPlan(){
        fieldSubjectAndPlan = new HashMap<>();
    }

    public Map<String, Map<String, String>> getFieldSubjectAndPlan() {
        return fieldSubjectAndPlan;
    }

    public void buildDayPlan(String date, String field, Map<String, String> subjectAndPlan){
        this.date = date;
        fieldSubjectAndPlan.put(field, subjectAndPlan);
    }

    public void printDayPlan(){
        System.out.println(date);
        for(int i = 0; i < fieldSubjectAndPlan.size(); i++){
            System.out.println(fieldSubjectAndPlan.get(i));
        }
    }
}
