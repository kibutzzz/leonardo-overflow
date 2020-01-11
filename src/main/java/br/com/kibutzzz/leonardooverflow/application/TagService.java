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

    public Tag createTag(@Valid CreateTagRequest tagRequest) {

        return tagRepository.save(tagMapper.fromRequest(tagRequest));
    }

    public List<Tag> findAllTagsByIds() {

        return tagRepository.findAll();
    }

    public boolean tagExists(String name) {

        return tagRepository.existsByName(name);
    }

    public List<Tag> findAllTagsByIds(List<Long> tagsIds) {
        return tagRepository.findAllById(tagsIds);
    }

    public boolean tagExists(Long id) {
        return tagRepository.existsById(id);
    }

    public List<Tag> findTagsByName(String name) {
        return tagRepository.findByNameContainingIgnoreCase(name);
    }
}
