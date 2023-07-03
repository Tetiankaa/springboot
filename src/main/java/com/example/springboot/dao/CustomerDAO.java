package com.example.springboot.dao;


import com.example.springboot.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerDAO extends JpaRepository<Customer,Integer> {
//    @Query("select n from Customer n where n.name=:name")
//    List<Customer> findByName(@Param("name") String name);

    List<Customer> findByName(String name);
}

