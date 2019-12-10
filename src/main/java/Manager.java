import ru.nsd.Node;

import java.io.InputStreamReader;
import java.util.*;

public class Manager {

    Menu menu = new Menu();
    LifePlan lifePlan = new LifePlan();
    DayPlan dayPlan;
    Map<String, String> subjectAndPlan;



    public void select() {
        int exit = 0;
        do {
            menu.buildPrintMenu();
            Scanner scanner = new Scanner(new InputStreamReader(System.in));
            int choice = scanner.nextInt();
            subjectAndPlan = new HashMap<>();
            switch (choice) {
                case (1): {
                    lifePlan.print();
                    System.out.println();
                    break;
                }
                case (2): {
                    lifePlan.print();
                    System.out.println();
                    System.out.println("Введите предмет:описание");
                    String date = scanner.nextLine();
                    Map<String, String> subjectAndPlan = new HashMap<>();
                    while (true) {
                        int flag = 0;
                        String stringSubjectPlan = scanner.nextLine();
                        if (stringSubjectPlan.indexOf('/') != -1) {
                            flag = 1;
                            if (stringSubjectPlan.length() == 1) {
                                break;
                            }
                            stringSubjectPlan.replace("/", "");
                        }
                        String[] split = stringSubjectPlan.split(":");
                        for(int i = 0; i < split.length; i++){
                            System.out.println(split[i]);
                        }
                        subjectAndPlan.put(split[0], split[1]);
                        subjectAndPlan = new HashMap<>();
                        if (flag == 1) {
                            break;
                        }
                    }
                    System.out.println(12121);
                    DayPlan dayPlan = new DayPlan(date, subjectAndPlan);
                    lifePlan.setPlanInLeavesFromDayPlan(subjectAndPlan);
                    ArrayList<Node> arrayList = lifePlan.getLeaves();
                    System.out.println(arrayList.size());
                    for(int i = 0; i < arrayList.size(); i++){
                        System.out.println(3333);
                        Node nod = arrayList.get(i);
                        if(nod.getPlan() != null)
                            System.out.println(nod.getPlan());
                    }
                    break;
                }
                case (3): {
                    exit = 1;
                    break;
                }
            }
        }while(exit == 0);
    }
}
