package com.j2ee.buildpcchecker.configuration;

import com.j2ee.buildpcchecker.entity.User;
import com.j2ee.buildpcchecker.enums.Role;
import com.j2ee.buildpcchecker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.HashSet;

@Slf4j
@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig
{
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository)
    {
        return args -> {
            if(userRepository.findByEmail("haoaboutme@gmail.com").isEmpty())
            {
                var role = new HashSet<String>();
                role.add(Role.ADMIN.name());

                User adminUser = User.builder()
                        .username("Admin HaoAboutMe")
                        .password(passwordEncoder.encode("admin"))
                        .email("haoaboutme@gmail.com")
                        .dateOfBirth(LocalDate.parse("2026-01-01"))
                        //.roles(role)
                        .build();

                userRepository.save(adminUser);
                log.warn("Admin User created with default password: admin, please change it after first login!");
            }
        };
    }
}
