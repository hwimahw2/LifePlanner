package ru.nsd;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.Map;

public class LifePlan {

    LifePlan(){
        buildLifePlan();
        createListLeaves();
        visitLeaves = new int[leaves.size()];
        for(int i = 0; i < leaves.size(); i++){
            visitLeaves[i] = 0;
        }
    }

    private Node root;

    private int quantityOfNodes = 0;

    private ArrayList<Node> leaves = new ArrayList<>();

    private int[] visitLeaves;

    public ArrayList<Node> getLeaves() {
        return leaves;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public int[] getVisitLeaves() {
        return visitLeaves;
    }

    public int getQuantityOfNodes() {
        return quantityOfNodes;
    }

    public void setQuantityOfNodes(int quantityOfNodes) {
        this.quantityOfNodes = quantityOfNodes;
    }

    public void print(){
        int[] number = {1};
        printIter(this.getRoot(), "", number);
    }

    private void printIter(Node root, String gap, int[] number){
        if(root.getChildren().size() == 0){
            System.out.println(gap + number[0] + ")"+ root.getName());
            number[0]++;
        }else {
            System.out.println(gap + root.getName());
        }
        ArrayList<Node> children = root.getChildren();
        for (int i = 0; i < children.size(); i++) {
            printIter(children.get(i), gap + "   ", number);
        }
    }

    public void buildLifePlan(){
        int[] number = {1};
        buildLifePlanIter(addRoot(), this.getRoot(), number);
    }

    public void buildLifePlanIter(org.w3c.dom.Node xmlRoot, Node root, int[] number){
        if(xmlRoot.hasChildNodes()){
            NodeList children = xmlRoot.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                if (children.item(i).getNodeType() != org.w3c.dom.Node.TEXT_NODE) {
                    Node child = new Node();
                    child.setParent(root);
                    child.setName(children.item(i).getAttributes().getNamedItem("name").getNodeValue());
                    root.getChildren().add(child);
                    child.setNumber(number[0]);
                    number[0] += 1;
                    quantityOfNodes++;
                    buildLifePlanIter(children.item(i), child, number);
                }
            }
        }
    }

    public void dfs(Node root){
        ArrayList<Node> children = root.getChildren();
        for(int i = 0; i < children.size(); i++){
            Node child = children.get(i);
            dfs(child);
        }
    }

    public org.w3c.dom.Node addRoot(){
        DocumentBuilder documentBuilder = null;
        Document document = null;
        org.w3c.dom.Node root = null;
        try{
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = documentBuilder.parse("./src/main/resources/lifePlan.xml");
            root = document.getDocumentElement();
            this.setRoot(new Node());
            this.getRoot().setParent(null);
            this.getRoot().setName(root.getAttributes().getNamedItem("name").getNodeValue());
            this.getRoot().setNumber(0);
            quantityOfNodes++;
        }catch(Exception e){

        }
        return root;
    }

    public void dfsForCreatingListLeaves(Node root){
        if(root.getChildren().size() == 0){
            this.getLeaves().add(root);
        }
        ArrayList<Node> children = root.getChildren();
        for(int i = 0; i < children.size(); i++){
            Node child = children.get(i);
            dfsForCreatingListLeaves(child);
        }
    }

    private void createListLeaves(){
        dfsForCreatingListLeaves(this.root);
    }

    public void setPlanInLeavesFromDayPlan(Map<String, String> subjectAndPlan){
        int sizeList = getLeaves().size();
        for(Map.Entry<String, String> entry:subjectAndPlan.entrySet()){
            String nameSubject = entry.getKey();
            for(int j = 0; j < sizeList; j++){
                Node node = getLeaves().get(j);
                if(nameSubject.equals(node.getName())){
                    node.setPlan(entry.getValue());
                }
            }
        }
    }

    public void addAllParentsToNode(Node node, Node parent){
        if(node.getParent() == null){
            return;
        }
        node.getParents().add(parent);
        addAllParentsToNode(node, parent.getParent());
    }

}
