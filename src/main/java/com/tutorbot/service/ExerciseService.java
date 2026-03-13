package com.tutorbot.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tutorbot.model.Exercise;
import com.tutorbot.model.Feedback;
import com.tutorbot.repository.ExerciseRepository;
 
@Service
public class ExerciseService {
 
    private final ExerciseRepository exerciseRepository;
 
    // Hardcoded correct answers per exercise id
    private final Map<Integer, String> correctAnswers = new HashMap<>();
 
    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
 
        correctAnswers.put(101, "@RestController");
        correctAnswers.put(102, "@SpringBootApplication");
        correctAnswers.put(103, "@Autowired");
        correctAnswers.put(104, "@Repository");
    }
 
    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }
 
    public List<Exercise> getExercisesByDifficulty(String difficulty) {
        return exerciseRepository.findByDifficulty(difficulty);
    }
 
    public Feedback submitAnswer(int studentId, int exerciseId, String answer) {
        Optional<Exercise> exerciseOpt = exerciseRepository.findById(exerciseId);
 
        if (exerciseOpt.isEmpty()) {
            return new Feedback(
                    studentId,
                    exerciseId,
                    answer,
                    0,
                    "Exercise not found.",
                    false
            );
        }
 
        String correct = correctAnswers.get(exerciseId);
        boolean isCorrect = correct != null && correct.equalsIgnoreCase(answer.trim());
 
        if (isCorrect) {
            return new Feedback(
                    studentId,
                    exerciseId,
                    answer,
                    100,
                    "Excellent! That is correct.",
                    true
            );
        } else {
            return new Feedback(
                    studentId,
                    exerciseId,
                    answer,
                    40,
                    "Not quite! Keep going, you're learning. The correct answer was: " + correct,
                    false
            );
        }
    }
}
 