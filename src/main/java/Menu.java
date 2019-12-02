import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<String> menu = new ArrayList<>();

    public void buildPrintMenu() {
        for (int i = 0; i < menu.size(); i++) {
            System.out.println(menu.get(i));
        }
    }

    private void build() {
        menu.add("Сделайте выбор");
        menu.add("1) Составление LifePlan");
        menu.add("2) Планирование дня");

    }

    private void print(){
        for (int i = 0; i < menu.size(); i++) {
            System.out.println(menu.get(i));
        }
    }
}
