import ru.nsd.Node;

import java.io.*;
import java.util.*;

public class Manager {

    Menu menu = new Menu();
    LifePlan lifePlan = new LifePlan();
    DayPlan dayPlan;
    Map<String, String> subjectAndPlan;


    private void writeToFileDayPlan() throws Exception{
        File file = new File("./src/main/resources/input.txt");
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try{
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            write(lifePlan.getRoot(), bufferedWriter, "");
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private void write(Node root, BufferedWriter bufferedWriter, String gaps) throws Exception{
        if(!isNodeInLeaveArrayList(root.getName())) {
            bufferedWriter.write(gaps + root.getName());
        }else {
            if (root.getPlan() != null) {
                bufferedWriter.write(gaps + root.getName() + "\n");
                bufferedWriter.write(gaps + "  " + root.getPlan() + "\n");
            }
        }
        ArrayList<Node> arrayList = root.getChildren();
        for(int i = 0; i < arrayList.size(); i++){
            write(arrayList.get(i), bufferedWriter, gaps + "  "+ "\n");
        }
    }

    private boolean isNodeInLeaveArrayList(String name){
        ArrayList<Node> leaves = lifePlan.getLeaves();
        for(int i = 0; i < leaves.size(); i++){
            if(leaves.get(i).getName().equals(name)){
                return true;
            }
        }
        return false;
    }


    public void select() throws Exception{
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
                            stringSubjectPlan = stringSubjectPlan.replace("/", "");
                        }
                        String[] split = stringSubjectPlan.split(":");
                        for(int i = 0; i < split.length; i++){
                            System.out.println(split[i]);
                        }
                        subjectAndPlan.put(split[0], split[1]);
                  //      subjectAndPlan = new HashMap<>();
                        if (flag == 1) {
                            break;
                        }
                    }
//                    for(Map.Entry<String, String> entry: subjectAndPlan.entrySet()){
//                        System.out.println(entry.getKey() + " " + entry.getValue());
//                    }
                    DayPlan dayPlan = new DayPlan(date, subjectAndPlan);
                    lifePlan.setPlanInLeavesFromDayPlan(subjectAndPlan);
                    ArrayList<Node> arrayList = lifePlan.getLeaves();
                    for(int i = 0; i < arrayList.size(); i++){
                        Node nod = arrayList.get(i);
                        if(nod.getPlan() != null)
                            System.out.println(nod.getPlan());
                    }
                    writeToFileDayPlan();
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
