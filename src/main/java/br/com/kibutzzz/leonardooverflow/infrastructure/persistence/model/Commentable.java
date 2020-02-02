package br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model;

import java.util.List;

public interface Commentable {

  List<Comment> getComments();
}
