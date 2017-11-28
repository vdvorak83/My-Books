package ru.itis.models;

import lombok.*;
import ru.itis.security.enums.Role;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "users")
public class User {
    public User() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "hashPassword")
    private String hashPassword;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;
/*
    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;*/
}
