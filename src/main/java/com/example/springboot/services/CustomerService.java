package com.example.springboot.services;

import com.example.springboot.dao.CustomerDAO;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService implements UserDetailsService {

    private CustomerDAO customerDAO;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return customerDAO.findByLogin(login);
    }
}
//У security свій зв'язок з базою даних і тому, щоб знайти користувача за його логіном або емайлом потрібно
//імплементувати UserDetailsService. Напряму через DAO не буде працювати.
