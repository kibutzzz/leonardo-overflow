package br.com.kibutzzz.leonardooverflow.presentation;

import br.com.kibutzzz.leonardooverflow.VoteRequestStubber;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Tag("full-context-test")
class VoteControllerIntegrationTest {

  private static final String CAST_VOTE_URL = "/vote";

  //  @MockBean
  //  private VoteService voteService;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  private final User user = User.builder().id(1L).build();

  @Test
  public void test_castVote_withUnauthenticatedRequest_shouldReturnForbidden() {

    assertDoesNotThrow(() -> {
      mockMvc
        .perform(post(CAST_VOTE_URL))

        .andDo(print())
        .andExpect(status().isForbidden());
    });
  }


  @Test
  @Disabled
  //FIXME roles are being ignored
  public void test_castVote_withValidRequest_shouldReturnOk() {

    assertDoesNotThrow(() -> {
      mockMvc
        .perform(
          post(CAST_VOTE_URL)
            .with(user(user))
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(VoteRequestStubber.generateValidRequest())))
        .andDo(print())
        .andExpect(status().isOk());
    });

  }

}
