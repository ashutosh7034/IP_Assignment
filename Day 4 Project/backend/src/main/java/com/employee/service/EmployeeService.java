package com.employee.service;

import com.employee.dto.EmployeeDTO;
import com.employee.entity.EmployeeEntity;
import com.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    
    private final EmployeeRepository employeeRepository;
    
    @Transactional
    public EmployeeDTO createEmployee(EmployeeDTO dto) {
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
        return mapToDTO(saved);
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
}
