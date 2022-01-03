package com.library_project.resource;

import com.library_project.model.User;
import com.library_project.utils.DataUtils;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/user")
@Api(value = "User Resource REST Endpoint", description = "Shows the user info")
public class UserController {

    @ApiOperation(value = "Returns user list")
    @GetMapping
    public List<User> getUsers() {
        List<com.library_project.model.User> users = new ArrayList<>();
        users = DataUtils.getUsersMethod();
        return users;
    }

    @ApiOperation(value = "Search by username")
    @GetMapping("/{userName}")
    public List<User> getUser(@PathVariable("userName") final String userName)
    {
        List<com.library_project.model.User> users = new ArrayList<>();
        users = DataUtils.getUsersMethod();
        return users.stream()
                    .filter(x -> x.getUsername().equalsIgnoreCase(userName))
                    .collect(Collectors.toList());
    }

    @ApiOperation(value = "Create user")
    @PostMapping("/user")
    public User create(@RequestBody User newUser) {
        DataUtils.addUser(newUser);
        return newUser;
    }

//    private class User {
//
//        @ApiModelProperty(notes = "name of the User")
//        private String userName;
//
//        @ApiModelProperty(notes = "salary of the user")
//        private Long salary;
//
//        public User(String userName, Long salary) {
//            this.userName = userName;
//            this.salary = salary;
//        }
//
//        public String getUserName() {
//            return userName;
//        }
//
//        public void setUserName(String userName) {
//            this.userName = userName;
//        }
//
//        public Long getSalary() {
//            return salary;
//        }
//
//        public void setSalary(Long salary) {
//            this.salary = salary;
//        }
//    }
}

