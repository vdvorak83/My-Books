package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.models.User;
import ru.itis.security.enums.Role;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface UsersRepository extends JpaRepository<User, Integer> {
    List<User> findAll();
    List<User> findAllByRole(Role role);
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserById(Integer id);

    @Modifying
    @Query("update User u set u.photo = :photo where  u.id = :id")
    void updateProfilePhoto(@Param("photo") String photo, @Param("id") Integer id);
}
