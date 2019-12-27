package br.com.kibutzzz.leonardooverflow.infrastructure.persistence;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepositoty extends JpaRepository<Vote, Long> {


}
