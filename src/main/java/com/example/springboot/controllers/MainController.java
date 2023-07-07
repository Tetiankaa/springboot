package com.example.springboot.controllers;


import com.example.springboot.dao.CustomerDAO;
import com.example.springboot.dto.CustomerDTO;
import com.example.springboot.models.Customer;
import com.example.springboot.services.CustomerService;
import com.example.springboot.services.MailService;
import com.example.springboot.views.View;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.mail.MessagingException;
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

    private CustomerService customerService;
    private MailService mailService;

    @GetMapping("")
    public ResponseEntity<List<CustomerDTO>> getCustomers(){
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("id")int id){
       return new ResponseEntity<>(customerService.findById(id),HttpStatus.OK);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public void saveCustomer(@RequestBody @Valid CustomerDTO customerDTO){
       customerService.save(customerDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") int id){

        customerService.delete(id);
    }
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCustomer(@PathVariable int id,@RequestBody CustomerDTO customerDTO){
        customerService.update(id, customerDTO);
    }

    @GetMapping ("/name/{name}")
    public List<CustomerDTO> getByName(@PathVariable("name") String name){
      return customerService.findByName(name);
    }

    @PostMapping("/send")
    @JsonView(View.Admin.class)
    @ResponseStatus(HttpStatus.OK)
    public void sendEmail(@RequestBody Customer customer) throws MessagingException {
        mailService.send(customer.getEmail());
    }
}
