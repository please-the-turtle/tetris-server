package com.viklov.tetris.service;

import com.viklov.tetris.exception.UserAlreadyExistsException;
import com.viklov.tetris.exception.UserNotFoundException;
import com.viklov.tetris.model.Role;
import com.viklov.tetris.model.User;
import com.viklov.tetris.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User loadUserByUsername(@NonNull String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }

    public User createUser(User user) {
        if (isUserAlreadyExists(user)) {
            throw new UserAlreadyExistsException();
        }

        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    private boolean isUserAlreadyExists(User user) {
        return userRepository.findByUsername(user.getUsername()).isPresent();
    }
}
