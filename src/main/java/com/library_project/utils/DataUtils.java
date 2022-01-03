package com.library_project.utils;

import com.library_project.model.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataUtils {

    public static List<User> getUsersMethod() {
        List<com.library_project.model.User> users = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("C:\\Users\\Miriam\\Desktop\\XML\\xml_project-v6\\xml_project\\src\\main\\resources\\user.xml");
            NodeList books = doc.getElementsByTagName("user_data");
            String infoMessage;
            for (int i = 0; i < books.getLength(); i++) {
                Node node = books.item(i);
                Element book = (Element) node;
                String id = book.getAttribute("id");
                String name = book.getElementsByTagName("user_name").item(0).getFirstChild().getNodeValue().toString();
                String pass = book.getElementsByTagName("user_password").item(0).getFirstChild().getNodeValue().toString();
                String email = book.getElementsByTagName("user_email").item(0).getFirstChild().getNodeValue().toString();

                User user = new User();
                user.setUsername(name);
                user.setPassword(pass);
                user.setEmail(email);
                user.setId(id);

                users.add(user);
                System.out.println();
            }
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return users;
    }


    public static void addUser(User user) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("C:\\Users\\Miriam\\Desktop\\XML\\xml_project-v6\\xml_project\\src\\main\\resources\\user.xml");
            NodeList books = doc.getElementsByTagName("user_data");

            Element root = doc.getDocumentElement();
            NodeList rootElement = doc.getElementsByTagName("user_list");

            Element newUser = rootElement.item(0).getFirstChild().getOwnerDocument().createElement("user_data");

            Element name = doc.createElement("user_name");
            name.appendChild(doc.createTextNode(user.getUsername()));
            newUser.appendChild(name);

            Element id = doc.createElement("id");
            id.appendChild(doc.createTextNode(user.getId()));
            newUser.appendChild(id);

            Element password = doc.createElement("user_password");
            password.appendChild(doc.createTextNode(user.getPassword()));
            newUser.appendChild(password);

            Element email = doc.createElement("user_email");
            email.appendChild(doc.createTextNode(user.getEmail()));
            newUser.appendChild(email);

            rootElement.item(0).appendChild(newUser);

            DOMSource source = new DOMSource(doc);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult("C:\\Users\\Miriam\\Desktop\\XML\\xml_project-v6\\xml_project\\src\\main\\resources\\user.xml");
            transformer.transform(source, result);


        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

}
