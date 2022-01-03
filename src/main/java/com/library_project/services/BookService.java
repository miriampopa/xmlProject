package com.library_project.services;

import com.library_project.model.*;
import com.sun.org.apache.xerces.internal.dom.DeepNodeListImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    AuthorService authorService;

    private List<Book> books;

    public List<Book> findBookByName(String bookName) {

        List<Book> result = getAllBooks()
                .stream()
                .filter(x -> x.getTitle().equalsIgnoreCase(bookName))
                .collect(Collectors.toList());
        return result;

    }


    public List<Book> getBookList() {
        return getAllBooks();
    }

    public List<BookChaptersModel>getBookThatStartWith(String booksTitleStartWith) {
        return getChaptersForBooksThatStartWith().stream()
                        .filter(x -> x.getTitle()
                        .startsWith(booksTitleStartWith))
                        .collect(Collectors.toList());
    }

    @PostConstruct
    private void iniDataForTesting() {
        books = new ArrayList<Book>();
        getAllBooks();
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("C:\\Users\\Miriam\\Desktop\\XML\\xml_project-v6\\xml_project\\src\\main\\resources\\library.xml");
            NodeList booksList = doc.getElementsByTagName("book");
            String infoMessage;
            for (int i = 0; i < booksList.getLength(); i++) {
                List<String> authors = new ArrayList<>();
                List<Chapter> chapters = new ArrayList<>();
                List<Section> sections = new ArrayList<>();
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
                            bookObj.setPublisher(info.getTextContent());
                        else {
                            if (info.getTagName().equalsIgnoreCase("title"))
                                bookObj.setTitle(info.getTextContent());
                            else {
                                if (info.getTagName().equalsIgnoreCase("authors")) {
                                    setAuthorsId(info, authors);
                                } else {
                                    if (info.getTagName().equalsIgnoreCase("chapters_and_sections")) {
                                        setChaptersAndSections(info, chapters, sections);
                                    } else {
                                        if (info.getTagName().equalsIgnoreCase("copyright_year"))
                                            bookObj.setCopyrightYear(Integer.parseInt(info.getTextContent()));
                                        else {
                                            if (info.getTagName().equalsIgnoreCase("subject"))
                                                bookObj.setSubject(info.getTextContent());
                                            else {
                                                if (info.getTagName().equalsIgnoreCase("isbn"))
                                                    bookObj.setIsbn(info.getTextContent());
                                                else {
                                                    if (info.getTagName().equalsIgnoreCase("price"))
                                                        bookObj.setPrice(Float.parseFloat(info.getTextContent()));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                bookObj.setAuthors(authors);
                bookObj.setChapters(chapters);
                bookObj.setSections(sections);
                books.add(bookObj);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }

    public List<Book> getBooksWithAuthorThatHasCoworkers() {
        List<Book> books = new ArrayList<>();
        boolean hasCoworkers;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("C:\\Users\\Miriam\\Desktop\\XML\\xml_project-v6\\xml_project\\src\\main\\resources\\library.xml");
            NodeList booksList = doc.getElementsByTagName("book");
            String infoMessage;
            for (int i = 0; i < booksList.getLength(); i++) {
                hasCoworkers = false;
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
                            bookObj.setPublisher(info.getTextContent());
                        else {
                            if (info.getTagName().equalsIgnoreCase("title"))
                                bookObj.setTitle(info.getTextContent());
                            else {
                                if (info.getTagName().equalsIgnoreCase("authors")) {
                                    if (checkIfAuthorsIdIfAuthorHasCoworkers(info, authors)) {
                                        hasCoworkers = true;
                                    }
                                } else {
                                    if (info.getTagName().equalsIgnoreCase("chapters_and_sections")) {
                                        setChapters(info, chapters);
                                    } else {

                                        if (info.getTagName().equalsIgnoreCase("copyright_year"))
                                            bookObj.setCopyrightYear(Integer.parseInt(info.getTextContent()));
                                        else {
                                            if (info.getTagName().equalsIgnoreCase("subject"))
                                                bookObj.setSubject(info.getTextContent());
                                            else {
                                                if (info.getTagName().equalsIgnoreCase("isbn"))
                                                    bookObj.setIsbn(info.getTextContent());
                                                else {
                                                    if (info.getTagName().equalsIgnoreCase("price"))
                                                        bookObj.setPrice(Float.parseFloat(info.getTextContent()));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (hasCoworkers) {
                    bookObj.setAuthors(authors);
                    bookObj.setChapters(chapters);
                    books.add(bookObj);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }


    public List<BookChaptersModel> getChaptersForBooksThatStartWith() {
        List<BookChaptersModel> books = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("C:\\Users\\Miriam\\Desktop\\XML\\xml_project-v6\\xml_project\\src\\main\\resources\\library.xml");
            NodeList booksList = doc.getElementsByTagName("book");
            String infoMessage;
            for (int i = 0; i < booksList.getLength(); i++) {
                List<String> authors = new ArrayList<>();
                List<Chapter> chapters = new ArrayList<>();
                BookChaptersModel bookObj = new BookChaptersModel();
                Node node = booksList.item(i);
                Element book = (Element) node;
                String id = book.getAttribute("id");
                NodeList infos = book.getChildNodes();
                for (int j = 0; j < infos.getLength(); j++) {
                    Node nodeInfo = infos.item(j);
                    if (nodeInfo.getNodeType() == Node.ELEMENT_NODE) {
                        Element info = (Element) nodeInfo;
                        if (info.getTagName().equalsIgnoreCase("title"))
                            bookObj.setTitle(info.getTextContent());
                        else {
                            if (info.getTagName().equalsIgnoreCase("authors")) {
                                setAuthorsId(info, authors);
                            } else {
                                if (info.getTagName().equalsIgnoreCase("chapters_and_sections")) {
                                    setChapters(info, chapters);
                                } else {
                                    if (info.getTagName().equalsIgnoreCase("subject"))
                                        bookObj.setSubject(info.getTextContent());
                                }
                            }
                        }
                    }
                }
                bookObj.setAuthors(authors);
                bookObj.setChapters(chapters);
                books.add(bookObj);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }

    public List<BookChaptersFilteredByAuthorName> getChaptersByAuthorName(String name) {
        List<BookChaptersFilteredByAuthorName> books = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("C:\\Users\\Miriam\\Desktop\\XML\\xml_project-v6\\xml_project\\src\\main\\resources\\library.xml");
            NodeList booksList = doc.getElementsByTagName("book");
            String infoMessage;
            for (int i = 0; i < booksList.getLength(); i++) {
                List<String> authors = new ArrayList<>();
                List<Chapter> chapters = new ArrayList<>();
                BookChaptersFilteredByAuthorName bookObj = new BookChaptersFilteredByAuthorName();
                Node node = booksList.item(i);
                Element book = (Element) node;
                String id = book.getAttribute("id");
                NodeList infos = book.getChildNodes();
                for (int j = 0; j < infos.getLength(); j++) {
                    Node nodeInfo = infos.item(j);
                    if (nodeInfo.getNodeType() == Node.ELEMENT_NODE) {
                        Element info = (Element) nodeInfo;
                        if (info.getTagName().equalsIgnoreCase("title"))
                            bookObj.setTitle(info.getTextContent());
                        else {
                            if (info.getTagName().equalsIgnoreCase("authors")) {
                                setAuthorsId(info, authors);
                            } else {
                                if (info.getTagName().equalsIgnoreCase("chapters_and_sections")) {
                                    setChapters(info, chapters);
                                } else {
                                    if (info.getTagName().equalsIgnoreCase("subject"))
                                        bookObj.setSubject(info.getTextContent());
                                }
                            }
                        }
                    }
                }
                boolean ok = false;
                for (String authorId : authors) {
                    if (authorId.equalsIgnoreCase(authorService.findAuthorIdByName(name)))
                        ok = true;
                }
                if (ok) {
                    bookObj.setAuthor(authorService.findAuthorsByName(name));
                    bookObj.setAuthors(authors);
                    bookObj.setChapters(chapters);
                    books.add(bookObj);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
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


    public List<BookSectionsModel> getNumberOfSectionsForABookTitle(String title) {

        List<BookSectionsModel> books = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("C:\\Users\\Miriam\\Desktop\\XML\\xml_project-v6\\xml_project\\src\\main\\resources\\library.xml");
            NodeList booksList = doc.getElementsByTagName("book");

            //we go through all list because can be possible to have different books with th same title
            for (int i = 0; i < booksList.getLength(); i++) {
                List<String> authors = new ArrayList<>();
                List<Chapter> chapters = new ArrayList<>();
                List<Section> sections = new ArrayList<>();
                String titleValue = "";
                BookSectionsModel bookObj = new BookSectionsModel();
                Node node = booksList.item(i);
                Element book = (Element) node;
                NodeList infos = book.getChildNodes();
                for (int j = 0; j < infos.getLength(); j++) {
                    Node nodeInfo = infos.item(j);
                    if (nodeInfo.getNodeType() == Node.ELEMENT_NODE) {
                        Element info = (Element) nodeInfo;
                        if (info.getTagName().equalsIgnoreCase("title")) {
                            bookObj.setTitle(info.getTextContent());
                            titleValue = info.getTextContent();
                        } else {
                            if (info.getTagName().equalsIgnoreCase("authors")) {
                                setAuthorsId(info, authors);
                            } else {
                                if (info.getTagName().equalsIgnoreCase("subject"))
                                    bookObj.setSubject(info.getTextContent());
                                else {
                                    if (info.getTagName().equalsIgnoreCase("chapters_and_sections"))
                                        setSections(info, sections);
                                }
                            }
                        }
                    }

                }
                if (titleValue.equalsIgnoreCase(title)) {
                    bookObj.setAuthors(authors);
                    bookObj.setSections(sections);
                    bookObj.setNumberOfSections(sections.size());
                    books.add(bookObj);
                }
            }
        } catch (
                ParserConfigurationException e) {
            e.printStackTrace();
        } catch (
                SAXException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return books;
    }


    public boolean checkIfAuthorsIdIfAuthorHasCoworkers(Element info, List<String> authors) {
        boolean hasCoworkers = false;
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
                        if (authorInfoNode.getLength() > 0)
                            hasCoworkers = true;
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
        return hasCoworkers;
    }


    public void setChapters(Element info, List<Chapter> chapters) {
        NodeList chapterInfoNode = info.getChildNodes();
        for (int l = 0; l < chapterInfoNode.getLength(); l++) {
            Chapter c = new Chapter();
            Node chaptersNode = chapterInfoNode.item(l);
            if (chaptersNode.getNodeType() == Node.ELEMENT_NODE) {
                Element chapter = (Element) chaptersNode;
                if (chapter.getTagName().equalsIgnoreCase("chapter")) {
                    NodeList chaptersNodeInfo = chapter.getChildNodes();
                    for (int index = 0; index < chaptersNodeInfo.getLength(); index++) {
                        Node chapterNode = chaptersNodeInfo.item(index);
                        if (chapterNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element chapterElement = (Element) chapterNode;
                            if (chapterElement.getTagName().equalsIgnoreCase("name"))
                                c.setName(chapterElement.getTextContent());
                            if (chapterElement.getTagName().equalsIgnoreCase("pages"))
                                c.setPageNumber(chapterElement.getTextContent());
                            if (chapterElement.getTagName().equalsIgnoreCase("number"))
                                c.setChapterNumber(Integer.parseInt(chapterElement.getTextContent()));
                        }
                    }
                }
            }
            //the title should not be null
            if (c.getName() != null)
                chapters.add(c);
        }
    }


    public void setChaptersAndSections(Element info, List<Chapter> chapters, List<Section> sections) {
        NodeList chapterInfoNode = info.getChildNodes();
        for (int l = 0; l < chapterInfoNode.getLength(); l++) {
            Chapter c = new Chapter();
            Section s = new Section();
            Node chaptersNode = chapterInfoNode.item(l);
            if (chaptersNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) chaptersNode;
                if (element.getTagName().equalsIgnoreCase("chapter")) {
                    NodeList chaptersNodeInfo = element.getChildNodes();
                    for (int index = 0; index < chaptersNodeInfo.getLength(); index++) {
                        Node chapterNode = chaptersNodeInfo.item(index);
                        if (chapterNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element chapterElement = (Element) chapterNode;
                            if (chapterElement.getTagName().equalsIgnoreCase("name"))
                                c.setName(chapterElement.getTextContent());
                            if (chapterElement.getTagName().equalsIgnoreCase("pages"))
                                c.setPageNumber(chapterElement.getTextContent());
                            if (chapterElement.getTagName().equalsIgnoreCase("number"))
                                c.setChapterNumber(Integer.parseInt(chapterElement.getTextContent()));
                        }
                    }
                } else if (element.getTagName().equalsIgnoreCase("section")) {
                    NodeList sectionsNodeInfo = element.getChildNodes();
                    for (int index = 0; index < sectionsNodeInfo.getLength(); index++) {
                        Node sectionChildNode = sectionsNodeInfo.item(index);
                        if (sectionChildNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element sectionElement = (Element) sectionChildNode;
                            if (sectionElement.getTagName().equalsIgnoreCase("name"))
                                s.setName(sectionElement.getTextContent());
                            if (sectionElement.getTagName().equalsIgnoreCase("pages"))
                                s.setPageNumber(sectionElement.getTextContent());
                        }
                    }
                }
            }
            //the title should not be null
            if (c.getName() != null)
                chapters.add(c);
            if (s.getName() != null) {
                sections.add(s);
            }
        }
    }


    public void setSections(Element info, List<Section> sections) {
        NodeList chapterInfoNode = info.getChildNodes();
        for (int l = 0; l < chapterInfoNode.getLength(); l++) {
            Section c = new Section();
            Node sectionNode = chapterInfoNode.item(l);
            if (sectionNode.getNodeType() == Node.ELEMENT_NODE) {
                Element section = (Element) sectionNode;
                if (section.getTagName().equalsIgnoreCase("section")) {
                    NodeList sectionsNodeInfo = section.getChildNodes();
                    for (int index = 0; index < sectionsNodeInfo.getLength(); index++) {
                        Node sectionChildNode = sectionsNodeInfo.item(index);
                        if (sectionChildNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element sectionElement = (Element) sectionChildNode;
                            if (sectionElement.getTagName().equalsIgnoreCase("name"))
                                c.setName(sectionElement.getTextContent());
                            if (sectionElement.getTagName().equalsIgnoreCase("pages"))
                                c.setPageNumber(sectionElement.getTextContent());
                        }
                    }
                }
            }
            //the title should not be null
            if (c.getName() != null)
                sections.add(c);
        }
    }


    public List<BookedBook> getAllBookedBooks() {
        List<BookedBook> books = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("C:\\Users\\Miriam\\Desktop\\XML\\xml_project-v6\\xml_project\\src\\main\\resources\\library.xml");
            NodeList booksList = doc.getElementsByTagName("booked_book");
            String infoMessage;
            for (int i = 0; i < booksList.getLength(); i++) {
                BookedBook bookObj = new BookedBook();
                Node node = booksList.item(i);
                Element book = (Element) node;
                String id = book.getAttribute("id");
                NodeList infos = book.getChildNodes();
                for (int j = 0; j < infos.getLength(); j++) {
                    Node nodeInfo = infos.item(j);
                    if (nodeInfo.getNodeType() == Node.ELEMENT_NODE) {
                        Element info = (Element) nodeInfo;
                        if (info.getTagName().equalsIgnoreCase("user_id"))
                            bookObj.setUserId(info.getTextContent());
                        else {
                            if (info.getTagName().equalsIgnoreCase("title"))
                                bookObj.setTitle(info.getTextContent());

                            }
                        }

                }
                books.add(bookObj);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }




    public void addBook(BookedBook  book) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("C:\\Users\\Miriam\\Desktop\\XML\\xml_project-v6\\xml_project\\src\\main\\resources\\library.xml");
            NodeList books = doc.getElementsByTagName("booked_book");

            Element root = doc.getDocumentElement();
            NodeList rootElement = doc.getElementsByTagName("booked_book_list");

            Element newUser = rootElement.item(0).getFirstChild().getOwnerDocument().createElement("booked_book");

            Element id = doc.createElement("user_id");
            id.appendChild(doc.createTextNode(book.getUserId()));
            newUser.appendChild(id);

            Element title = doc.createElement("title");
            title.appendChild(doc.createTextNode(book.getTitle()));
            newUser.appendChild(title);

            rootElement.item(0).appendChild(newUser);

            DOMSource source = new DOMSource(doc);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult("C:\\Users\\Miriam\\Desktop\\XML\\xml_project-v6\\xml_project\\src\\main\\resources\\library.xml");
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
