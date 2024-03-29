package edu.ntnu.repository;

import edu.ntnu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the User entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);

    User findByUsernameOrEmail(String username, String email);


}
