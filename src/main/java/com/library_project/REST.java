package com.library_project;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

import org.apache.camel.Processor;
import java.io.FileInputStream;
import java.io.InputStream;

public class REST extends RouteBuilder {
    private static final String TEXT_HTML = "text/html";

    private static final Processor PROCESSOR = new Processor() {
        @Override
        public void process(Exchange exchange) throws Exception {
            final StringBuilder stBuilder = new StringBuilder();
            int buf;
            try(InputStream is = new FileInputStream(Utils.FILE)){
                while((buf = is.read()) != -1) {
                    stBuilder.append((char) buf);
                }
                exchange.getIn().setBody(stBuilder.toString());
            }
        }
    };

    @Override
    public void configure() throws Exception {
        restConfiguration().component("restlet").host("localhost").port(1234).enableCORS(true);

        // 1th .request
        // http://localhost:1234/tema/booksName/Jeff%20Keller
        rest("/tema").get("/booksName/{name}")
                .produces(TEXT_HTML)
                .route()
                .process(PROCESSOR)
                .to("xslt:file:C:\\Users\\Miriam\\Desktop\\XML\\xml_project\\xml_project\\src\\main\\java\\com\\library_project\\NewStylesheet.xsl");

        // 2nd.request
        //http://localhost:1234/tema/booksCopyright/2010
        rest("/tema").get("/booksCopyright/{year}")
                .produces(TEXT_HTML)
                .route()
                .process(PROCESSOR)
                .to("xslt:file:C:\\Users\\Miriam\\Desktop\\XML\\xml_project\\xml_project\\src\\main\\java\\com\\library_project\\NewStylesheet1.xsl");

        // 3th.request
        // http://localhost:1234/tema/booksStartWithA
        rest("/tema").get("/booksStartWithA")
                .produces(TEXT_HTML)
                .route()
                .process(PROCESSOR)
                .to("xslt:file:C:\\Users\\Miriam\\Desktop\\XML\\xml_project\\xml_project\\src\\main\\java\\com\\library_project\\NewStylesheet2.xsl");

        // 4th.request
        // http://localhost:1234/tema/booksContainsWord/Arta
        rest("/tema").get("/booksContainsWord/{word}")
                .produces(TEXT_HTML)
                .route()
                .process(PROCESSOR)
                .to("xslt:file:C:\\Users\\Miriam\\Desktop\\XML\\xml_project\\xml_project\\src\\main\\java\\com\\library_project\\NewStylesheet3.xsl");

        // 5.request
        // http://localhost:1234/tema/chapterNumber
        rest("/tema").get("/chapterNumber")
                .produces(TEXT_HTML)
                .route()
                .process(PROCESSOR)
                .to("xslt:file:C:\\Users\\Miriam\\Desktop\\XML\\xml_project\\xml_project\\src\\main\\java\\com\\library_project\\NewStylesheet4.xsl");

        // 6 request.
        // http://localhost:1234/tema/authorName/Jeff%20Keller
        rest("/tema").get("/authorName/{name}")
                .produces(TEXT_HTML)
                .route()
                .process(PROCESSOR)
                .to("xslt:file:C:\\Users\\Miriam\\Desktop\\XML\\xml_project\\xml_project\\src\\main\\java\\com\\library_project\\NewStylesheet5.xsl");
    }
}
