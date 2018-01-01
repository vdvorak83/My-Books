package ru.itis.models;

import lombok.*;
import ru.itis.security.enums.Role;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "hashPassword")
    private String hashPassword;

    @Column(name = "hash_temp_password")
    private String hashTempPassword;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    /*@EmbeddedId
    private UsersBooksCompositeKey usersBooksCompositeKey;*/
}
