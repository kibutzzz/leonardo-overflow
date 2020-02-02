package br.com.kibutzzz.leonardooverflow.infrastructure.persistence;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

  Boolean existsByName(String name);

  List<Tag> findByNameContainingIgnoreCase(String name);

}
