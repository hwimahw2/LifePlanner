import java.util.ArrayList;

public class Field {

    private String name;
    private ArrayList<Subject> subjects = new ArrayList<>();

    Field(String name){
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }


}
