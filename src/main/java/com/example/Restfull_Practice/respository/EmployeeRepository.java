package com.example.Restfull_Practice.respository;

import com.example.Restfull_Practice.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Long> {
    // No extra methods needed — JPA provides findAll(), findById(), save(), delete()
}