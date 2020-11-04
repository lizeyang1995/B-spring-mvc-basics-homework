package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.exception.UserAlreadyExistsException;
import com.thoughtworks.capacity.gtb.mvc.exception.UserNameOrPasswordWrong;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private Map<Integer, User> userMap;

    public UserService() {
        this.userMap = new HashMap<>();
    }

    public void registerUser(User user) {
        for (Map.Entry<Integer, User> existsUser : userMap.entrySet()) {
            if (existsUser.getValue().getUsername().equals(user.getUsername())) {
                throw new UserAlreadyExistsException("用户已存在");
            }
        }
        user.setId(userMap.size() + 1);
        userMap.put(userMap.size() + 1, user);
    }

    public User login(String username, String password) {
        for (Map.Entry<Integer, User> existsUser : userMap.entrySet()) {
            if (existsUser.getValue().getUsername().equals(username) && existsUser.getValue().getPassword().equals(password)) {
                return existsUser.getValue();
            }
        }
        throw  new UserNameOrPasswordWrong("用户名或密码错误");
    }
}
