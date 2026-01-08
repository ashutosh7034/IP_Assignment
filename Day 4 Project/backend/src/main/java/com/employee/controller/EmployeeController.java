package com.employee.controller;

import com.employee.dto.CreatedEmployeeResponse;
import com.employee.dto.EmployeeDTO;
import com.employee.entity.User;
import com.employee.service.AuthService;
import com.employee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {
    
    private final EmployeeService employeeService;
    private final AuthService authService;

    public EmployeeController(EmployeeService employeeService, AuthService authService) {
        this.employeeService = employeeService;
        this.authService = authService;
    }
    
    @GetMapping
    public ResponseEntity<?> getAllEmployees(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No token provided");
            }
            
            String token = authHeader.substring(7);
            if (!authService.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }
            
            String username = authService.getUsernameFromToken(token);
            User user = authService.getUserByUsername(username);
            
            // Check if admin
            if (user.getRoles().contains("ROLE_ADMIN")) {
                List<EmployeeDTO> employees = employeeService.getAllEmployees();
                return ResponseEntity.ok(employees);
            } else {
                // Regular user - return only their own profile
                if (user.getEmployeeId() != null) {
                    EmployeeDTO employee = employeeService.getEmployeeById(user.getEmployeeId());
                    return ResponseEntity.ok(List.of(employee));
                } else {
                    return ResponseEntity.ok(List.of());
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
    
    @GetMapping("/me")
    public ResponseEntity<?> getMyProfile(@RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.substring(7);
            String username = authService.getUsernameFromToken(token);
            User user = authService.getUserByUsername(username);
            
            if (user.getEmployeeId() != null) {
                EmployeeDTO employee = employeeService.getEmployeeById(user.getEmployeeId());
                return ResponseEntity.ok(employee);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No employee profile linked");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
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
    public ResponseEntity<CreatedEmployeeResponse> createEmployee(@RequestBody EmployeeDTO dto) {
        try {
            CreatedEmployeeResponse created = employeeService.createEmployee(dto);
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
