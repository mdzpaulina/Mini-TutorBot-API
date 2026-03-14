package com.tutorbot.controller;
 
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorbot.model.Student;
import com.tutorbot.model.Feedback;
import com.tutorbot.service.StudentService;
 
@RestController
@RequestMapping("/api/students")
public class StudentController {
 
    private final StudentService studentService;
 
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
 
    // GET /api/students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }
 
    // GET /api/students/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            Map<String, String> error = Map.of(
                "status", "404",
                "message", "Student with ID " + id + " not found"
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        return ResponseEntity.ok(student);
    }
 
    // GET /api/students/{id}/feedback
    @GetMapping("/{id}/feedback")
    public ResponseEntity<?> getStudentFeedback(@PathVariable int id) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            Map<String, String> error = Map.of(
                "status", "404",
                "message", "Student with ID " + id + " not found"
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        List<Feedback> feedback = studentService.getStudentFeedback(id);
        return ResponseEntity.ok(feedback);
    }
 
    // POST /api/students
    @PostMapping
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        Student created = studentService.registerStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}