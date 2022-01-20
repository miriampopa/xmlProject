package com.library_project.services;

import com.library_project.Utils;
import com.library_project.model.*;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    public Set<Author> getAllAuthors(){

        List<Author> authors = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(Utils.LIBRARY_XML_PATH);
            NodeList authorList = doc.getElementsByTagName("author_data");


            for (int i = 0; i < authorList.getLength(); i++) {
                Author authorObj = new Author();
                Node node = authorList.item(i);
                Element author = (Element) node;

                NodeList infos = author.getChildNodes();
                for (int j = 0; j < infos.getLength(); j++) {
                    Node nodeInfo = infos.item(j);
                    if (nodeInfo.getNodeType() == Node.ELEMENT_NODE) {
                        Element info = (Element) nodeInfo;
                        if (info.getTagName().equalsIgnoreCase("id")) {
                            authorObj.setId(info.getTextContent());
                        } else if (info.getTagName().equalsIgnoreCase("author_name"))
                            authorObj.setName(info.getTextContent());
                    }
                    authors.add(authorObj);
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
      return authors.stream().collect(Collectors.toSet());
    }

    public Author findAuthorsById(String id){

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Author authorObj = new Author();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(Utils.LIBRARY_XML_PATH);
            NodeList authorList = doc.getElementsByTagName("author_data");

            for (int i = 0; i < authorList.getLength(); i++) {
                authorObj = new Author();
                Node node = authorList.item(i);
                Element author = (Element) node;

                String  idValue = "";
                NodeList infos = author.getChildNodes();
                for (int j = 0; j < infos.getLength(); j++) {
                    Node nodeInfo = infos.item(j);
                    if (nodeInfo.getNodeType() == Node.ELEMENT_NODE) {
                        Element info = (Element) nodeInfo;
                        if (info.getTagName().equalsIgnoreCase("id")) {
                            {
                                authorObj.setId(info.getTextContent());
                                idValue = info.getTextContent();
                            }
                        } else if (info.getTagName().equalsIgnoreCase("author_name"))
                            authorObj.setName(info.getTextContent());

                        if (authorObj.getName() != null && idValue.equalsIgnoreCase(id))
                           return authorObj;
                    }
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return authorObj;
    }



    public Author findAuthorsByName(String name){

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Author authorObj = new Author();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(Utils.LIBRARY_XML_PATH);
            NodeList authorList = doc.getElementsByTagName("author_data");

            for (int i = 0; i < authorList.getLength(); i++) {
                authorObj = new Author();
                Node node = authorList.item(i);
                Element author = (Element) node;

                NodeList infos = author.getChildNodes();
                for (int j = 0; j < infos.getLength(); j++) {
                    Node nodeInfo = infos.item(j);
                    if (nodeInfo.getNodeType() == Node.ELEMENT_NODE) {
                        Element info = (Element) nodeInfo;
                        if (info.getTagName().equalsIgnoreCase("id")) {
                            {
                                authorObj.setId(info.getTextContent());
                            }
                        } else if (info.getTagName().equalsIgnoreCase("author_name"))
                            authorObj.setName(info.getTextContent());

                        if (authorObj.getName() != null && authorObj.getName().equalsIgnoreCase(name))
                            return authorObj;
                    }
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return authorObj;
    }


    public String findAuthorIdByName(String name){

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Author authorObj = new Author();
        String id = "";
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(Utils.LIBRARY_XML_PATH);
            NodeList authorList = doc.getElementsByTagName("author_data");

            for (int i = 0; i < authorList.getLength(); i++) {
                authorObj = new Author();
                Node node = authorList.item(i);
                Element author = (Element) node;

                NodeList infos = author.getChildNodes();
                for (int j = 0; j < infos.getLength(); j++) {
                    Node nodeInfo = infos.item(j);
                    if (nodeInfo.getNodeType() == Node.ELEMENT_NODE) {
                        Element info = (Element) nodeInfo;
                        if (info.getTagName().equalsIgnoreCase("id")) {
                            {
                                authorObj.setId(info.getTextContent());
                            }
                        } else if (info.getTagName().equalsIgnoreCase("author_name"))
                            authorObj.setName(info.getTextContent());

                        if (authorObj.getName() != null && authorObj.getName().equalsIgnoreCase(name))
                            id = authorObj.getId();
                    }
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }



    public Set<BookAuthorsFilteredByCopyRightYear> getBookAuthorsFilteredByCopyRightYear(int year) {
        List<Book> books = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(Utils.LIBRARY_XML_PATH);
            NodeList booksList = doc.getElementsByTagName("book");
            int copyrightYear;
            for (int i = 0; i < booksList.getLength(); i++) {
                copyrightYear = year;
                List<String> authors = new ArrayList<>();
                List<Chapter> chapters = new ArrayList<>();
                Book bookObj = new Book();
                Node node = booksList.item(i);
                Element book = (Element) node;
                String id = book.getAttribute("id");
                NodeList infos = book.getChildNodes();
                for (int j = 0; j < infos.getLength(); j++) {
                    Node nodeInfo = infos.item(j);
                    if (nodeInfo.getNodeType() == Node.ELEMENT_NODE) {
                        Element info = (Element) nodeInfo;
                        if (info.getTagName().equalsIgnoreCase("copyright_year"))
                        {
                            bookObj.setCopyrightYear(Integer.parseInt(info.getTextContent()));
                            copyrightYear = Integer.parseInt(info.getTextContent());
                        }
                        else {
                            if (info.getTagName().equalsIgnoreCase("title"))
                                bookObj.setTitle(info.getTextContent());
                            else {
                                if (info.getTagName().equalsIgnoreCase("authors")) {
                                    setAuthorsId(info, authors);
                                }
                            }
                            if (copyrightYear != year) {
                                bookObj.setAuthors(authors);
                                books.add(bookObj);
                            }
                        }
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<BookAuthorsFilteredByCopyRightYear> bookAuthorsFilteredByCopyRightYear = new ArrayList<>();

        for (Book book: books.stream().collect(Collectors.toSet())){
            BookAuthorsFilteredByCopyRightYear obj = new BookAuthorsFilteredByCopyRightYear();
            obj.setYear(book.getCopyrightYear());
            for (String authorId: book.getAuthors())
               obj.setAuthor(Arrays.asList(findAuthorsById(authorId)));
            obj.setTitle(book.getTitle());
            bookAuthorsFilteredByCopyRightYear.add(obj);
        }

        return bookAuthorsFilteredByCopyRightYear.stream().collect(Collectors.toSet());
    }


    public Set<BookAuthorsFilteredByPublisher> getBookAuthorsFilteredByPublisherAndAuthorName(String publishParam, String word) {
        List<Book> books = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(Utils.LIBRARY_XML_PATH);
            NodeList booksList = doc.getElementsByTagName("book");
            String publisher;
            for (int i = 0; i < booksList.getLength(); i++) {
                publisher = "";
                List<String> authors = new ArrayList<>();
                List<Chapter> chapters = new ArrayList<>();
                Book bookObj = new Book();
                Node node = booksList.item(i);
                Element book = (Element) node;
                String id = book.getAttribute("id");
                NodeList infos = book.getChildNodes();
                for (int j = 0; j < infos.getLength(); j++) {
                    Node nodeInfo = infos.item(j);
                    if (nodeInfo.getNodeType() == Node.ELEMENT_NODE) {
                        Element info = (Element) nodeInfo;
                        if (info.getTagName().equalsIgnoreCase("publisher"))
                        {
                            bookObj.setPublisher(info.getTextContent());
                            publisher = info.getTextContent();
                        }
                        else {
                            if (info.getTagName().equalsIgnoreCase("title"))
                                bookObj.setTitle(info.getTextContent());
                            else {
                                if (info.getTagName().equalsIgnoreCase("authors")) {
                                    setAuthorsId(info, authors);
                                }
                            }
                            if (publisher.equalsIgnoreCase(publishParam)) {
                                bookObj.setAuthors(authors);
                                books.add(bookObj);
                            }
                        }
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<BookAuthorsFilteredByPublisher> bookAuthorsFilteredByPublisher = new ArrayList<>();

        for (Book book: books.stream().collect(Collectors.toSet())){
            BookAuthorsFilteredByPublisher obj = new BookAuthorsFilteredByPublisher();
            obj.setPublisher(book.getPublisher());
            for (String authorId: book.getAuthors()) {
                Author authorTemp = findAuthorsById(authorId);
                if (authorTemp.getName().contains(word)) {
                    obj.setAuthor(Arrays.asList(findAuthorsById(authorId)));
                    obj.setTitle(book.getTitle());
                    bookAuthorsFilteredByPublisher.add(obj);
                }
            }
        }

        return bookAuthorsFilteredByPublisher.stream().collect(Collectors.toSet());
    }






    public void setAuthorsId(Element info, List<String> authors) {
        NodeList authorNode = info.getChildNodes();
        for (int k = 0; k < authorNode.getLength(); k++) {
            Node authorNodeInfo = authorNode.item(k);
            if (authorNodeInfo.getNodeType() == Node.ELEMENT_NODE) {
                {
                    Element authorInfo = (Element) authorNodeInfo;
                    if (authorInfo.getTagName().equalsIgnoreCase("id"))
                        authors.add(authorInfo.getTextContent());
                    else if (authorInfo.getTagName().equalsIgnoreCase("coauthors")) {
                        NodeList authorInfoNode = authorInfo.getChildNodes();
                        for (int l = 0; l < authorInfoNode.getLength(); l++) {
                            Node coauthorNode = authorInfoNode.item(l);
                            if (coauthorNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element coauthor = (Element) coauthorNode;
                                if (coauthor.getTagName().equalsIgnoreCase("id"))
                                    authors.add(coauthor.getTextContent());
                            }
                        }
                    }

                }
            }
        }
    }
}
