package app.repositories;


import app.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для User нужен для SpringSecurity
 *
 *
 */

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @EntityGraph(attributePaths = {"roles"})
    User findByEmail(String email);
    @EntityGraph(attributePaths = {"roles"})
    User findUserById(Long id);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findAll();

}
