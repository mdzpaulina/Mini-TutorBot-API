package com.tutorbot.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.tutorbot.model.Exercise;
 
@Repository
public class ExerciseRepository {
 
    private final List<Exercise> exercises = new ArrayList<>();
 
    public ExerciseRepository() {
        exercises.add(new Exercise(101, "Spring Boot",  "What annotation marks a REST controller?",             "easy"));
        exercises.add(new Exercise(102, "Spring Boot",  "Which annotation enables component scanning?",          "easy"));
        exercises.add(new Exercise(103, "Spring Core",  "What annotation is used for constructor injection?",    "medium"));
        exercises.add(new Exercise(104, "Spring Data",  "Which annotation marks a repository bean?",             "medium"));
    }
 
    public List<Exercise> findAll() {
        return exercises;
    }
 
    public Optional<Exercise> findById(int id) {
        return exercises.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
    }
 
    public List<Exercise> findByDifficulty(String difficulty) {
        return exercises.stream()
                .filter(e -> e.getDifficulty().equalsIgnoreCase(difficulty))
                .collect(Collectors.toList());
    }
}
