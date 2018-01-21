package ru.itis.models;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "about")
    private String about;

    @Column(name = "date_of_birth")
    private Integer dateOfBirth;

    @Column(name = "date_of_death")
    private Integer dateOfDeath;

    @Column(name = "photo_path")
    @Value("storage.path")
    private String photoPath;


    //private List writtenBooks;
}
