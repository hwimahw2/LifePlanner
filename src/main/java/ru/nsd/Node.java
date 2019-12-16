package ru.nsd;
import java.util.ArrayList;

public class Node {

    private String name;

    private ArrayList<Node> children = new ArrayList<>();

    private Node parent = null;

    private String plan = null;

    private int number;

    private ArrayList<Node> parents = new ArrayList<>();

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Node> getParents() {
        return parents;
    }

    public void setParents(ArrayList<Node> parents) {
        this.parents = parents;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public String getName() {
        return name;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getPlan() {
        return plan;
    }
}
