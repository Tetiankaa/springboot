package com.example.springboot.services;

import com.example.springboot.dao.UserDAO;
import com.example.springboot.models.User;
import com.example.springboot.models.UserDTO;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserDAO userDAO;
    private MailService mailService;

    public void save(User user) {
        if (user==null){
            throw new  RuntimeException();
        }
        userDAO.save(user);
//        mailService.sendMail(user);

    }

    public ResponseEntity<List<User>> findAllWithSpecifications(Specification<User> criteria){
        List<User> all = userDAO.findAll(criteria);
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    public ResponseEntity<List<User>> findAll(){
       List<User> all = userDAO.findAll();
     return new ResponseEntity<>(all,HttpStatus.OK);
    }

    public ResponseEntity<UserDTO> getUser(int id){
        UserDTO userDTO=null;
        if (id>0){
            userDTO= new UserDTO(userDAO.findById(id).get());
        }
       return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }
    public void deleteUser(int id){
        userDAO.deleteById(id);
    }

    public void updateUserName(int id, User user){
        User user1 = userDAO.findById(id).get();
        user1.setName(user.getName());
        userDAO.save(user1);
    }
    public void updateUserAge(int id, User user){
        User u = userDAO.findById(id).get();
        u.setAge(user.getAge());
        userDAO.save(u);
    }
    public ResponseEntity<List<User>> getUserByName(String name){
        return  new ResponseEntity<>(userDAO.getUserByName(name),HttpStatus.OK);
    }
}
