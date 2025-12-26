package com.employee.controller;

import com.employee.dto.EmployeeDTO;
import com.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EmployeeController {
    
    private final EmployeeService employeeService;
    
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        try {
            EmployeeDTO employee = employeeService.getEmployeeById(id);
            return ResponseEntity.ok(employee);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO dto) {
        try {
            EmployeeDTO created = employeeService.createEmployee(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO dto) {
        try {
            EmployeeDTO updated = employeeService.updateEmployee(id, dto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Employee deleted successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @GetMapping("/department/{department}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByDepartment(@PathVariable String department) {
        List<EmployeeDTO> employees = employeeService.getEmployeesByDepartment(department);
        return ResponseEntity.ok(employees);
    }
    
    @GetMapping("/search/{searchTerm}")
    public ResponseEntity<List<EmployeeDTO>> searchEmployees(@PathVariable String searchTerm) {
        List<EmployeeDTO> employees = employeeService.searchEmployees(searchTerm);
        return ResponseEntity.ok(employees);
    }
    
    @GetMapping("/stats/count")
    public ResponseEntity<Map<String, Long>> getTotalEmployees() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("total", employeeService.getTotalEmployees());
        return ResponseEntity.ok(stats);
    }
    
    @GetMapping("/stats/salary")
    public ResponseEntity<Map<String, Double>> getAverageSalary() {
        Map<String, Double> stats = new HashMap<>();
        stats.put("average", employeeService.getAverageSalary());
        return ResponseEntity.ok(stats);
    }
    
    @GetMapping("/stats/department-salary/{department}")
    public ResponseEntity<Map<String, Double>> getDepartmentSalaryTotal(@PathVariable String department) {
        Map<String, Double> stats = new HashMap<>();
        stats.put("total", employeeService.getTotalSalaryByDepartment(department));
        return ResponseEntity.ok(stats);
    }
}
