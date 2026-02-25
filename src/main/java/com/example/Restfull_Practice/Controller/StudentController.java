package com.example.Restfull_Practice.Controller;

import com.example.Restfull_Practice.entity.Student;
import com.example.Restfull_Practice.exception.StudentNotFoundException;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")   // 👈 adds /api to all endpoints
public class StudentController {

    private final List<Student> students = List.of(
            new Student(1L, "Alice"),
            new Student(2L, "Bob"),
            new Student(3L, "Charlie"),
            new Student(4L, "Misbah Subhani")
    );

    @GetMapping("/students" )
    public List<Student> getAllStudents() {
        return students;
    }

    @GetMapping("/students/{count}")
    public Student getStudentbyId(@PathVariable int count) {

       return students.stream()
                .filter(s -> s.getId() == count)   // match count to student id
                .findFirst()
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student with id " + count + " not found!"
                ));
    }
}