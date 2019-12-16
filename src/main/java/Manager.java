import ru.nsd.Node;

import java.io.*;
import java.util.*;

public class Manager {

    Menu menu = new Menu();
    LifePlan lifePlan = new LifePlan();
    DayPlan dayPlan;
    int[] visit;
    Map<String, String> subjectAndPlan;


    private void fillVisitArray(DayPlan dayPlan){
        visit = new int[lifePlan.getQuantityOfNodes()];
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

    private void writeToFileDayPlan() throws Exception{
        File file = new File("./src/main/resources/input.txt");
        FileWriter fileWriter = null;
        String[] gaps = {""};
        BufferedWriter bufferedWriter = null;
        try{
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            Node node = lifePlan.getRoot();
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


//    private void writeToFileDayPlan() throws Exception{
//        File file = new File("./src/main/resources/input.txt");
//        FileWriter fileWriter = null;
//        String[] gaps = {""};
//        BufferedWriter bufferedWriter = null;
//        try{
//            fileWriter = new FileWriter(file);
//            bufferedWriter = new BufferedWriter(fileWriter);
//            ArrayList<Node> leaves = lifePlan.getLeaves();
//            for(int i = 0; i < leaves.size(); i++){
//                Node leaf = leaves.get(i);
//                if(leaf.getPlan() != null){
//                    write(leaf, leaf, bufferedWriter, gaps, i);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally{
//            try {
//                bufferedWriter.close();
//                fileWriter.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

//    private void write(Node nodeFromUp, Node leaf, BufferedWriter bufferedWriter, String[] gaps, int indexOfNodeFromUp) throws Exception{
//        if(nodeFromUp.getParent() != null) {
//            write(nodeFromUp.getParent(), leaf, bufferedWriter, gaps, indexOfNodeFromUp);
//        }
//        bufferedWriter.write(gaps[0] + nodeFromUp.getName() + '\n');
//        if(nodeFromUp == leaf){
//            int size = lifePlan.getLeaves().size();
//            int[] visitLeaves = lifePlan.getVisitLeaves();
//            visitLeaves[indexOfNodeFromUp] = 1;
//            for(int i = 0; i < size; i++){
//                Node brother = lifePlan.getLeaves().get(i);
//                if(nodeFromUp.getParent() == brother.getParent() && visitLeaves[i] == 0){
//                    visitLeaves[i] = 1;
//                    bufferedWriter.write(gaps[0] + brother.getName() + '\n');
//                }
//            }
//        }
//        gaps[0] = gaps[0] + "  ";
//    }

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
                    lifePlan.setPlanInLeavesFromDayPlan(dayPlan.getSubjectAndPlan());
                    fillVisitArray(dayPlan);
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
