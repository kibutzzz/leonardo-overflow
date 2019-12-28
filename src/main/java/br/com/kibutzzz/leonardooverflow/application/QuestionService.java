package br.com.kibutzzz.leonardooverflow.application;

import br.com.kibutzzz.leonardooverflow.infrastructure.ApiException;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.QuestionRepository;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Question;
import br.com.kibutzzz.leonardooverflow.presentation.resources.mapper.QuestionMapper;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.CreateQuestionRequest;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.UpdateQuestionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> listQuestions() {
        return questionRepository.findAll();
    }

    @Transactional
    public Question createQuestion(CreateQuestionRequest questionRequest) {
        Question question = QuestionMapper.INSTANCE.fromRequest(questionRequest);
        return questionRepository.save(question);
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND, "question not found"));
    }

    public List<Question> searchQuestionsByExpression(String expression) {
        return questionRepository.findByTitleContainingIgnoreCase(expression);
    }

    public Question updateQuestion(UpdateQuestionRequest questionRequest, Long questionId) {
        Question question = questionRepository.findById(questionId).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND, "Question not found"));

        question.setTitle(questionRequest.getTitle());
        question.setDescription(questionRequest.getDescription());

        return questionRepository.save(question);
    }
}
