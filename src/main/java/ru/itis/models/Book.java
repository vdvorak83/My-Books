package ru.itis.models;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "rating")
    private double rating;

    @Column(name = "photo")
    private String photo;

    @ManyToOne(cascade = CascadeType.ALL)
    private Author bookAuthor;
}
