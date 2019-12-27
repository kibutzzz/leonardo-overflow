package br.com.kibutzzz.leonardooverflow.presentation.resources.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerResponse {

    private Long id;

    private String text;

    private Integer voteCount;

    private List<CommentResponse> comments;

}
