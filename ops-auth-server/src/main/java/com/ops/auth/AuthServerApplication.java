package com.ops.auth;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@SpringBootApplication
@EnableMethodSecurity
public class AuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

    @Bean
    public JdbcUserDetailsManager jdbc(DataSource dataSource) {
        JdbcUserDetailsManager jdbc = new JdbcUserDetailsManager(dataSource);
        jdbc.setEnableAuthorities(false);
        jdbc.setEnableGroups(true);
        return jdbc;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public ApplicationRunner initializeUsers(JdbcUserDetailsManager userDetailsManager) {
        return (args) -> {
            String userName = "admin";
            if (!userDetailsManager.userExists(userName)) {
                userDetailsManager.createUser(
                        User.builder()
                                .username(userName)
                                .password("{bcrypt}$2a$10$jdJGhzsiIqYFpjJiYWMl/eKDOd8vdyQis2aynmFN0dgJ53XvpzzwC")
                                .build()
                );
                userDetailsManager.createGroup("GROUP_USERS", AuthorityUtils.createAuthorityList("ROLE_USER"));
                userDetailsManager.addUserToGroup(userName, "GROUP_USERS");
                userDetailsManager.createGroup("GROUP_ADMINS", AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
                userDetailsManager.addUserToGroup(userName, "GROUP_ADMINS");
                userDetailsManager.createGroup("GROUP_MASTERS", AuthorityUtils.createAuthorityList("ROLE_MASTER"));
                userDetailsManager.addUserToGroup(userName, "GROUP_MASTERS");
            }
        };
    }
}