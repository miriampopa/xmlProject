package com.library_project.repository;

import com.library_project.model.User;
import com.library_project.services.UserService;
import com.library_project.utils.DataUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Innova on 02-10-2017.
 */
@Component
public class UserRepository {

    private static final List<User> users = DataUtils.getUsersMethod();

    public User findUser(int id){
        return users.get(id);
    }
}


