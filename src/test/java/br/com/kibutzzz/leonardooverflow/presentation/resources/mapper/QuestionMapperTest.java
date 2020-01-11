package br.com.kibutzzz.leonardooverflow.presentation.resources.mapper;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Question;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.CreateQuestionRequest;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@NoArgsConstructor
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = {QuestionMapperImpl.class}
)
class QuestionMapperTest {

    private static final CreateQuestionRequest NULL_CREATE_QUESTION_REQUEST = null;
    private static final Question NULL_QUESTION = null;

    @Autowired
    private QuestionMapper questionMapper;

    @Test
    public void test_fromRequest_withNullArgument_shouldReturnNull() {

        assertNull(questionMapper.fromRequest(NULL_CREATE_QUESTION_REQUEST));
    }

    @Test
    public void test_toResponse_withNullArgument_shouldReturnNull() {

        assertNull(questionMapper.toResponse(NULL_QUESTION));
    }

    @Test
    public void test_toSpecificQuestionResponse_withNullArgument_shouldReturnNull() {

        assertNull(questionMapper.toSpecificQuestionResponse(NULL_QUESTION));
    }

}