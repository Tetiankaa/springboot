package com.example.springboot.dao;

import com.example.springboot.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDAO extends JpaRepository<Customer,Integer> {
    Customer findByLogin(String login);
}
