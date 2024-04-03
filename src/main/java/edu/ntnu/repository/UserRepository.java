package edu.ntnu.repository;

import edu.ntnu.dao.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the User entity.
 */
@Repository
public interface UserRepository extends JpaRepository<UserDAO, String> {
    UserDAO findByUsername(String username);

    UserDAO findByUsernameOrEmail(String username, String email);


}
