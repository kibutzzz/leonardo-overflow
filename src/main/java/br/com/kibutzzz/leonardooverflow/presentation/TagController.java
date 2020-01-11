package br.com.kibutzzz.leonardooverflow.presentation;

import br.com.kibutzzz.leonardooverflow.application.TagService;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Tag;
import br.com.kibutzzz.leonardooverflow.presentation.resources.mapper.TagMapper;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.CreateTagRequest;
import br.com.kibutzzz.leonardooverflow.presentation.resources.response.TagResponse;
import br.com.kibutzzz.leonardooverflow.presentation.resources.validator.CreateTagValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tag")
public class TagController {

    private final TagService tagService;

    private final TagMapper tagMapper;

    private final CreateTagValidator createTagValidator;

    @InitBinder("createTagRequest")
    public void setCreateTagValidator(WebDataBinder binder) {
        binder.addValidators(createTagValidator);
    }

    @PostMapping
    public TagResponse createTag(@Valid @RequestBody CreateTagRequest request) {

        return tagMapper.toResponse(tagService.createTag(request));
    }

    @GetMapping
    public List<TagResponse> listAllTags() {
        return tagMapper.toResponse(tagService.findAllTagsByIds());
    }

}
