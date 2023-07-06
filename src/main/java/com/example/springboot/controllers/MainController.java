package com.example.springboot.controllers;


import com.example.springboot.dao.CustomerDAO;
import com.example.springboot.dto.CustomerDTO;
import com.example.springboot.models.Customer;
import com.example.springboot.views.View;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController //indicates that the class is a specialized version of a controller that is primarily used for RESTful web services
@RequestMapping("/customers")
@AllArgsConstructor
public class MainController {

    private CustomerDAO customerDAO;

    @GetMapping("")
    @JsonView(View.Superadmin.class)
    public ResponseEntity<List<Customer>> getCustomers(){
        return new ResponseEntity<>(customerDAO.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id")int id){

       return new ResponseEntity<>(customerDAO.findById(id).get(),HttpStatus.OK);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public void saveCustomer(@RequestBody @Valid CustomerDTO customerDTO){
        Customer customer1 = new Customer();
        customer1.setName(customerDTO.getName());
        customer1.setAge(customerDTO.getAge());

        customerDAO.save(customer1);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") int id){
        customerDAO.deleteById(id);
    }
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCustomer(@PathVariable int id,@RequestBody Customer customer){
        Customer customer1 = customerDAO.findById(id).get();
        customer1.setName(customer.getName());
        customerDAO.save(customer1);
    }
    @GetMapping ("/name/{name}")
    @JsonView(View.Admin.class)
    public List<Customer> getByName(@PathVariable("name") String name){
      return customerDAO.findByName(name);
    }

}
