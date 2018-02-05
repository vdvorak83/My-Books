package ru.itis.models;

import lombok.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @OneToMany(mappedBy = "bookAuthor")
    private Set<Book> books;
}









