package com.tutorbot.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.tutorbot.model.Student;
import com.tutorbot.model.Feedback;
 
@Repository
public class StudentRepository {
 
    private final List<Student> students = new ArrayList<>();
    private final List<Feedback> allFeedback = new ArrayList<>();
 
    public StudentRepository() {
        students.add(new Student(1, "Ana Torres",    "ana@university.com",     "beginner"));
        students.add(new Student(2, "Luis Mendoza",  "luis@university.com",    "intermediate"));
        students.add(new Student(3, "Sofia Ramirez", "sofia@university.com",   "advanced"));
    }
 
    public List<Student> findAll() {
        return students;
    }
 
    public Optional<Student> findById(int id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
    }
 
    public Student save(Student student) {
        // Auto-assign id: max existing id + 1
        int nextId = students.stream()
                .mapToInt(Student::getId)
                .max()
                .orElse(0) + 1;
        student.setId(nextId);
        students.add(student);
        return student;
    }
 
    public void saveFeedback(Feedback feedback) {
        allFeedback.add(feedback);
    }
 
    public List<Feedback> getFeedbackByStudentId(int studentId) {
        return allFeedback.stream()
                .filter(f -> f.getStudentId() == studentId)
                .toList();
    }
}
 