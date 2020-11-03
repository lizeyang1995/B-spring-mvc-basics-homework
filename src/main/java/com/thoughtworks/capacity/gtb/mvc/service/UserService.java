package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.exception.UserAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    List<Map<String, Object>> users;

    public UserService() {
        this.users = new ArrayList<>();
    }

    public void registerUser(User user) {
        for (Map<String, Object> existsUser : users) {
            if (existsUser.get("username").equals(user.getUsername())) {
                throw new UserAlreadyExistsException("用户已存在");
            }
        }
        Map<String, Object> userInformation = new HashMap<>();
        userInformation.put("username", user.getUsername());
        userInformation.put("password", user.getPassword());
        userInformation.put("email", user.getEmail());
        users.add(userInformation);
    }
}