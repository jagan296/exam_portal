package com.exam.service.impl;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User localUser = this.userRepository.findByUsername(user.getUsername());
        if(localUser != null) {
            System.out.println("User is already present");
            throw new Exception("User already present!!");
        } else {
            for(UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            localUser = this.userRepository.save(user);
        }
        return localUser;
    }

    // get user by username
    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    // delete user by userId
    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }

    // update user
    @Override
    public User updateUser(User user) throws Exception {
        User localUser = this.userRepository.findByUsername(user.getUsername());
        if(localUser == null) {
            System.out.println("User is not present");
            throw new Exception("User is not present!!");
        } else {
            this.userRepository.deleteById(localUser.getId());
            localUser = this.userRepository.save(user);
        }
        return localUser;
    }
}
