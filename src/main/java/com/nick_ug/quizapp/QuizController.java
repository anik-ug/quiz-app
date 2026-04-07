package com.nick_ug.quizapp;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@CrossOrigin(origins = "*")
@Tag(name =  "Quiz API",description = "Operations related to quiz management")
@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionRepository questionRepository;

//    create QUIZ
    @Operation(summary = "Create a new quiz")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Quiz>> createQuiz(@Valid @RequestBody Quiz quiz) {
        Quiz savedQuiz = quizRepository.save(quiz);

        ApiResponse<Quiz> response = new ApiResponse<>(
                200,
                "Quiz created successfully",
                savedQuiz
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //old
//    public Quiz createQuiz(@Valid @RequestBody Quiz quiz){
//        return quizRepository.save(quiz);
//    }

    //GET ALL QUIZ
    @GetMapping("/all")
    public Page<QuizDTO> getAllQuizzes(Pageable pageable) {
        return quizService.getAllQuizzes(pageable);
    }

    //old
//    public List<Quiz> getAllQuizzes() {
//        return quizRepository.findAll();
//    }

    //GET by ID
    @Operation(summary = "Get quiz by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<QuizDTO>> getQuiz(@Parameter(description = "ID of the quiz") @PathVariable long id) {
        QuizDTO quiz = quizService.getQuiz(id);

        ApiResponse<QuizDTO> response = new ApiResponse<>(
                200,
                "Quiz fetched successfully",
                quiz
        );

        return ResponseEntity.ok(response);
    }
//    public QuizDTO getQuiz(@PathVariable Long id) {
//        return quizService.getQuiz(id);
//    }

    @PostMapping("/{quizId}/add-question")
    public String addQuestion(@PathVariable Long quizId, @RequestBody Question question){
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new QuizNotFoundException("Quiz not found"));

        question.setQuiz(quiz);
        questionRepository.save(question);

        return "Question added";
    }


    @PostMapping("/{id}/submit")
    public String submitQuiz(@PathVariable Long id,
                             @RequestBody List<String> answers) {
        return quizService.submitQuiz(id, answers);
    }

    @GetMapping("/search")
    public List<QuizDTO> searchQuiz(@RequestParam String title){
        return quizService.searchQuiz(title);
    }


}
