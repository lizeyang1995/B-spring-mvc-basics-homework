package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
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
        Map<String, Object> information = new HashMap<>();
        information.put("username", user.getUsername());
        information.put("password", user.getPassword());
        information.put("email", user.getEmail());
        users.add(information);
    }
}
