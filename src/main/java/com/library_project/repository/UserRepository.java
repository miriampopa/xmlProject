package com.library_project.repository;

import com.part2.models.soap.user.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Innova on 02-10-2017.
 */
@Component
public class UserRepository {

    private static final Map<Integer, User> emps = new HashMap<>();

    @PostConstruct
    public void init(){
        User emp1 = new User();
        emp1.setId(1);
        emp1.setUsername("Suzi");
        emp1.setUserPassword("password");
        emp1.setUserEmail("suzi@com.innova.com");
        emps.put(emp1.getId(), emp1);
    }

    public User findUser(int id){
        return emps.get(id);
    }
}


