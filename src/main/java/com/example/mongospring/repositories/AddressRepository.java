package com.example.mongospring.repositories;

import com.example.mongospring.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address,String> {

}
