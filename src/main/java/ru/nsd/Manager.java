package ru.nsd;

import java.io.*;
import java.util.*;

public class Manager {

    Menu menu = new Menu();
    LifePlan lifePlan = new LifePlan();
    int[] visit = new int[lifePlan.getQuantityOfNodes()];


    private void fillVisitArray(DayPlan dayPlan){
        for(int i = 0; i < lifePlan.getQuantityOfNodes(); i++){
            visit[i] = 0;
        }
        for(Map.Entry<String, String> entry:dayPlan.getSubjectAndPlan().entrySet()){
            for(int i = 0; i < lifePlan.getLeaves().size(); i++){
                if(entry.getKey().equals(lifePlan.getLeaves().get(i).getName())){
                    Node node = lifePlan.getLeaves().get(i);
                    fillVisitArrayIter(node);
                }
            }
        }
    }

    private void fillVisitArrayIter(Node node){
        if(node == null){
            return;
        }
        this.visit[node.getNumber()] = 1;
        fillVisitArrayIter(node.getParent());
    }

    private void writeToFileDayPlan(DayPlan dayPlan) throws Exception{
        File file = new File("./src/main/resources/input.txt");
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try{
            fileWriter = new FileWriter(file, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            Node node = lifePlan.getRoot();
            bufferedWriter.write("-----------------------------------------------------------\n");
            bufferedWriter.write(dayPlan.getDate() + "\n");
            write(node, bufferedWriter, "");
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

    private void write(Node node, BufferedWriter bufferedWriter, String gaps) throws Exception{
        if(node != null && this.visit[node.getNumber()] == 1) {
            bufferedWriter.write(gaps + node.getName() + "\n");
            if (node.getPlan() != null) {
                bufferedWriter.write(gaps + "  " + node.getPlan() + "\n");
            }
            gaps = gaps + "  ";
        }
        for(int i = 0; i < node.getChildren().size(); i++){
            write(node.getChildren().get(i), bufferedWriter, gaps);
        }
    }

    public void select() throws Exception{
        int exit = 0;
        do {
            menu.buildPrintMenu();
            Scanner scanner = new Scanner(new InputStreamReader(System.in));
            int choice = scanner.nextInt();
            switch (choice) {
                case (1): {
                    lifePlan.print();
                    System.out.println();
                    break;
                }
                case (2): {
                    lifePlan.print();
                    System.out.println();
                    System.out.println("Дата");
                    scanner.nextLine();
                    String date = scanner.nextLine();
                    System.out.println("Введите предмет:описание");
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
                        subjectAndPlan.put(split[0], split[1]);
                        if (flag == 1) {
                            break;
                        }
                    }
                    DayPlan dayPlan = new DayPlan(date, subjectAndPlan);
                    lifePlan.setPlanInLeavesFromDayPlan(dayPlan.getSubjectAndPlan());
                    fillVisitArray(dayPlan);
                    writeToFileDayPlan(dayPlan);
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
