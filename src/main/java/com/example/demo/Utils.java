package com.example.demo;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.camel.CamelContext;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.main.Main;

public class Utils {

    public static final String FILE = "C:\\Users\\Miriam\\Desktop\\XML\\demo\\src\\main\\resources\\library.xml";

    public void withXSLT() throws Exception {
        final CamelContext camelContext = new DefaultCamelContext();

        try(final InputStream is = new FileInputStream(FILE)) {
            camelContext.start();
            camelContext.addRoutes(new XSLTRoute());

            final ProducerTemplate template = camelContext.createProducerTemplate();
            template.sendBody("direct:start", is);
        } finally {
            camelContext.stop();
        }
    }

    public void camelRest() throws Exception {
        final Main camel = new Main();
        camel.addRouteBuilder(new REST());
        camel.run();
    }

    private static class XSLTRoute extends RouteBuilder {

        @Override
        public void configure() throws Exception {
            from("direct:start")
                    .to("xslt:file:C:\\Users\\Miriam\\Desktop\\XML\\demo\\src\\main\\java\\com\\example\\demo\\xsl_library.xsl")
                    .to("stream:out");
        }

    }

}
