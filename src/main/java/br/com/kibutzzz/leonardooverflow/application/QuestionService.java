package br.com.kibutzzz.leonardooverflow.application;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.QuestionRepository;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Question;
import br.com.kibutzzz.leonardooverflow.presentation.resources.mapper.QuestionMapper;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.CreateQuestionRequest;
import lombok.RequiredArgsConstructor;
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

}
