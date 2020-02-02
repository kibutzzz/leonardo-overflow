package br.com.kibutzzz.leonardooverflow.presentation;

import br.com.kibutzzz.leonardooverflow.application.TagService;
import br.com.kibutzzz.leonardooverflow.presentation.resources.mapper.TagMapper;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.CreateTagRequest;
import br.com.kibutzzz.leonardooverflow.presentation.resources.response.SimplifiedTagResponse;
import br.com.kibutzzz.leonardooverflow.presentation.resources.response.TagResponse;
import br.com.kibutzzz.leonardooverflow.presentation.resources.validator.CreateTagValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
  public void setCreateTagValidator(final WebDataBinder binder) {
    binder.addValidators(createTagValidator);
  }

  @PostMapping
  public TagResponse createTag(@Valid @RequestBody final CreateTagRequest request) {

    return tagMapper.toResponse(tagService.createTag(request));
  }

  @GetMapping
  public List<TagResponse> listAllTags() {
    return tagMapper.toResponse(tagService.findAllTagsByIds());
  }

  @GetMapping("/{name}")
  public List<SimplifiedTagResponse> searchByName(@PathVariable final String name) {
    return tagMapper.toSimplifiedResponse(tagService.findTagsByName(name));
  }

}
