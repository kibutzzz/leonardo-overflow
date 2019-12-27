package br.com.kibutzzz.leonardooverflow.presentation.resources.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecificQuestionResponse {

    private Long id;

    private String title;

    private String description;

    private List<AnswerResponse> answers;

    private List<CommentResponse> comments;

    private Long voteCount;

}
