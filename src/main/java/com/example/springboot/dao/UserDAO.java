package com.example.springboot.dao;

import com.example.springboot.models.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    @Query("select u from User u where u.name=:name")
    List<User> getUserByName(@Param("name")String name);

     List<User> findByName(String name);
}
