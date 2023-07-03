package com.example.springboot.dao;

import com.example.springboot.models.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CustomerDAO {
    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    public void save(Customer customer){
        entityManager.merge(customer);
    }
    public List<Customer> findAll(){
         return entityManager.createQuery("select c from Customer c", Customer.class).getResultList();
}
    @Transactional
    public void update(Customer customer){
    entityManager.merge(customer);
}
    public Customer findById(int id){
        return entityManager.find(Customer.class,id);
}
    @Transactional
    public void removeById(int id){
         entityManager.remove(findById(id));
}


}
//By adding the @Transactional annotation to the save() method, Spring will automatically handle the transaction
// management for you. Now, when you call the save() method, a transaction will be created, the customer
// will be persisted, and the transaction will be committed.
//
