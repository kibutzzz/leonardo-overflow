package br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 512)
    private String description;

    @ManyToOne
    private User user;

    @OneToMany
    private List<Vote> votes = new ArrayList<>();

    @OneToMany
    private List<Answer> answers = new ArrayList<>();

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    @OneToMany
    private List<Tag> tags = new ArrayList<>();

    private LocalDateTime creationDate;

    private LocalDateTime updateDate;

}
