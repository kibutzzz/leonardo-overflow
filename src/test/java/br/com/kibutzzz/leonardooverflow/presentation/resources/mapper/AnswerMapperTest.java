package br.com.kibutzzz.leonardooverflow.presentation.resources.mapper;

import br.com.kibutzzz.leonardooverflow.AnswerResponseStubber;
import br.com.kibutzzz.leonardooverflow.AnswerStubber;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Answer;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.AnswerRequest;
import br.com.kibutzzz.leonardooverflow.presentation.resources.response.AnswerResponse;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@NoArgsConstructor
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = {AnswerMapperImpl.class, VoteMapperImpl.class, CommentMapperImpl.class, UserMapperImpl.class}
)
class AnswerMapperTest {

    private static final Answer NULL_ANSWER = null;

    private static final List<Answer> NULL_ANSWER_LIST = null;

    private static final AnswerRequest NULL_REQUEST = null;

    @Autowired
    private AnswerMapper answerMapper;

    @Test
    public void test_toResponse_withNullAnswer_shouldReturnNull() {
        assertNull(answerMapper.toResponse(NULL_ANSWER));
    }

    @Test
    public void test_toResponse_withNullAnswerList_shouldReturnNull() {
        assertNull(answerMapper.toResponse(NULL_ANSWER_LIST));
    }

    @Test
    public void test_fromRequest_withNullRequest_shouldReturnNull() {
        assertNull(answerMapper.fromRequest(NULL_REQUEST));
    }

    @Test
    public void test_fromRequest_withValidRequest_shouldMapCorrectly() {

        Answer expected = new Answer();
        expected.setDescription("DESCRIPTION");

        AnswerRequest validRequest = AnswerRequest.builder().description("DESCRIPTION").build();

        assertEquals(expected, answerMapper.fromRequest(validRequest));
    }

    @Test
    public void test_toResponse_withCompleteAnswer_shouldMapCorreclty() {

        Answer answer = AnswerStubber.generateCompleteAnswer();
        AnswerResponse expected = AnswerResponseStubber.generateCompleteResponse();

        assertEquals(expected, answerMapper.toResponse(answer));
    }

}