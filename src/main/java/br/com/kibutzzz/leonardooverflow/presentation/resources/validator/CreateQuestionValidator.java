package br.com.kibutzzz.leonardooverflow.presentation.resources.validator;

import br.com.kibutzzz.leonardooverflow.application.TagService;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.CreateQuestionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateQuestionValidator implements Validator {

  private final TagService tagService;

  @Override
  public boolean supports(final Class<?> clazz) {
    return CreateQuestionRequest.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(final Object target, final Errors errors) {

    if (errors.hasErrors()) {
      return;
    }

    final CreateQuestionRequest request = (CreateQuestionRequest) target;

    final List<Long> ids = request.getTagsIds();
    for (int i = 0; i < ids.size(); i++) {

      if (!tagService.tagExists(ids.get(i))) {
        errors.rejectValue("tagsIds[" + i + "]", "validation.question.create.tagDoesNotExist");
      }
    }

  }
}
