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
                DayPlan dayPlan = new DayPlan();
                Map<String, String> subjectAndPlan = null;
                String date = scanner.nextLine();
                dayPlan.setDate(date);
                while(true){
                    System.out.println("Введите направление");
                    String field = scanner.nextLine();
                    subjectAndPlan = new HashMap<>();
                    while(true){
                        System.out.println("Чем именно займетесь сегодня?");
                        in = scanner.nextLine();
                        if(in.indexOf('/') != -1){
                            if(in.length() == 1){
                                break;
                            }
                            flag = 1;
                            in.replace("/", "");
                        }
                        String[] affairs = in.split(":");
                        subjectAndPlan.put(affairs[0], affairs[1]);
                        if(flag == 1){
                            break;
                        }
                    }
                    dayPlan.addFieldSubjectPlan(field, subjectAndPlan);

                   if(in.indexOf('/') != -1){
                       flag = 1;
                       in.replace("/", "");
                   }
                   if(flag == 1){
                       break;
                   }
               }
                dayPlan.printDayPlan();
            }
        }

    }

}
