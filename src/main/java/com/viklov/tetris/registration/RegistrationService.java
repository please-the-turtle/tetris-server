package com.viklov.tetris.registration;

import com.viklov.tetris.authentication.Role;
import com.viklov.tetris.user.User;
import com.viklov.tetris.user.UserAlreadyExistsException;
import com.viklov.tetris.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public User registerUser(RegistrationData registrationData) {
        if (isUsernameTaken(registrationData.getUsername())) {
            throw new UserAlreadyExistsException();
        }

        User newUser = new User();
        newUser.setUsername(registrationData.getUsername());
        newUser.setRoles(Collections.singleton(Role.USER));
        newUser.setPassword(passwordEncoder.encode(registrationData.getPassword()));
        return userRepository.save(newUser);
    }

    private boolean isUsernameTaken(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
