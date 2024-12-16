package com.pdtech.talentnet.user.services;

import com.pdtech.talentnet.user.models.User;
import com.pdtech.talentnet.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
}
