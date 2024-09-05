package org.dailycode.springbootbackend.service;


import org.dailycode.springbootbackend.entity.User;
import org.dailycode.springbootbackend.model.UserModel;

import java.util.List;

public interface UserService {
    User insertUser(User user);

    List<User> fetchUserList();

    UserModel getUserById(Long id);

    boolean deleteUser(Long id);

    User updateUser(Long id, User user);
}
