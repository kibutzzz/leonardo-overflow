package br.com.kibutzzz.leonardooverflow;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.VoteType;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.VoteRequest;

public class VoteRequestStubber {


    public static VoteRequest generateValidRequest() {
        return VoteRequest.builder()
                .id(1L)
                .type(VoteType.UP)
                .build();
    }
}

