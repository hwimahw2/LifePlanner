import java.io.InputStreamReader;
import java.util.*;

public class Selector {

    private List<String> menu = new ArrayList<>();
    DayPlan dayPlan;
    Map<String, String> subjectAndPlan;

    private void buildMenu(){
        menu.add("Сделайте выбор");
        menu.add("1) Планирование дня");
        for(int i = 0; i < menu.size(); i++){
            System.out.println(menu.get(i));
        }
    }

    public void select(){
        buildMenu();
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        int choice = scanner.nextInt();
        String in = null;
        int flag = 0;
        dayPlan = new DayPlan();
        subjectAndPlan = new HashMap<>();
        switch(choice){
            case(1):{
                String date = scanner.nextLine();
                while(true){
                    String field = scanner.nextLine();
                    while(true) {
                        in = scanner.nextLine();
                        if(in.indexOf('/') != -1){
                            flag = 1;
                            in.replace("/", "");
                        }
                        String[] affairs = in.split(":");
                        subjectAndPlan.put(affairs[1], affairs[2]);
                        if(flag == 1){
                            break;
                        }
                    }

                   if(in.indexOf('/') != -1){
                       flag = 1;
                       in.replace("/", "");
                   }
                   if(flag == 1){
                       break;
                   }
               }
                dayPlan.buildDayPlan(date, , subjectAndPlan);
            }
        }
        dayPlan.printDayPlan();
    }

}
