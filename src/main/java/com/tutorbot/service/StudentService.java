package com.tutorbot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tutorbot.model.Student;
import com.tutorbot.repository.StudentRepository;
 
@Service
public class StudentService {
 
    private final StudentRepository studentRepository;
 
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
 
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
 
    public Student getStudentById(int id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.orElse(null);
    }
 
    public Student registerStudent(Student student) {
        return studentRepository.save(student);
    }
}
 