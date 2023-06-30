package com.example.springboot.controllers;

import com.example.springboot.dao.ClientUserDAO;
import com.example.springboot.models.ClientUser;
import com.example.springboot.models.ClientUserDTO;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

//Every request handling method of the controller class automatically serializes return objects into HttpResponse.
@RestController //indicates that the class is a specialized version of a controller that is primarily used for RESTful web services
@AllArgsConstructor
public class MainController {

    private ClientUserDAO clientUserDAO;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    @PostMapping("/clients/save")
    public void saveClient(@RequestBody ClientUserDTO clientUserDTO) {
//        clientUserDAO.save(ClientUser.builder() //with builder doesnt work column Role
//                .email(clientUserDTO.getEmail())
//                .password(passwordEncoder.encode(clientUserDTO.getPassword() ))
//                .build());

        ClientUser clientUser = new ClientUser();
        clientUser.setEmail(clientUserDTO.getUsername());
        clientUser.setPassword(passwordEncoder.encode(clientUserDTO.getPassword()));
        clientUserDAO.save(clientUser);
    }
    @PostMapping("/clients/login")
    public ResponseEntity<String> login(@RequestBody ClientUserDTO clientUserDTO){
        System.out.println("clientUserDTO "+clientUserDTO);

        //represents an authentication request token for username and password-based authentication.(тобто представляє токен для запиту для автентифікаії на основі логіна і пароля)
        UsernamePasswordAuthenticationToken authenticationToken  = new UsernamePasswordAuthenticationToken(clientUserDTO.getUsername(), clientUserDTO.getPassword());
        System.out.println("auth "+authenticationToken );


        //and previos token we need to pass here, it will check if user is exist with these credentials
        // Pass the authentication token to the authentication manager to perform the authentication process
        Authentication authenticate = authenticationManager.authenticate(authenticationToken );
        System.out.println("authenticate "+authenticate);

        if (authenticate!=null){
            String jwtToken = Jwts.builder()
                    .setSubject(authenticate.getName())
                    .signWith(SignatureAlgorithm.HS512, "tanya".getBytes(StandardCharsets.UTF_8))
                    .compact();
            System.out.println("jwt token "+jwtToken);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization","Bearer " + jwtToken);

            return new ResponseEntity<>("login successfully",httpHeaders,HttpStatus.OK);
        }
        return new ResponseEntity<>("bad",HttpStatus.FORBIDDEN);
    }

    @GetMapping("/clients/all")
    public List<String> getClientsWithoutSensitiveInformation(){
       return clientUserDAO.findAll()
               .stream()
               .map(ClientUser::getEmail)
               .collect(Collectors.toList());
    }

    @GetMapping("/admin/admin")
    public ResponseEntity<List<ClientUser>> getAllClients(){
        return new  ResponseEntity<>(clientUserDAO.findAll(),HttpStatus.OK);
    }

}
