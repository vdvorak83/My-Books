package ru.itis.models;

import lombok.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString

@Entity
@Indexed
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Field
    @Column(name = "name")
    private String name;

    @Field
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "about")
    private String about;

    @Column(name = "date_of_birth")
    private Integer dateOfBirth;

    @Column(name = "date_of_death")
    private Integer dateOfDeath;

    @Column(name = "photo")
    private String photo;
}









