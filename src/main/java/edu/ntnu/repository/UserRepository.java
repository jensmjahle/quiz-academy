package edu.ntnu.repository;

import edu.ntnu.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
    User findByPassword(String password);
    User findByUsernameAndPassword(String username, String password);

    User findByUsernameOrEmail(String username, String email);


}
