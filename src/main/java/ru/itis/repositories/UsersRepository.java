package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.models.User;
import ru.itis.security.enums.Role;
import ru.itis.security.enums.Status;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Integer> {
    List<User> findAll();

    List<User> findAllByRole(Role role);

    Optional<User> findOneByUsername(String username);

    Optional<User> findUserById(Integer id);

    @Deprecated
    void deleteUserById(Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.hashPassword = :newPassword WHERE u.id = :id")
    void updateUserPassword(@Param("newPassword") String newPassword, @Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.username = :newUsername WHERE u.id = :id")
    void changeUsername(@Param("newUsername") String newUsername, @Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.photo = :photo WHERE u.id = :id")
    void updateUserProfilePhoto(@Param("photo") String photo, @Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.status = :status WHERE u.id = :id")
    void setUserStatusDeleted(@Param("status") Status status, @Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.status = :status WHERE u.id = :id")
    void setUserStatusEnabled(@Param("status") Status status, @Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.status = :status WHERE u.id = :id")
    void setUserStatusBanned(@Param("status") Status status, @Param("id") Integer id);
}
