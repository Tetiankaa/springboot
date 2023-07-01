package com.example.springboot.dao;

import com.example.springboot.models.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Customer customer){
        entityManager.persist(customer);
    }
    public List<Customer> findAll(){
         return entityManager.createQuery("select c from Customer c", Customer.class).getResultList();
}
    public void update(Customer customer){
        entityManager.merge(customer);
}
    public Customer findById(int id){
        return entityManager.find(Customer.class,id);
}

    public void removeById(int id){
         entityManager.remove(findById(id));
}


}
