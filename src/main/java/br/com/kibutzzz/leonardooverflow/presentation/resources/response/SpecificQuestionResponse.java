package br.com.kibutzzz.leonardooverflow.presentation.resources.response;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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

    private List<SimplifiedTagResponse> tags;

    private Long voteCount;

    private LocalDateTime creationDate;

    private LocalDateTime updateDate;

}
