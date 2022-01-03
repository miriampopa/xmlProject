package com.library_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringBootWebApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootWebApplication.class, args);

        ApplicationContext context = new ClassPathXmlApplicationContext("Config.xml");
        DemoManager obj = (DemoManager) context.getBean("DemoMangerBean");

        obj.start();

    }

}