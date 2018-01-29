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

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserById(Integer id);

    @Deprecated
    void deleteUserById(Integer id);

    @Transactional
    @Modifying
    @Query("update User u set u.photo = :photo where  u.id = :id")
    void updateProfilePhoto(@Param("photo") String photo, @Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("update User u set u.status = :status where u.id = :id")
    void setUserStatusDeleted(@Param("status") Status status, @Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("update User u set u.status = :status where u.id = :id")
    void setUserStatusEnabled(@Param("status") Status status, @Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("update User u set u.status = :status where u.id = :id")
    void setUserStatusBanned(@Param("status") Status status, @Param("id") Integer id);
}
