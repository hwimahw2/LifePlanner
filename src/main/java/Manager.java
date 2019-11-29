import java.io.InputStreamReader;
import java.util.*;

public class Manager {

    Menu menu = new Menu();
    DayPlan dayPlan;
    Map<String, String> subjectAndPlan;



    public void select() {
        menu.buildPrintMenu();
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        int choice = scanner.nextInt();
        String in = null;
        int flag = 0;
        dayPlan = new DayPlan();
        subjectAndPlan = new HashMap<>();
        switch (choice) {
            case (1): {
                int n = scanner.nextInt();
                int m = scanner.nextInt();
                //Matrix matrix = new Matrix();

                System.out.println("Основное направление");

            }
            case (2): {
                DayPlan dayPlan = new DayPlan();
                Map<String, String> subjectAndPlan = null;
                String date = scanner.nextLine();
                dayPlan.setDate(date);
                while (true) {
                    System.out.println("Направление");
                    String field = scanner.nextLine();
                    subjectAndPlan = new HashMap<>();
                    if (field.indexOf('/') != -1) {
                        flag = 1;
                        if (field.length() == 1) {
                            break;
                        }
                        in.replace("/", "");
                    }
                    while (true) {
                        System.out.println("Дисциплина:Уточнение");
                        in = scanner.nextLine();
                        if (in.indexOf('/') != -1) {
                            if (in.length() == 1) {
                                break;
                            }
                            flag = 1;
                            in.replace("/", "");
                        }
                        String[] affairs = in.split(":");
                        subjectAndPlan.put(affairs[0], affairs[1]);
                        if (flag == 1) {
                            break;
                        }
                    }
                    dayPlan.addFieldSubjectPlan(field, subjectAndPlan);
                    if (flag == 1) {
                        break;
                    }
                }
                dayPlan.printDayPlan();
            }
        }
    }
}
