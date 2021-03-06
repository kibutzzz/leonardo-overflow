package br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Question implements Voteable, Commentable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  @Column(length = 4096)
  private String description;

  @ManyToOne
  private User user;

  @OneToMany
  private List<Vote> votes = new ArrayList<>();

  @OneToMany
  private List<Answer> answers = new ArrayList<>();

  @OneToMany
  private List<Comment> comments = new ArrayList<>();

  @ManyToMany
  private List<Tag> tags = new ArrayList<>();

  private LocalDateTime creationDate;

  private LocalDateTime updateDate;

}
