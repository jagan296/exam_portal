package com.exam.service;

import com.exam.model.User;
import com.exam.model.UserRole;

import java.util.Set;

public interface UserService {

    // creating user
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

    // get user by username
    public User getUser(String username);

    // delete user by userId
    public void deleteUser(Long userId);

    public User updateUser(User user) throws Exception;
}
