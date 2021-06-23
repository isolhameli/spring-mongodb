package com.example.mongospring.services;

import com.example.mongospring.dtos.requests.AddressCreateRequest;
import com.example.mongospring.model.Address;
import com.example.mongospring.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AddressService {

    List<Address> saveAll(List<Address> addresses);

    void removeAll(List<Address> addresses);
}
