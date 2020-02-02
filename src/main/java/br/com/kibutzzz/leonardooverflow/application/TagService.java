package br.com.kibutzzz.leonardooverflow.application;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.TagRepository;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Tag;
import br.com.kibutzzz.leonardooverflow.presentation.resources.mapper.TagMapper;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.CreateTagRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

  private final TagRepository tagRepository;

  private final TagMapper tagMapper;

  public Tag createTag(@Valid final CreateTagRequest tagRequest) {

    return tagRepository.save(tagMapper.fromRequest(tagRequest));
  }

  public List<Tag> findAllTagsByIds() {

    return tagRepository.findAll();
  }

  public List<Tag> findAllTagsByIds(final List<Long> tagsIds) {
    return tagRepository.findAllById(tagsIds);
  }

  public boolean tagExists(final String name) {

    return tagRepository.existsByName(name);
  }

  public boolean tagExists(final Long id) {
    return tagRepository.existsById(id);
  }

  public List<Tag> findTagsByName(final String name) {
    return tagRepository.findByNameContainingIgnoreCase(name);
  }

}
