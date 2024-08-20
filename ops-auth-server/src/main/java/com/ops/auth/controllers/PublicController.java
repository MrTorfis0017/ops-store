package com.ops.auth.controllers;

import com.ops.common.dto.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public")
public class PublicController {

    private final JdbcUserDetailsManager userDetailsManager;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/users/create")
    public void createUser(@RequestBody CreateUserRequest createUserRequest) {
        userDetailsManager.createUser(
                User.builder()
                        .username(createUserRequest.getUsername())
                        .password(passwordEncoder.encode(createUserRequest.getPassword()))
                        .build()
        );
        addUserToGroup(createUserRequest.getUsername());
    }

    private void addUserToGroup(String userName) {
        userDetailsManager.addUserToGroup(userName, "GROUP_" + "USERS");
    }
}
