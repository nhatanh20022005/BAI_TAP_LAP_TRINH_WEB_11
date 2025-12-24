package vn.bt10.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.bt10.entity.Role;
import vn.bt10.entity.User;
import vn.bt10.repository.UserRepository;

/**
 * Simple data initializer that inserts a default admin and user into the
 * database on application startup.  This makes it easy to log in and
 * test the role‑based access control without needing to create users
 * manually through the UI.  Passwords are hashed using the configured
 * PasswordEncoder to ensure they work with Spring Security.
 */
@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // create admin user if not present
            userRepository.findByEmail("admin@example.com").orElseGet(() -> {
                User admin = User.builder()
                        .fullname("Admin")
                        .email("admin@example.com")
                        .password(passwordEncoder.encode("admin123"))
                        .role(Role.ADMIN)
                        .build();
                return userRepository.save(admin);
            });
            // create standard user if not present
            userRepository.findByEmail("user@example.com").orElseGet(() -> {
                User user = User.builder()
                        .fullname("User")
                        .email("user@example.com")
                        .password(passwordEncoder.encode("user123"))
                        .role(Role.USER)
                        .build();
                return userRepository.save(user);
            });
        };
    }
}