import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.Map;

import ru.nsd.Node;

public class LifePlan {

    LifePlan(){
        buildLifePlan();
        createListLeaves();
    }

    private Node root;

    private ArrayList<Node> leaves = new ArrayList<>();

    public ArrayList<Node> getLeaves() {
        return leaves;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
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
        buildLifePlanIter(addRoot(), this.getRoot());
    }

    public void buildLifePlanIter(org.w3c.dom.Node xmlRoot, Node root){
        if(xmlRoot.hasChildNodes()){
            NodeList children = xmlRoot.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                if (children.item(i).getNodeType() != org.w3c.dom.Node.TEXT_NODE) {
                    Node child = new Node();
                    child.setParent(root);
                    child.setName(children.item(i).getAttributes().getNamedItem("name").getNodeValue());
                    root.getChildren().add(child);
                    buildLifePlanIter(children.item(i), child);
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
}
