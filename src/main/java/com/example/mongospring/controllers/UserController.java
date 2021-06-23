package com.example.mongospring.controllers;

import com.example.mongospring.dtos.requests.UserCreateRequest;
import com.example.mongospring.model.User;
import com.example.mongospring.services.UserService;
import com.example.mongospring.services.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id){
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody UserCreateRequest userRequest){
        userRequest.setId(null);
        User user = userService.createUser(userRequest.toModel());
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri()).build();
    }

    @PutMapping
    public ResponseEntity updateUser(@RequestBody UserCreateRequest userCreateRequest){
        userService.updateUser(userCreateRequest.toModel());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/password")
    public ResponseEntity updatePassword(@RequestBody UserCreateRequest userCreateRequest){
        userService.changePassword(userCreateRequest.toModel());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
