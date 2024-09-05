package org.dailycode.springbootbackend.service;


import org.dailycode.springbootbackend.entity.User;
import org.dailycode.springbootbackend.exceptions.UserNotFoundException;
import org.dailycode.springbootbackend.model.UserModel;
import org.dailycode.springbootbackend.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServicesImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User insertUser(User user) {
        User userEntity = new User();
        BeanUtils.copyProperties(user, userEntity);
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> fetchUserList() {
        return userRepository.findAll().stream()
                .map(user -> new User(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail()))
                .collect(Collectors.toList());
    }


    @Override
    public UserModel getUserById(Long id) {
        User user = userRepository.findById(id).get();
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(user, userModel);
        return userModel;
    }

    @Override
    public boolean deleteUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
            return true;
        } else {
            return false;
        }
    }


    @Override
    public User updateUser(Long id, User user) {
        User userEntity = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        userEntity.setEmail(user.getEmail());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        return userRepository.save(userEntity);
    }


}
