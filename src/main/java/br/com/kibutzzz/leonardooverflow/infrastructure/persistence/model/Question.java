package br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @OneToMany
    private List<Vote> votes = new ArrayList<>();

    @OneToMany
    private List<Answer> answers = new ArrayList<>();

}
