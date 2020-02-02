package br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface Voteable {

  @NotNull List<Vote> getVotes();
}
