package br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long description;

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

}
