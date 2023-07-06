package com.example.springboot.services;

import com.example.springboot.dao.CustomerDAO;
import com.example.springboot.dto.CustomerDTO;
import com.example.springboot.models.Customer;
import com.example.springboot.utils.CustomerUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerDAO customerDAO;
    private CustomerUtil customerUtil;

    public List<CustomerDTO> findAll(){
        List<Customer> all = customerDAO.findAll();
        return customerUtil.convertCustomerToDTO(all);
    }
    public CustomerDTO findById(int id){
        Customer customer = customerDAO.findById(id).get();
        return customerUtil.convertCustomerToDTO(customer);
    }

    public void save(CustomerDTO customerDTO){
        Customer customer = customerUtil.convertDTOToCustomer(customerDTO);
        customerDAO.save(customer);
    }

    public void delete(int id){
        customerDAO.deleteById(id);
    }
    public void update(int id,CustomerDTO customerDTO){
        Customer customer1 = customerDAO.findById(id).get();
      customer1.setName(customerDTO.getName());
      customer1.setAge(customerDTO.getAge());
      customerDAO.save(customer1);
    }
    public List<CustomerDTO> findByName(String name){
        List<Customer> listByName = customerDAO.findByName(name);
        List<CustomerDTO> convertedList = customerUtil.convertCustomerToDTO(listByName);
        return convertedList;
    }

}
