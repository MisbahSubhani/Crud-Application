package com.example.Restfull_Practice.Controller;

import com.example.Restfull_Practice.entity.Employees;
import com.example.Restfull_Practice.respository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api") // Base URL for all endpoints
public class EmployeeController {

    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    // GET all employees
    @GetMapping("/employees")
    public List<Employees> getAllEmployees() {

        return repository.findAll();
    }

    // GET employee by ID
    @GetMapping("/employees/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        return repository.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity
                        .status(404)
                        .body("Employee with id" + id + "not found"));
    }

    // POST - add a new employee
    @PostMapping("/employees")
    public ResponseEntity<?> addEmployee(@RequestBody Employees employee) {
        if (employee.getFirstName() == null || employee.getLastName() == null || employee.getEmail() == null) {
            return ResponseEntity
                    .badRequest()
                    .body("All fields (firstName, lastName, email) are required!");
        }

        try {
            Employees savedEmployee = repository.save(employee);
            return ResponseEntity.ok(savedEmployee);
        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body("Cannot add employee: " + e.getMessage());
        }
    }

    // PUT - update an existing employee
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employees> updateEmployee(@PathVariable Long id, @RequestBody Employees employeeDetails) {
        return repository.findById(id).map(employee -> {
            employee.setFirstName(employeeDetails.getFirstName());
            employee.setLastName(employeeDetails.getLastName());
            employee.setEmail(employeeDetails.getEmail());
            Employees updatedEmployee = repository.save(employee);
            return ResponseEntity.ok(updatedEmployee);
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE - remove an employee
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        return repository.findById(id).map(employee -> {
            repository.delete(employee);
            return ResponseEntity.ok("Employee deleted successfully!");
        }).orElseGet(()->ResponseEntity
    .status(404)
.body("Employee with id " + id + " not found!"));
    }
}