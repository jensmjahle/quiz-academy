package edu.ntnu.service;

import edu.ntnu.dao.UserDAO;
import edu.ntnu.repository.UserRepository;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
   private final UserRepository userRepository;
    private final Logger logger = Logger.getLogger(SecurityService.class.getName());


   @Autowired
    public SecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
   public boolean correctPassword(String username, String password) {
      try {
         UserDAO user = userRepository.findByUsername(username);
          return user.getPassword().equals(password);
      } catch (Exception e) {
         logger.severe("No user with username " + username + " found: " + e.getMessage());
         throw new IllegalArgumentException("No user with username " + username + " found: ");
      }

   }

}
