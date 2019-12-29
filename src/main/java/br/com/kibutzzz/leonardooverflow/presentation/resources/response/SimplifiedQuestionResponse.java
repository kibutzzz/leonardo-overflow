package br.com.kibutzzz.leonardooverflow.presentation.resources.response;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Tag;
import lombok.Data;

import java.util.List;

@Data
public class SimplifiedQuestionResponse {

    private Long id;

    private String title;

    private String description;

    private Long voteCount;

    private SimplifiedUserResponse user;

    private List<Tag> tags;
}
