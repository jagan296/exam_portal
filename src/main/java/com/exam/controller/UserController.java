package com.exam.controller;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService userService;

    // creating user
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {
        Set<UserRole> userRoles = new HashSet<>();
        UserRole userRole = new UserRole();
        Role role = new Role();

        role.setRoleName("NORMAL");
        role.setRoleId(55L);

        userRole.setRole(role);
        userRole.setUser(user);

        userRoles.add(userRole);

        return this.userService.createUser(user, userRoles);
    }

    // get user by username
    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username) {
        return this.userService.getUser(username);
    }

    // delete user by userId
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        this.userService.deleteUser(userId);
    }

    // update user
    @PostMapping("/updateUser")
    public User updateUser(@RequestBody User user) throws Exception {
        return this.userService.updateUser(user);
    }
}
