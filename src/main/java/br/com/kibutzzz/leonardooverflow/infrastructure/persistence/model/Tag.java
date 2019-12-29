package br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false, length = 512)
    private String description;

}
