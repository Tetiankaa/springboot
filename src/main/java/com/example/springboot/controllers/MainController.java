package com.example.springboot.controllers;


import com.example.springboot.dto.CustomerDTO;
import com.example.springboot.models.Customer;
import com.example.springboot.services.CustomerService;
import com.example.springboot.views.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.List;



@RestController //indicates that the class is a specialized version of a controller that is primarily used for RESTful web services
@RequestMapping("/customers")
@AllArgsConstructor
public class MainController {

    private CustomerService customerService;


    @PostMapping("/image")
    @ResponseStatus(HttpStatus.OK)
    public void saveImage(@RequestParam String name, @RequestParam MultipartFile file) throws IOException {
        file.transferTo(new File(System.getProperty("user.home")+File.separator+"folder"+File.separator+file.getOriginalFilename()));
        customerService.save(new Customer(name, file.getOriginalFilename()));
    }

    @GetMapping("/image")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getImages(){
       return customerService.findImages();
    }
}
