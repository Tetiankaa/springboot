package com.example.springboot.controllers;

import com.example.springboot.dao.ClientUserDAO;
import com.example.springboot.models.ClientUser;
import com.example.springboot.models.ClientUserDTO;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//Every request handling method of the controller class automatically serializes return objects into HttpResponse.
@RestController //indicates that the class is a specialized version of a controller that is primarily used for RESTful web services
@AllArgsConstructor
@RequestMapping(value = "/users")//we establish the base URL for all the handler methods within the class.
public class MainController {

    private ClientUserDAO clientUserDAO;
    private PasswordEncoder passwordEncoder;

    @PostMapping("/clients/save")
    public void saveClient(ClientUserDTO clientUserDTO){
        ClientUser user = ClientUser.builder()
                .email(clientUserDTO.getEmail())
                .password(passwordEncoder.encode(clientUserDTO.getPassword()))
                .build();
        clientUserDAO.save(user);
    }
    @PostMapping("/clients/login")
    public void login(){
    }

    @GetMapping("/clients/all")
    public List<String> getClientsWithoutSensitiveInformation(){
       return clientUserDAO.findAll()
               .stream()
               .map(ClientUser::getEmail)
               .collect(Collectors.toList());
    }

    @GetMapping("/admin/all")
    public List<ClientUser> getAllClients(){
        return clientUserDAO.findAll();
    }

}
