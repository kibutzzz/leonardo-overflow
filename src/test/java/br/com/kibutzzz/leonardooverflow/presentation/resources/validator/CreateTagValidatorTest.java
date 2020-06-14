package br.com.kibutzzz.leonardooverflow.presentation.resources.validator;

import br.com.kibutzzz.leonardooverflow.application.TagService;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.CreateTagRequest;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@NoArgsConstructor
class CreateTagValidatorTest {

  @Mock
  private TagService tagService;

  @InjectMocks
  private CreateTagValidator validator;

  private Errors errors;

  private final CreateTagRequest target = new CreateTagRequest("tag", "description");

  @Test
  public void test_supports_withValidObject() {
    assertTrue(validator.supports(CreateTagRequest.class));
  }

  @Test
  public void test_supports_withInvalidObject_shouldReturnFalse() {
    assertFalse(validator.supports(Object.class));
  }

  @Test
  public void test_validate_withInvalidValues_shouldNotAddNewErrors() {

    errors = new BeanPropertyBindingResult(target, "invalidRequest");

    errors.rejectValue("name", "invalidNameError");
    validator.validate(target, errors);

    assertEquals(1, errors.getErrorCount());
  }

  @Test
  public void test_validate_withExistingTagName_shouldAddAlreadyExistsError() {
    errors = new BeanPropertyBindingResult(target, "invalidRequest");

    when(tagService.tagExists(anyString())).thenReturn(true);

    validator.validate(target, errors);

    assertEquals("validation.tag.create.alreadyExists", errors.getFieldError().getCode());
  }

  @Test
  public void test_validate_withValidTagName_shouldNotAddAnyErrors() {

    errors = new BeanPropertyBindingResult(target, "validTag");

    when(tagService.tagExists(anyString())).thenReturn(false);

    validator.validate(target, errors);

    assertFalse(errors.hasErrors());
  }

}
