package com.fatec.configuration;

import com.fatec.adapter.out.dao.SkillDaoPort;
import com.fatec.adapter.out.dao.SkillDaoPostgresAdapter;
import com.fatec.adapter.out.repository.SkillRepository;
import com.fatec.adapter.out.repository.UserRepository;
import com.fatec.service.SkillService;
import com.fatec.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SkillMatrixConfiguration {

    @Bean
    public UserService userService(UserRepository userRepository){
        return new UserService(userRepository);
    }


    @Bean
    public SkillService skillService(SkillDaoPort skillAdapter){
        return new SkillService(skillAdapter);
    }
    @Bean
    public SkillDaoPort skillDaoPort(SkillRepository skillRepository){
        return new SkillDaoPostgresAdapter(skillRepository);
    }

}
