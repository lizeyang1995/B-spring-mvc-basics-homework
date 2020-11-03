package com.thoughtworks.capacity.gtb.mvc.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capacity.gtb.mvc.domain.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @Order(1)
    void should_add_a_user() throws Exception {
        User user = User.builder().username("lizeyang").password("123456").email("123@123.com").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/register").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    void should_throw_error_when_username_exist() throws Exception {
        User user = User.builder().username("lizeyang").password("123456").email("123@123.com").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/register").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("用户已存在")))
                .andExpect(jsonPath("$.code", is(400)));
    }

    @Test
    @Order(3)
    void should_throw_error_when_username_size_invalid() throws Exception {
        User user = User.builder().username("li").password("123456").email("123@123.com").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/register").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("用户名不合法")))
                .andExpect(jsonPath("$.code", is(400)));
    }

    @Test
    @Order(4)
    void should_throw_error_when_email_invalid() throws Exception {
        User user = User.builder().username("lizeyang").password("123456").email("123123.com").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/register").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("邮箱地址不合法")))
                .andExpect(jsonPath("$.code", is(400)));
    }

    @Test
    @Order(5)
    void should_throw_error_when_password_is_null() throws Exception {
        User user = User.builder().username("lizeyang").password(null).email("123@123.com").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/register").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("密码是不为空")))
                .andExpect(jsonPath("$.code", is(400)));
    }

    @Test
    @Order(6)
    void should_throw_error_when_username_is_null() throws Exception {
        User user = User.builder().username(null).password("123456").email("123@123.com").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/register").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("用户名不为空")))
                .andExpect(jsonPath("$.code", is(400)));
    }

    @Test
    @Order(7)
    void should_throw_error_when_password_invalid() throws Exception {
        User user = User.builder().username("lizeyang").password("123").email("123@123.com").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/register").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("密码不合法")))
                .andExpect(jsonPath("$.code", is(400)));
    }

    @Test
    @Order(8)
    void should_login_success() throws Exception {
        mockMvc.perform(get("/login?username=lizeyang&password=123456"))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.username", is("lizeyang")))
                .andExpect(jsonPath("$.password", is("123456")))
                .andExpect(jsonPath("$.email", is("123@123.com")))
                .andExpect(status().isOk());
    }
}
