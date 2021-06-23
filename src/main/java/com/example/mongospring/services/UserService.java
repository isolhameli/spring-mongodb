package com.example.mongospring.services;

import com.example.mongospring.dtos.requests.UserCreateRequest;
import com.example.mongospring.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    List<User> findAllUsers();

    User findUserById(String id);

    void updateUser(User userToUpdate);

    void changePassword(User userToUpdate);

    void deleteUser(String id);
}
