package com.library_project.model;

import com.library_project.utils.Common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "user", propOrder = {
        "id",
        "user_name",
        "user_password",
        "user_email"
})
public class User {
    private String id;
    @XmlElement(required = true)
    private String user_name;
    @XmlElement(required = true)
    private String user_password;
    @XmlElement(required = true)
    private String user_email;

//    public User(String username, String password, String email, String id) {
//        this.user_name = username;
//        this.user_password = password;
//        this.user_email = email;
//        this.id = "user_" + Common.getRandomNumber();
//    }

    public User(){}

    @Override
    public String toString() {
        return "User{" +
                "username='" + user_name + '\'' +
                ", password='" + user_password + '\'' +
                ", email='" + user_email + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getUsername() {
        return user_name;
    }

    public void setUsername(String username) {
        this.user_name = username;
    }

    public String getPassword() {
        return user_password;
    }

    public void setPassword(String password) {
        this.user_password = password;
    }

    public String getEmail() {
        return user_email;
    }

    public void setEmail(String email) {
        this.user_email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
