package com.example.springboot.controllers;

import com.example.springboot.dto.CustomerDTO;
import com.example.springboot.models.Customer;
import com.example.springboot.services.CustomerService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;
    private AuthenticationManager authenticationManager;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public void saveCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.save(customerDTO);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> loginCustomer(@RequestBody CustomerDTO customerDTO){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(customerDTO.getLogin(), customerDTO.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (authenticate != null){
            String token = Jwts.builder()
                    .setSubject(authenticate.getName())
                    .signWith(SignatureAlgorithm.HS512, "tanya".getBytes(StandardCharsets.UTF_8))
                    .compact();

            HttpHeaders header = new HttpHeaders();
            header.add("Authorization","Bearer " + token);

            return new ResponseEntity<>("login completed:)",header, HttpStatus.OK);
        }
        return new ResponseEntity<>("failed",HttpStatus.FORBIDDEN);
    }

    @GetMapping("/admin/admin")
    public ResponseEntity<List<Customer>> getAllCustomers(){
            return new ResponseEntity<>(customerService.getAll().getStatusCode());
    }

}
