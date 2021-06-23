package com.example.mongospring.dtos.requests;

import com.example.mongospring.model.Address;

public class AddressCreateRequest {

    private String street;

    @Deprecated
    public AddressCreateRequest() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Address toModel(){
        return new Address(null, street);
    }
}
