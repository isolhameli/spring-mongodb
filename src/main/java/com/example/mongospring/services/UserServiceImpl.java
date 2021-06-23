package com.example.mongospring.services;

import com.example.mongospring.model.Address;
import com.example.mongospring.model.User;
import com.example.mongospring.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final AddressService addressService;

    public UserServiceImpl(UserRepository userRepository, AddressService addressService) {
        this.userRepository = userRepository;
        this.addressService = addressService;
    }

    @Override
    public User createUser(User user) {
        List<Address> addresses = addressService.saveAll(user.getAddresses());
        user.setAddresses(addresses);
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void updateUser(User userToUpdate) {
        User user = userRepository.findById(userToUpdate.getId()).orElse(null);
        if (user==null){return;}
        user.setUsername(userToUpdate.getUsername());
        user.setPassword(userToUpdate.getPassword());
        addressService.removeAll(findAddressesToRemove(user.getAddresses(),userToUpdate.getAddresses()));
        List<Address> addressesToKeep = findAddressesToKeep(user.getAddresses(), userToUpdate.getAddresses());
        List<Address> newAddresses = addressService.saveAll(findAddressesToAdd(user.getAddresses(),userToUpdate.getAddresses()));
        user.setAddresses(Stream.concat(addressesToKeep.stream(),newAddresses.stream()).collect(Collectors.toList()));
        userRepository.save(user);
    }

    @Override
    public void changePassword(User userToUpdate) {
        User user = userRepository.findById(userToUpdate.getId()).orElse(null);
        if (user==null){return;}
        user.setPassword(userToUpdate.getPassword());
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    private List<Address> findAddressesToKeep(List<Address> originalAddresses, List<Address> newAdresses){
        List<String> newAddressesName = newAdresses.stream().map(el -> el.getStreet()).collect(Collectors.toList());
        return originalAddresses.stream().filter(el -> newAddressesName.contains(el.getStreet())).collect(Collectors.toList());
    }

    private List<Address> findAddressesToRemove(List<Address> originalAddresses, List<Address> newAdresses){
        List<String> newAddressesName = newAdresses.stream().map(el -> el.getStreet()).collect(Collectors.toList());
        return originalAddresses.stream().filter(el -> !newAddressesName.contains(el.getStreet())).collect(Collectors.toList());
    }

    private List<Address> findAddressesToAdd(List<Address> originalAddresses, List<Address> newAdresses){
        List<String> originalAddressesNames = originalAddresses.stream().map(el -> el.getStreet()).collect(Collectors.toList());
        return newAdresses.stream().filter(el -> !originalAddressesNames.contains(el.getStreet())).collect(Collectors.toList());
    }
}
