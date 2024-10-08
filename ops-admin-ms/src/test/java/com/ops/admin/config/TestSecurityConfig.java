package com.ops.admin.config;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

@TestConfiguration
public class TestSecurityConfig {

    @Bean
    public ClientRegistrationRepository mockClientRegistrationRepository() {
        return Mockito.mock(ClientRegistrationRepository.class);
    }
}

