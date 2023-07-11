package com.example.springboot.services;

import com.example.springboot.dao.CustomerDAO;
import com.example.springboot.dto.CustomerDTO;
import com.example.springboot.models.Customer;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService implements UserDetailsService {

    private CustomerDAO customerDAO;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return customerDAO.findByLogin(login);
    }
    ////У security свій зв'язок з базою даних і тому, щоб знайти користувача за його логіном або емайлом потрібно
    ////імплементувати UserDetailsService. Напряму через DAO не буде працювати.

public void save(CustomerDTO customerDTO){
    Customer customer = new Customer();
    customer.setLogin(customerDTO.getLogin());
    customer.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
    customerDAO.save(customer);
}


public ResponseEntity<List<Customer>> getAll(){
       return new ResponseEntity<>(customerDAO.findAll(),HttpStatus.OK);
}
}

