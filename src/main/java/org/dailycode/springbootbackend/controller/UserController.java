package org.dailycode.springbootbackend.controller;

import org.dailycode.springbootbackend.entity.User;
import org.dailycode.springbootbackend.model.UserModel;
import org.dailycode.springbootbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(value = "http://localhost:3000/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public User saveUser(@RequestBody User user) {
        return userService.insertUser(user);
    }

    @GetMapping("/users")
    public List<User> getUserList() {
        return userService.fetchUserList();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable("id") Long id) {
        UserModel user = null;
        user = userService.getUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable("id") Long id) {
        boolean isDeleted = false;
        isDeleted = userService.deleteUser(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleteStatus", isDeleted);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id ,@RequestBody User user) {
        user = userService.updateUser(id, user);
        return ResponseEntity.ok(user);
    }

}
