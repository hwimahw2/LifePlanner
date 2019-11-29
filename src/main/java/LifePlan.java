import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;

public class LifePlan {
    private ArrayList<MainField> mainFields = new ArrayList();

    public void buildLifePlan()  throws Exception{
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse("./src/main/resources/lifePlan.xml");
        NodeList mainFields = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < mainFields.getLength(); i++) {
            Node mainField = mainFields.item(i);
            if (mainField.getNodeType() != Node.TEXT_NODE) {
                NamedNodeMap attribMainField = mainField.getAttributes();
                this.mainFields.add(new MainField(attribMainField.getNamedItem("name").getNodeValue()));
                NodeList fields = mainField.getChildNodes();
                for (int j = 0; j < fields.getLength(); j++) {
                    Node field = fields.item(j);
                    if (field.getNodeType() != Node.TEXT_NODE) {
                        NamedNodeMap attribField = field.getAttributes();
                        ArrayList<Field> listFields = this.mainFields.get(this.mainFields.size() - 1).getFields();
                        listFields.add(new Field(attribField.getNamedItem("name").getNodeName()));
                        NodeList subjects = field.getChildNodes();
                        for (int k = 0; k < subjects.getLength(); k++) {
                            Node subject = subjects.item(k);
                            if (subject.getNodeType() != Node.TEXT_NODE) {
                                NamedNodeMap attribSubject = subject.getAttributes();
                                listFields.get(listFields.size() - 1).getSubjects().add(new Subject(attribSubject.getNamedItem("name").getNodeName()));
                            }
                        }
                    }
                }
            }
        }
        System.out.println(this.mainFields.size());

    }

    //Граф, как в блокноте
    //Чтобы составлять DayPlan
}
