package br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model;

import lombok.Data;
import org.hibernate.metamodel.model.domain.IdentifiableDomainType;

import javax.persistence.*;

@Entity
@Data
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private VoteType type;

    @ManyToOne
    private User user;

}
