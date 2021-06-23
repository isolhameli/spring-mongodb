package com.example.mongospring.services;

import com.example.mongospring.model.Address;
import com.example.mongospring.repositories.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService{

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    @Override
    public List<Address> saveAll(List<Address> addresses) {
        return addressRepository.saveAll(addresses);
    }

    @Override
    public void removeAll(List<Address> addresses) {
        addressRepository.deleteAll(addresses);
    }
}
