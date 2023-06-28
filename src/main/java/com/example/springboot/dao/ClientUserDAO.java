package com.example.springboot.dao;

import com.example.springboot.models.ClientUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientUserDAO extends JpaRepository<ClientUser,Integer> {

    ClientUser findByEmail(String email);
}
