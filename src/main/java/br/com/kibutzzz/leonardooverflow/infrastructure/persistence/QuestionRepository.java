package br.com.kibutzzz.leonardooverflow.infrastructure.persistence;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByTitleContainingIgnoreCase(String title);

    List<Question> findByUserId(Long id);
}
