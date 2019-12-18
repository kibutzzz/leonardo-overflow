package br.com.kibutzzz.leonardooverflow.infrastructure.persistence;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {


}
