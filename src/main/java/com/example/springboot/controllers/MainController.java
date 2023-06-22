package com.example.springboot.controllers;

import com.example.springboot.models.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController //indicates that the class is a specialized version of a controller that is primarily used for RESTful web services
public class MainController {

    private List<User> users = new ArrayList<>();

    public MainController() {
        users.add(new User(1,"tanya"));
        users.add(new User(2,"vira"));
        users.add(new User(3,"katya"));
    }

    @GetMapping("/")
    public String homePage(){
        return "hello";
    }
    @GetMapping("/users")
    public List<User> getUsers(){
        return users;
    }
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") int id){
        List<User> collect = users.stream()
                .filter(user -> user.getId() == id)
                .toList();
    return collect.get(0);
    }
    @PostMapping("/users")
    public List<User> saveUser(@RequestBody User user){
        users.add(user);
        return users;
    }

}
