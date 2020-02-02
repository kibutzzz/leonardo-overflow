package br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Answer implements Voteable, Commentable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 4096)
  private String description;

  @OneToMany
  @Builder.Default
  private List<Comment> comments = new ArrayList<>();

  @OneToMany
  @Builder.Default
  private List<Vote> votes = new ArrayList<>();

  @ManyToOne
  private User user;

}
