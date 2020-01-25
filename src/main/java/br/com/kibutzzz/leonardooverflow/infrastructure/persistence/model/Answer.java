package br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Answer implements Voteable, Commentable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 4096)
    private String description;

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    @OneToMany
    private List<Vote> votes = new ArrayList<>();

    @ManyToOne
    private User user;

}
