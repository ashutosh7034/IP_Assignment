package com.employee.config;

import com.employee.entity.EmployeeEntity;
import com.employee.entity.User;
import com.employee.repository.EmployeeRepository;
import com.employee.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class DataInitializer {
    
    @Bean
    public CommandLineRunner initializeData(UserRepository userRepository, EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
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
                admin.setEmployeeId(null); // Admin has no employee profile
                
                userRepository.save(admin);
                System.out.println("✅ Admin user created successfully");
            }
            
            // Initialize Employees first
            if (employeeRepository.count() == 0) {
                EmployeeEntity emp1 = employeeRepository.save(new EmployeeEntity(null, "John Doe", "Engineering", 75000.0, 
                        "john@company.com", "555-0101", "Senior Developer", "ACTIVE", LocalDateTime.now(), LocalDateTime.now()));
                
                EmployeeEntity emp2 = employeeRepository.save(new EmployeeEntity(null, "Jane Smith", "HR", 65000.0, 
                        "jane@company.com", "555-0102", "HR Manager", "ACTIVE", LocalDateTime.now(), LocalDateTime.now()));
                
                EmployeeEntity emp3 = employeeRepository.save(new EmployeeEntity(null, "Mike Johnson", "Finance", 80000.0, 
                        "mike@company.com", "555-0103", "Finance Director", "ACTIVE", LocalDateTime.now(), LocalDateTime.now()));
                
                EmployeeEntity emp4 = employeeRepository.save(new EmployeeEntity(null, "Alice Brown", "Engineering", 70000.0, 
                        "alice@company.com", "555-0104", "Software Engineer", "ACTIVE", LocalDateTime.now(), LocalDateTime.now()));
                
                EmployeeEntity emp5 = employeeRepository.save(new EmployeeEntity(null, "Bob Wilson", "Sales", 60000.0, 
                        "bob@company.com", "555-0105", "Sales Manager", "ACTIVE", LocalDateTime.now(), LocalDateTime.now()));
                
                System.out.println("✅ Employees initialized successfully");
                
                // Create user accounts linked to employees
                User user1 = new User();
                user1.setUsername("john");
                user1.setEmail("john@company.com");
                user1.setPassword(passwordEncoder.encode("john123"));
                user1.setFullName("John Doe");
                user1.setIsActive(true);
                Set<String> userRoles = new HashSet<>();
                userRoles.add("ROLE_USER");
                user1.setRoles(userRoles);
                user1.setEmployeeId(emp1.getId());
                
                User user2 = new User();
                user2.setUsername("jane");
                user2.setEmail("jane@company.com");
                user2.setPassword(passwordEncoder.encode("jane123"));
                user2.setFullName("Jane Smith");
                user2.setIsActive(true);
                user2.setRoles(new HashSet<>(Set.of("ROLE_USER")));
                user2.setEmployeeId(emp2.getId());
                
                User user3 = new User();
                user3.setUsername("mike");
                user3.setEmail("mike@company.com");
                user3.setPassword(passwordEncoder.encode("mike123"));
                user3.setFullName("Mike Johnson");
                user3.setIsActive(true);
                user3.setRoles(new HashSet<>(Set.of("ROLE_USER")));
                user3.setEmployeeId(emp3.getId());
                
                userRepository.save(user1);
                userRepository.save(user2);
                userRepository.save(user3);
                
                System.out.println("✅ Employee users created successfully");
            }
        };
    }
}
