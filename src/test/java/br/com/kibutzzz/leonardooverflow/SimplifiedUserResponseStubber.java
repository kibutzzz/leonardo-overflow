package br.com.kibutzzz.leonardooverflow;

import br.com.kibutzzz.leonardooverflow.presentation.resources.response.SimplifiedUserResponse;

public class SimplifiedUserResponseStubber {
    public static SimplifiedUserResponse generateCompleteResponse() {

        return SimplifiedUserResponse.builder()
                .id(1L)
                .username("USERNAME")
                .build();
    }
}
