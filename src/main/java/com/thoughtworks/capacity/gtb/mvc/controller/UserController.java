package com.thoughtworks.capacity.gtb.mvc.controller;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Map;

@RestController
@Validated
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public ResponseEntity registerUser(@RequestBody @Valid User user) {
        userService.registerUser(user);
        return ResponseEntity.created(null).build();
    }

    @GetMapping("login")
    public ResponseEntity login(@RequestParam
                                @Size(max = 10, min = 3, message = "用户名不合法")
                                @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "用户名不合法")String username,
                                @RequestParam
                                @Size(max = 12, min = 5, message = "密码不合法") String password) {
        Map<String, Object> user = userService.login(username, password);
        return ResponseEntity.ok(user);
    }
}
