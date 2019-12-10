import java.util.*;

public class DayPlan {

    private String date;
    private Map<String, String> subjectAndPlan;

    DayPlan(String date, Map<String, String> subjectAndPlan){
        this.date = date;
        this.subjectAndPlan = subjectAndPlan;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, String> getSubjectAndPlan() {
        return subjectAndPlan;
    }

}
