package com.example.springboot.controllers;

import com.example.springboot.dao.UserDAO;
import com.example.springboot.dao.mongodb.ClientDAO;
import com.example.springboot.models.User;
import com.example.springboot.models.UserDTO;
import com.example.springboot.models.mongo_models.Client;
import com.example.springboot.queryFilters.UserSpecifications;
import com.example.springboot.services.UserService;
import com.example.springboot.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

//Every request handling method of the controller class automatically serializes return objects into HttpResponse.
@RestController //indicates that the class is a specialized version of a controller that is primarily used for RESTful web services
@AllArgsConstructor
@RequestMapping(value = "/users")//we establish the base URL for all the handler methods within the class.
public class MainController {

    private UserService userService;
    private ClientDAO clientDAO;
    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public void saveUser(@RequestBody @Valid User user) {

        userService.save(user);

        clientDAO.save(new Client(user.getName(), user.getAge()));

    }
    @GetMapping("")
    @JsonView(value = {Views.Client.class})
    public ResponseEntity<List<User>> getUsers(){
//        List<User> all = userDAO.findAll();
//        return new ResponseEntity<>(all,HttpStatus.OK);
        //******
//        List<User> all = userDAO.findAll((root, query, criteriaBuilder) -> criteriaBuilder.gt(root.get("age"),10));
//        return new ResponseEntity<>(all,HttpStatus.OK);
        //*****
//        List<User> all = userDAO.findAll(UserSpecifications.byAge(15));
//        return new ResponseEntity<>(all,HttpStatus.OK);
        //*****
       return userService.findAllWithSpecifications(UserSpecifications.byName("tetiana"));
    }
//    @GetMapping("")
//    @JsonView(value = Views.Client.class)
//    public ResponseEntity<List<User>> getAllUsers(){
//       return userService.findAll();
//    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable int id){
//        User user = userDAO.findById(id).get();
//        User user = userDAO.findById(id).get();
//        UserDTO dto = new UserDTO(user);
//        return new ResponseEntity<>(dto,HttpStatus.OK);
       return userService.getUser(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable int id){
        userService.deleteUser(id);
    }
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable int id, @RequestBody User user){
     userService.updateUserName(id, user);
     userService.updateUserAge(id, user);
    }
    @GetMapping("/name/{nameValue}")
    @JsonView(value = Views.Admin.class)
    public ResponseEntity<List<User>> userByName(@PathVariable String nameValue){
//        List<User> userByName = userDAO.getUserByName(nameValue);
//        return userByName;
       return userService.getUserByName(nameValue);
    }
    @PostMapping("/saveWithAvatar")
    public void saveWithAvatar(@RequestParam String name, @RequestParam int age, @RequestParam MultipartFile avatar) throws IOException {

        User user = new User(name, age);

        String originalFilename = avatar.getOriginalFilename();

        user.setAvatar("/photo/" + originalFilename);//зберігання посилання на певну картинку

        String path = System.getProperty("user.home") + File.separator + originalFilename;
        File file = new File(path);
        avatar.transferTo(file);
        userService.save(user);
    }
}
