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
    private List<User> users;

    public UserService() {
        this.users = new ArrayList<>();
    }

    public void registerUser(User user) {
        for (User existsUser : users) {
            if (existsUser.getUsername().equals(user.getUsername())) {
                throw new UserAlreadyExistsException("用户已存在");
            }
        }
        user.setId(users.size() + 1);
        users.add(user);
    }

    public User login(String username, String password) {
        for (User existsUser : users) {
            if (existsUser.getUsername().equals(username) && existsUser.getPassword().equals(password)) {
                return existsUser;
            }
        }
        throw  new UserNameOrPasswordWrong("用户名或密码错误");
    }
}
