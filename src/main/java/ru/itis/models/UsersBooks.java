package ru.itis.models;

import lombok.*;
import ru.itis.security.enums.BookStatus;

import javax.persistence.*;

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
    @Column(name = "id")
    public Integer id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;

    @Column(name = "users_book_rating")
    private Integer bookRatingByUser;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "book_status")
    private BookStatus bookStatus;
}
