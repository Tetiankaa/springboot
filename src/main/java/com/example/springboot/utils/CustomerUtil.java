package com.example.springboot.utils;

import com.example.springboot.dto.CustomerDTO;
import com.example.springboot.models.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerUtil {

    public List<CustomerDTO> convertCustomerToDTO(List<Customer> customerList){
      return customerList.stream()
              .map(customer -> new CustomerDTO(customer.getName(), customer.getAge()))
              .collect(Collectors.toList());
    }

    public CustomerDTO convertCustomerToDTO(Customer customer){
      return  new CustomerDTO(customer.getName(), customer.getAge());
    }
    public Customer convertDTOToCustomer(CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setAge(customerDTO.getAge());
        return customer;
    }
}
