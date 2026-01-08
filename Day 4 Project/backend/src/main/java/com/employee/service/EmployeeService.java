package com.employee.service;

import com.employee.dto.CreatedEmployeeResponse;
import com.employee.dto.EmployeeDTO;
import com.employee.entity.EmployeeEntity;
import com.employee.entity.User;
import com.employee.repository.EmployeeRepository;
import com.employee.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepository employeeRepository,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Transactional
    public CreatedEmployeeResponse createEmployee(EmployeeDTO dto) {
        EmployeeEntity entity = new EmployeeEntity();
        entity.setName(dto.getName());
        entity.setDepartment(dto.getDepartment());
        entity.setSalary(dto.getSalary());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setPosition(dto.getPosition());
        entity.setStatus("ACTIVE");
        entity.setCreatedAt(LocalDateTime.now());
        
        EmployeeEntity saved = employeeRepository.save(entity);
        UserCreation creds = createDefaultUserForEmployee(saved);
        return new CreatedEmployeeResponse(mapToDTO(saved), creds.username(), creds.password());
    }
    
    @Transactional
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        EmployeeEntity entity = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        
        entity.setName(dto.getName());
        entity.setDepartment(dto.getDepartment());
        entity.setSalary(dto.getSalary());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setPosition(dto.getPosition());
        entity.setUpdatedAt(LocalDateTime.now());
        
        EmployeeEntity updated = employeeRepository.save(entity);
        return mapToDTO(updated);
    }
    
    public EmployeeDTO getEmployeeById(Long id) {
        EmployeeEntity entity = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return mapToDTO(entity);
    }
    
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    public List<EmployeeDTO> getEmployeesByDepartment(String department) {
        return employeeRepository.findByDepartment(department).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    public List<EmployeeDTO> searchEmployees(String searchTerm) {
        return employeeRepository.searchEmployees(searchTerm).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }
    
    public long getTotalEmployees() {
        return employeeRepository.count();
    }
    
    public Double getAverageSalary() {
        List<EmployeeEntity> employees = employeeRepository.findAll();
        if (employees.isEmpty()) return 0.0;
        return employees.stream()
                .mapToDouble(EmployeeEntity::getSalary)
                .average()
                .orElse(0.0);
    }
    
    public Double getTotalSalaryByDepartment(String department) {
        return employeeRepository.findByDepartment(department).stream()
                .mapToDouble(EmployeeEntity::getSalary)
                .sum();
    }
    
    private EmployeeDTO mapToDTO(EmployeeEntity entity) {
        return new EmployeeDTO(
                entity.getId(),
                entity.getName(),
                entity.getDepartment(),
                entity.getSalary(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getPosition(),
                entity.getStatus()
        );
    }

    private UserCreation createDefaultUserForEmployee(EmployeeEntity employee) {
        if (employee == null || employee.getName() == null) {
            return new UserCreation(null, null);
        }

        String[] parts = employee.getName().trim().split("\\s+");
        if (parts.length == 0) {
            return new UserCreation(null, null);
        }

        String baseUsername = parts[0].trim();
        if (baseUsername.isEmpty()) {
            return new UserCreation(null, null);
        }

        String username = baseUsername;
        int suffix = 1;
        while (userRepository.existsByUsername(username)) {
            username = baseUsername + suffix;
            suffix++;
        }

        String email = employee.getEmail();
        if (email == null || email.isBlank() || userRepository.existsByEmail(email)) {
            email = username.toLowerCase() + "@auto.local";
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(baseUsername + "123"));
        user.setFullName(employee.getName());
        user.setIsActive(true);
        user.setCreatedAt(LocalDateTime.now());
        user.setEmployeeId(employee.getId());

        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");
        user.setRoles(roles);

        userRepository.save(user);
        return new UserCreation(username, baseUsername + "123");
    }

    private record UserCreation(String username, String password) {}
}
