package com.library_project.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.library_project.model.User;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserService {

    private List<User> users;

    public List<User> findByUserNameOrEmail(String username) {

        List<User> result = users
                            .stream()
                            .filter(x -> x.getUsername().equalsIgnoreCase(username))
                            .collect(Collectors.toList());

        return result;

    }
    /*public List<User> findByUserNameOrEmail(String username, String email) {

        List<User> result = new ArrayList<User>();

        for (User user : users) {

            if ((!StringUtils.isEmpty(username)) && (!StringUtils.isEmpty(email))) {

                if (username.equals(user.getUsername()) && email.equals(user.getEmail())) {
                    result.add(user);
                    continue;
                } else {
                    continue;
                }

            }
            if (!StringUtils.isEmpty(username)) {
                if (username.equals(user.getUsername())) {
                    result.add(user);
                    continue;
                }
            }

            if (!StringUtils.isEmpty(email)) {
                if (email.equals(user.getEmail())) {
                    result.add(user);
                    continue;
                }
            }

        }

        return result;

    }*/

    // Init some users for testing
    @PostConstruct
    private void iniDataForTesting() {
        users = new ArrayList<User>();
        getAllUsers();
    }


    public void getAllUsers() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("C:\\Users\\Miriam\\Desktop\\XML\\xml_project\\xml_project\\src\\main\\resources\\user.xml");
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
    }


}
