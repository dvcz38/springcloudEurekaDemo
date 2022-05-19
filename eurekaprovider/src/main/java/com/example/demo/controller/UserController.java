package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @RequestMapping("/user")
    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        users.add(new User(1, "zhangsan", 20));
        users.add(new User(2, "lisi", 22));
        users.add(new User(3, "wangwu", 30));
        return users;
    }
}