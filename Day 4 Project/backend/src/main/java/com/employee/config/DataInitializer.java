package com.employee.config;

import com.employee.entity.EmployeeEntity;
import com.employee.entity.User;
import com.employee.repository.EmployeeRepository;
import com.employee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {
    
    private final PasswordEncoder passwordEncoder;
    
    @Bean
    public CommandLineRunner initializeData(UserRepository userRepository, EmployeeRepository employeeRepository) {
        return args -> {
            // Initialize Users
            if (userRepository.count() == 0) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setEmail("admin@example.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setFullName("Administrator");
                admin.setIsActive(true);
                Set<String> adminRoles = new HashSet<>();
                adminRoles.add("ROLE_ADMIN");
                admin.setRoles(adminRoles);
                
                User user = new User();
                user.setUsername("user");
                user.setEmail("user@example.com");
                user.setPassword(passwordEncoder.encode("user123"));
                user.setFullName("Regular User");
                user.setIsActive(true);
                Set<String> userRoles = new HashSet<>();
                userRoles.add("ROLE_USER");
                user.setRoles(userRoles);
                
                userRepository.save(admin);
                userRepository.save(user);
            }
            
            // Initialize Employees
            if (employeeRepository.count() == 0) {
                employeeRepository.save(new EmployeeEntity(null, "John Doe", "IT", 50000.0, 
                        "john@example.com", "9876543210", "Senior Developer", "ACTIVE", null, null));
                
                employeeRepository.save(new EmployeeEntity(null, "Jane Smith", "HR", 55000.0, 
                        "jane@example.com", "9876543211", "HR Manager", "ACTIVE", null, null));
                
                employeeRepository.save(new EmployeeEntity(null, "Mike Johnson", "Finance", 60000.0, 
                        "mike@example.com", "9876543212", "Finance Head", "ACTIVE", null, null));
                
                employeeRepository.save(new EmployeeEntity(null, "Alice Brown", "IT", 52000.0, 
                        "alice@example.com", "9876543213", "Developer", "ACTIVE", null, null));
                
                employeeRepository.save(new EmployeeEntity(null, "Bob Wilson", "Sales", 48000.0, 
                        "bob@example.com", "9876543214", "Sales Executive", "ACTIVE", null, null));
            }
        };
    }
}
