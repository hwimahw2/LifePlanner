package ru.nsd;

import java.util.*;

public class DayPlan {

    private String date;
    private Map<String, String> subjectAndPlan;

    DayPlan(String date, Map<String, String> subjectAndPlan){
        this.date = date;
        this.subjectAndPlan = subjectAndPlan;
    }

    public String getDate() {
        return date;
    }

    public Map<String, String> getSubjectAndPlan() {
        return subjectAndPlan;
    }
}
