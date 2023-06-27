package com.example.springboot.dao.mongodb;

import com.example.springboot.models.mongo_models.Client;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientDAO extends MongoRepository<Client, ObjectId> {
}
