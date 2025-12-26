package com.employee.repository;

import com.employee.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    Optional<EmployeeEntity> findByEmail(String email);
    List<EmployeeEntity> findByDepartment(String department);
    List<EmployeeEntity> findByStatus(String status);
    
    @Query("SELECT e FROM EmployeeEntity e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(e.email) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<EmployeeEntity> searchEmployees(String searchTerm);
}
