package com.example.springboot.services;

import com.example.springboot.dao.CustomerDAO;
import com.example.springboot.models.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerDAO customerDAO;

    public List<Customer> findImages(){
       return customerDAO.findAll();
    }
    public void save(Customer customer){
        customerDAO.save(customer);
    }

}
