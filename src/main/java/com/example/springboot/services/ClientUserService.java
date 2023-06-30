package com.example.springboot.services;

import com.example.springboot.dao.ClientUserDAO;
import com.example.springboot.dao.UserDAO;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientUserService implements UserDetailsService {

    private ClientUserDAO clientUserDAO;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return clientUserDAO.findByEmail(username);
    }
}
