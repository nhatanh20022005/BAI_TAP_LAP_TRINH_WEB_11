package vn.bt10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bt10.entity.User;

import java.util.Optional;

/**
 * Repository for {@link User} entities.  Includes a method to find a user
 * by their email address which is used during login.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}