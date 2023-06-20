package com.fatec.configuration;

import com.fatec.adapter.out.repository.UserRepository;
import com.fatec.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SkillMatrixConfiguration {

    @Bean
    public UserService userService(UserRepository userRepository){
        return new UserService(userRepository);
    }

}
