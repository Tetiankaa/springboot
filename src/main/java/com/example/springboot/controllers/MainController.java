package com.example.springboot.controllers;


import com.example.springboot.dao.CustomerDAO;
import com.example.springboot.models.Customer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController //indicates that the class is a specialized version of a controller that is primarily used for RESTful web services
@RequestMapping("/customers")
@AllArgsConstructor
public class MainController {

    private CustomerDAO customerDAO;

    @GetMapping("")
    public ResponseEntity<List<Customer>> getCustomers(){
        return new ResponseEntity<>(customerDAO.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id")int id){
       return new ResponseEntity<>(customerDAO.findById(id),HttpStatus.OK);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public void saveCustomer(@RequestBody Customer customer){
        customerDAO.save(customer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") int id){
        customerDAO.removeById(id);
    }
    @PatchMapping("/{id}")
    public void updateCustomer(@RequestBody Customer customer){
        customerDAO.update(customer);
    }

}
