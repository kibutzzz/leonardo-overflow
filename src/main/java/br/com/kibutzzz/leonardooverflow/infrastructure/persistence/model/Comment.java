package br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//TODO consider the possibility of creating abstract class that both Question and Answer inherit from
// so comment methods can avoid duplicates, as they are basically the same for those to classes
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment implements Voteable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    private User user;

    @OneToMany
    @Builder.Default
    private List<Vote> votes = new ArrayList<>();

}
