package ru.itis.models;

import lombok.*;
import ru.itis.security.enums.BookStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "users_books")
public class UsersBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;

    @NotNull
    @Column(name = "users_book_rating")
    private Double bookRatingByUser;

    @NotNull
    @Column(name = "book_rating")
    private Double bookRating;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "book_status")
    private BookStatus bookStatus;
}
