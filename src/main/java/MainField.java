import java.util.ArrayList;

public class MainField {

    private String name;
    private ArrayList<Field> fields = new ArrayList<>();

    MainField(String name){
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }
}
