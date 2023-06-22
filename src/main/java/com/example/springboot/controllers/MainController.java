package com.example.springboot.controllers;

import com.example.springboot.dao.UserDAO;
import com.example.springboot.models.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController //indicates that the class is a specialized version of a controller that is primarily used for RESTful web services
@AllArgsConstructor
public class MainController {

    private UserDAO userDAO;
    @PostMapping("/users")
    public void saveUser(@RequestBody User user){
        userDAO.save(user);
    }
    @GetMapping("/users")
    public List<User> getUsers(){
        List<User> all = userDAO.findAll();
        return all;
    }
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id){
        User user = userDAO.findById(id).get();
        return user;
    }
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        userDAO.deleteById(id);
    }
    @PatchMapping("/users/{id}")
    public void updateUser(@PathVariable int id, @RequestBody User user){
        User user1 = userDAO.findById(id).get();
        user1.setName(user.getName());
        userDAO.save(user1);
    }
    @GetMapping("/users/name/{nameValue}")
    public List<User> userByName(@PathVariable String nameValue){
//        List<User> userByName = userDAO.getUserByName(nameValue);
//        return userByName;

        return userDAO.findByName(nameValue);
    }

}
