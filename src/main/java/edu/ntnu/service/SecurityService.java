package edu.ntnu.service;

import edu.ntnu.dao.UserDAO;
import edu.ntnu.repository.UserRepository;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class SecurityService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final Logger logger = Logger.getLogger(SecurityService.class.getName());


    @Autowired
    public SecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean correctPassword(String username, String password) {
        try {
            UserDAO user = userRepository.findByUsername(username);
            logger.info(passwordEncoder.encode(password));
            return passwordEncoder.matches(password, user.getPassword());
            //return user.getPassword().equals(password);
        } catch (Exception e) {
            logger.severe("No user with username " + username + " found: " + e.getMessage());
            throw new IllegalArgumentException("No user with username " + username + " found: ");
        }

    }
}

