package br.com.kibutzzz.leonardooverflow.presentation.resources.validator;

import br.com.kibutzzz.leonardooverflow.application.TagService;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.CreateTagRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class CreateTagValidator implements Validator {

    private final TagService tagService;

    @Override
    public boolean supports(Class<?> clazz) {
        return CreateTagRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if(errors.hasErrors()) {
            return;
        }

        CreateTagRequest request = (CreateTagRequest) target;

        if(tagService.tagExists(request.getName())){
            errors.rejectValue("name", "validation.tag.create.alreadyExists");
        }

    }
}
