package br.com.kibutzzz.leonardooverflow.presentation.resources.mapper;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Vote;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.VoteType;
import br.com.kibutzzz.leonardooverflow.presentation.resources.mapper.config.BaseMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(config = BaseMapperConfig.class)
@Named("VoteMapper")
public interface VoteMapper {

  @Named("countVoteTotals")
  default Long countVoteTotals(List<Vote> votes) {
    if (votes == null) {
      return 0L;
    }
    long total = 0;

    for (Vote vote : votes) {
      if (VoteType.UP.equals(vote.getType())) {
        total++;
        continue;
      }
      total--;

    }

    return total;
  }
}
