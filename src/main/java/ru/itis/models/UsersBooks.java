/*
package ru.itis.models;

import lombok.Getter;
import lombok.Setter;
import ru.itis.security.enums.BookStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter

@Entity
@Table(name = "users_books")
public class UsersBooks {
    @Id
    @GeneratedValue
    @NotNull
    private UsersBooksCompositeKey usersBooksCompositeKey;

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
*/
