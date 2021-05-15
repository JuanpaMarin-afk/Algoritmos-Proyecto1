package data;

import domain.CircularDoublyLinkedList;
import domain.Security;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author juanp
 */
public class FileXML {

    //Constructor
    public FileXML() {
    }

    public Boolean exist(String address) {
        File file = new File(address);
        if (file.exists()) {
            return true;
        }
        return false;
    }

    public void createXML(String objectName, String address, String fileName) {//Crea el archivo xml

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement(objectName);
            doc.appendChild(rootElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new File(fileName + ".xml"));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeXML(String FileName, String elementType, String[] dataName, String[] data) throws TransformerException {//Escribe en el xml
        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(FileName));
            doc.getDocumentElement().normalize();

            Element rootElement = doc.getDocumentElement();

            Element ele = doc.createElement(elementType);
            rootElement.appendChild(ele);

            Attr attr = doc.createAttribute(dataName[0]);
            attr.setValue(data[0]);
            ele.setAttributeNode(attr);

            for (int i = 1; i < data.length; i++) {

                Element dato = doc.createElement(dataName[i]);

                dato.appendChild(doc.createTextNode(data[i]));

                ele.appendChild(dato);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new File(FileName));
            transformer.transform(source, result);

            System.out.println("Registro Guardado");

        } catch (ParserConfigurationException pce) {

            pce.printStackTrace();

        } catch (SAXException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        } catch (TransformerConfigurationException e) {

            e.printStackTrace();
        } catch (TransformerException e) {

            e.printStackTrace();
        }
    }

    public CircularDoublyLinkedList readXMLUser(String address, String elementType) {
        CircularDoublyLinkedList loginList = new CircularDoublyLinkedList();
        String type = "";
        String name = "";
        String password = "";
        try {
            File inputFile = new File(address);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName(elementType);

            for (int indice = 0; indice < nList.getLength(); indice++) {
                Node nNode = nList.item(indice);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    type = eElement.getAttribute("Type");
                    name = eElement.getElementsByTagName("Name").item(0).getTextContent();
                    password = eElement.getElementsByTagName("Password").item(0).getTextContent();

                    Security security = new Security(type, name, password);
                    loginList.add(security);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return loginList;
    }

    public Security readXMLLogIn(String address, String elementType, Security object) {
        Security security = new Security();
        String type = "";
        String name = "";
        String password = "";
        try {
            File inputFile = new File(address);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName(elementType);

            for (int indice = 0; indice < nList.getLength(); indice++) {
                Node nNode = nList.item(indice);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    if (eElement.getElementsByTagName("Name").item(0).getTextContent().equals(object.getUser())) {

                        if (eElement.getElementsByTagName("Password").item(0).getTextContent().equals(object.getPassword())) {

                            type = eElement.getAttribute("Type");
                            name = eElement.getElementsByTagName("Name").item(0).getTextContent();
                            password = eElement.getElementsByTagName("Password").item(0).getTextContent();
                            security = new Security(type, name, password);
                        }

                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return security;
    }

}//end class
