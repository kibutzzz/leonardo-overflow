package br.com.kibutzzz.leonardooverflow.presentation;

import br.com.kibutzzz.leonardooverflow.application.TagService;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Tag;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.CreateTagRequest;
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

    private final CreateTagValidator createTagValidator;

    @InitBinder("createTagRequest")
    public void setCreateTagValidator(WebDataBinder binder) {
        binder.addValidators(createTagValidator);
    }

    @PostMapping
    public Tag createTag(@Valid @RequestBody CreateTagRequest request) {

        return tagService.createTag(request);
    }

    @GetMapping
    public List<Tag> listAllTags() {
        return tagService.findAllTagsByIds();
    }

}
