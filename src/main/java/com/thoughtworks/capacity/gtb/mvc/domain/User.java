package com.thoughtworks.capacity.gtb.mvc.domain;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class User {
    @Generated
    private Integer id;
    @NotBlank(message = "用户名不为空")
    @Size(max = 10, min = 3, message = "用户名不合法")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "用户名不合法")
    private String username;
    @NotBlank(message = "密码是不为空")
    @Size(max = 12, min = 5, message = "密码不合法")
    private String password;
    @Email(message = "邮箱地址不合法")
    private String email;
}
