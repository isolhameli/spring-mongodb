package com.example.mongospring.dtos.requests;

import com.example.mongospring.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserCreateRequest {

    private String id;
    private String username;
    private String password;
    private List<AddressCreateRequest> addresses;

    public UserCreateRequest() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User toModel(){
        return new User(id, username, password, addresses.stream().map(AddressCreateRequest::toModel).collect(Collectors.toList()));
    }

    public List<AddressCreateRequest> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressCreateRequest> addresses) {
        this.addresses = addresses;
    }
}
