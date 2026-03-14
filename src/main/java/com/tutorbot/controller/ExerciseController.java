package com.tutorbot.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tutorbot.model.Exercise;
import com.tutorbot.model.Feedback;
import com.tutorbot.service.ExerciseService;
 
@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {
 
    private final ExerciseService exerciseService;
 
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }
 
    // GET /api/exercises
    // GET /api/exercises?difficulty=easy
    @GetMapping
    public ResponseEntity<List<Exercise>> getExercises(
            @RequestParam(required = false) String difficulty) {
 
        if (difficulty != null && !difficulty.isBlank()) {
            return ResponseEntity.ok(exerciseService.getExercisesByDifficulty(difficulty));
        }
        return ResponseEntity.ok(exerciseService.getAllExercises());
    }
 
    // POST /api/exercises/submit
    // Body: { "studentId": 1, "exerciseId": 101, "answer": "@RestController" }
    @PostMapping("/submit")
    public ResponseEntity<?> submitAnswer(@RequestBody Map<String, Object> body) {
        int studentId  = (int) body.get("studentId");
        int exerciseId = (int) body.get("exerciseId");
        String answer  = (String) body.get("answer");
 
        Feedback feedback = exerciseService.submitAnswer(studentId, exerciseId, answer);
        
        // If exercise not found, return 404 with custom message
        if (!feedback.isCorrect() && feedback.getMessage().equals("Exercise not found.")) {
            Map<String, String> error = Map.of(
                "status", "404",
                "message", "Exercise with ID " + exerciseId + " not found"
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        
        return ResponseEntity.ok(feedback);
    }
}